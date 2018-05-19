package com.sfl.chat.controller;

import com.sfl.chat.domain.User;
import com.sfl.chat.service.UserService;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * controller for user profile.
 *
 * @author Sevak Gharibian
 */
@Controller
public class ProfileController {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('profile_view')")
    public String view(Model model, Principal principal, HttpServletRequest request) {
        User user = userService.findByUsername(principal.getName());
        // get picture url
        String  pictureUrl = ServletUriComponentsBuilder
                         .fromContextPath(request)
                         .path("/pictures/" + user.getPictureFileName())
                         .build()
                         .toUriString();
        LOG.debug("picture url path: {}", pictureUrl);
        model.addAttribute("user", user);
        model.addAttribute("picurl", pictureUrl);
        return "profile";
    }
}
