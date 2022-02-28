package com.wanho.searchnearbyplaces.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String root(@RequestParam(required = false) String error, @RequestParam(required = false)String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "index";
    }

    @GetMapping("/signup")
    public String singUpView() {
        return "user/signup";
    }


    @GetMapping("/edit")
    public String editView() {
        return "user/edit";
    }



}
