package com.wanho.searchnearbyplaces.place;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String getPlace(@RequestParam(required = false) Long id, Model model) {
        if(id != null) {
            PlaceDto.Response response = placeService.findById(id);
            model.addAttribute("response", response);
        }
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
