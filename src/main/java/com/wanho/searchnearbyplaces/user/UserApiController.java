package com.wanho.searchnearbyplaces.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {


    private final UserService userService;

    @PostMapping
    public ModelAndView signUp(@ModelAttribute UserDto.Request request) {
        userService.signup(request);
        return new ModelAndView("redirect:/");
    }

    @PutMapping
    public ModelAndView edit(@ModelAttribute UserDto.Request request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        userService.edit(user, request);


        return new ModelAndView("redirect:/place");
    }

    @DeleteMapping
    public ModelAndView delete(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userService.delete(user);
        return new ModelAndView("redirect:/logout");

    }
}
