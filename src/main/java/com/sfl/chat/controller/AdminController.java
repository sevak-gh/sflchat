package com.sfl.chat.controller;

import com.sfl.chat.domain.User;
import com.sfl.chat.service.UserService;
import com.sfl.chat.dto.ProfileDto;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(value = "/admin/profiles/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("hasAnyAuthority('profile_update')")
    public String profileUpdate(@PathVariable("id") Long id, 
                                @ModelAttribute("profile") @Valid ProfileDto profile, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/profile/" + String.valueOf(id);
        } else {
            User user = userService.findById(id);
            user.setFirstName(profile.getFirstName());
            user.setLastName(profile.getLastName());
            userService.save(user);
           return "redirect:/admin/profile";
        }
    }

}
