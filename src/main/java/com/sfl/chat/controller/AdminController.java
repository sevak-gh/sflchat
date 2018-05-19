package com.sfl.chat.controller;

import com.sfl.chat.domain.User;
import com.sfl.chat.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
