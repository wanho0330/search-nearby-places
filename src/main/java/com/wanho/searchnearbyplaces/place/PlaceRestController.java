package com.wanho.searchnearbyplaces.place;

import com.wanho.searchnearbyplaces.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceRestController {

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
    public ModelAndView savePlace(Authentication authentication,@ModelAttribute @Valid PlaceDto.Request request) {
        User user = (User) authentication.getPrincipal();

        PlaceDto.Response response = placeService.savePlace(user, request);

        return new ModelAndView("redirect:/place/list");
    }

    @PutMapping("/{id}")
    public ModelAndView editPlace(@ModelAttribute @Valid PlaceDto.Request request,@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        PlaceDto.Response response = placeService.editPlace(user, request, id);

        return new ModelAndView("redirect:/place/" + response.getId());
    }

    @DeleteMapping("/{id}")
    public ModelAndView deletePlace(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        placeService.deletePlace(user, id);

        return new ModelAndView("redirect:/place/list");
    }


    @GetMapping("/search")
    public List<PlaceDto.Response> getPlaces(@RequestParam String address) {
        return placeService.findPlacesByAddressContaining(address);
    }
}
