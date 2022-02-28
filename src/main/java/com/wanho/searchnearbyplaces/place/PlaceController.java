package com.wanho.searchnearbyplaces.place;


import com.wanho.searchnearbyplaces.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String root() {

        return "place/list";
    }


    @GetMapping("/add")
    public String savePlaceView() {
        return "place/add";
    }


    @GetMapping("/edit")
    public String editPlaceView(@RequestParam Long id, Model model) {

        PlaceDto.Response response = placeService.findById(id);
        model.addAttribute("response", response);

        return "place/edit";
    }


    @GetMapping("/list")
    public ModelAndView getPlace(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<PlaceDto.Response> responses = placeService.findPlacesByUser(user);

        model.addAttribute("responses", responses);

        return new ModelAndView("place/list");
    }


}
