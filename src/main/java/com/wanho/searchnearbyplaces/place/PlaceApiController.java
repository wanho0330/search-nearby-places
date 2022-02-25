package com.wanho.searchnearbyplaces.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceApiController {

    private final PlaceService placeService;

    @PostMapping
    public ModelAndView savePlace(@ModelAttribute PlaceDto.Request request, RedirectAttributesModelMap map) {
        PlaceDto.Response response = placeService.savePlace(request);
        map.addAttribute("id", response.getId());

        return new ModelAndView("redirect:/place", map);
    }

    @PutMapping("/{id}")
    public ModelAndView editPlace(@ModelAttribute PlaceDto.Request request,@PathVariable Long id, RedirectAttributesModelMap map) {
        PlaceDto.Response response = placeService.editPlace(request, id);
        map.addAttribute("id", response.getId());

        return new ModelAndView("redirect:/place", map);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);

        return new ModelAndView("redirect:/place");
    }
}
