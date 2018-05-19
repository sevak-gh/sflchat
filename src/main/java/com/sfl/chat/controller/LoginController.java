package com.sfl.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * controller for signin, signout.
 *
 * @author Sevak Gharibian
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signinshowSignIn(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "signin";
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signout(Model model) {
        return "index";
    }
}
