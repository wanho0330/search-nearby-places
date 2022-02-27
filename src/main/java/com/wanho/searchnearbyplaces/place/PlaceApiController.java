package com.wanho.searchnearbyplaces.place;

import com.wanho.searchnearbyplaces.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView savePlace(Authentication authentication, @ModelAttribute PlaceDto.Request request) {
        User user = (User) authentication.getPrincipal();

        PlaceDto.Response response = placeService.savePlace(user, request);

        return new ModelAndView("redirect:/place/" + response.getId());
    }

    @PutMapping("/{id}")
    public ModelAndView editPlace(@ModelAttribute PlaceDto.Request request,@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        PlaceDto.Response response = placeService.editPlace(user, request, id);

        return new ModelAndView("redirect:/place/" + response.getId());
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletePlace(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        placeService.deletePlace(user, id);

        return new ModelAndView("redirect:/place");
    }

    // ajax로 처리 필요
    @GetMapping("/list")
    public ModelAndView getPlace(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<PlaceDto.Response> responses = placeService.findPlacesByUser(user);

        model.addAttribute("responses", responses);

        return new ModelAndView("place/detail");
    }
}
