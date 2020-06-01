package ro.metro.qe.controllers;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ro.metro.qe.clients.Place;
import ro.metro.qe.services.GooglePlacesService;

import java.util.List;

@Component
@RestController
@RequestMapping("/googleplaces")
@Api(value = "/googleplaces", description = "google place")
public class GooglePlacesController {

    private final GooglePlacesService googlePlacesService;

    public GooglePlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Place> getGooglePlaces(@RequestParam(value = "query") final String searchString){
        if (Strings.isNullOrEmpty(searchString)){
            throw new IllegalArgumentException("No search text added");
        }

        return googlePlacesService.searchGooglePlace(searchString);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rating/{rating}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Place> getAllGymsForWithRaiting(@PathVariable(value = "rating") final Double rating){
        if (Double.isNaN(rating)) {
            throw new IllegalArgumentException("Rating cannot be empty");
        }

        return googlePlacesService.getAllGymsForWithRaiting(rating);
    }
}