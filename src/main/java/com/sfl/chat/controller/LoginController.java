package com.sfl.chat.controller;

import com.sfl.chat.domain.User;
import com.sfl.chat.service.UserService;
import com.sfl.chat.security.GoogleOAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * controller for signin, signout.
 *
 * @author Sevak Gharibian
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }    

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinshowSignIn(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "signin";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signout(Model model) {
        return "index";
    }

    @RequestMapping(value = "/oauth2callback", method = RequestMethod.GET)
    public String signout(@RequestParam("code") String code) {    
        LOG.debug("google oauth2 code: {}", code);
        
        String email = GoogleOAuth2.getUserEmail(code);

        LOG.debug("google oauth2 email: {}", email);

        // create auth token and session
        User user = (User)((UserDetailsService)userService).loadUserByUsername(email);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "index";
    }
}
