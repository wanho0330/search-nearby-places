package com.wanho.searchnearbyplaces.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceApiController {

    private final PlaceService placeService;

    @GetMapping("/{id}")
    public ModelAndView getPlace(@PathVariable Long id, Model model) {
        if(id != null) {
            PlaceDto.Response response = placeService.findById(id);
            model.addAttribute("response", response);
        }
        return new ModelAndView("place/detail");
    }

    @PostMapping
    public ModelAndView savePlace(@ModelAttribute PlaceDto.Request request) {
        PlaceDto.Response response = placeService.savePlace(request);

        return new ModelAndView("redirect:/place/" + response.getId());
    }

    @PutMapping("/{id}")
    public ModelAndView editPlace(@ModelAttribute PlaceDto.Request request,@PathVariable Long id) {
        PlaceDto.Response response = placeService.editPlace(request, id);

        return new ModelAndView("redirect:/place/" + response.getId());
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);

        return new ModelAndView("redirect:/place");
    }
}
