package com.sfl.chat.controller;

import com.sfl.chat.domain.User;
import com.sfl.chat.domain.Role;
import com.sfl.chat.service.UserService;
import com.sfl.chat.dto.ProfileDto;

import java.util.List;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for admin.
 *
 * @author Sevak Gharibian
 */
@Controller
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    private static final String USER_ROLE_NAME = "USER";
    private static final long USER_ROLE_ID = 1L;
    private static final long PROFILE_PICTURE_MAX_FILE_SIZE = 1024 * 1024;
    private static final String PROFILE_PICTURE_UPLOAD_PATH = "/opt/sflchat/resources/pictures/";


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('admin_view')")
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('admin_profile')")
    public String adminProfile(Model model) {
        List<User> users = userService.findByRoleName(USER_ROLE_NAME);
        model.addAttribute("users", users);
        return "adminProfile";
    }

    @RequestMapping(value = "/admin/chat", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('admin_chat')")
    public String adminChat(Model model) {
        return "adminChat";
    }

    @RequestMapping(value = "/admin/profiles/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('profile_update')")
    public String profileUpdateView(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        ProfileDto profile = new ProfileDto();
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());
        profile.setPictureFileName(user.getPictureFileName());
        model.addAttribute("profile", user);
        return "profileUpdate";
    }

    @RequestMapping(value = "/admin/profiles/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('profile_update')")
    public String profileUpdate(@PathVariable("id") Long id, 
                                @ModelAttribute("profile") @Valid ProfileDto profile, BindingResult result,
                                @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "admin/profile/" + String.valueOf(id);
        } else {
            User user = userService.findById(id);
            user.setFirstName(profile.getFirstName());
            user.setLastName(profile.getLastName());

            // check file size
//            if (file.getSize() > PROFILE_PICTURE_MAX_FILE_SIZE) {
//                return "admin/profile/" + String.valueOf(id);
//            }


            // extract file extension
//            LOG.debug("profile file: {}", file.getOriginalFilename());
//            String[] tokens = file.getOriginalFilename().split("\\.");
//            LOG.debug("profile file tokens: {}", tokens.toString());
//            String extension = "jpg";
//            if ((tokens == null) || (tokens.length != 2)) {
//                extension = tokens[1];
//            }

        // save file
        String fileName = String.format("%d_%s", id, file.getOriginalFilename());
        File destination = new File(PROFILE_PICTURE_UPLOAD_PATH + fileName);
        try {
            file.transferTo(destination);
        } catch (IOException e) {
            // rethrow, to be handled by global error handler
            throw new RuntimeException(e);
        }

            user.setPictureFileName(fileName);
            userService.save(user);
           return "redirect:/admin/profile";
        }
    }

    @RequestMapping(value = "/admin/profile/create", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('profile_create')")
    public String createView(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "profileCreate";
    }

    @RequestMapping(value = "/admin/profile/create", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('profile_create')")
    public String profileUpdate(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "profileCreate";
        } else {            
            // hash the password
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            user.setPassword(bcrypt.encode(user.getPassword()));
            Role role = new Role();
            role.setId(USER_ROLE_ID);
            user.getRoles().add(role);
            userService.save(user);
            return "redirect:/admin/profile";
        }
    }

    @RequestMapping(value = "/admin/profiles/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('profile_delete')")
    public String profileUpdate(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        user.setEnabled(false);
        userService.save(user);
        return "redirect:/admin/profile";    
    }
}
