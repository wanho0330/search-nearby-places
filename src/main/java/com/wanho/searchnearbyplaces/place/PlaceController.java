package com.wanho.searchnearbyplaces.place;


import com.wanho.searchnearbyplaces.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String root() {

        return "place/detail";
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




}
