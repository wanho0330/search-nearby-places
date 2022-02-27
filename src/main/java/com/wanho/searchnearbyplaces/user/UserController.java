package com.wanho.searchnearbyplaces.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String root() {

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
