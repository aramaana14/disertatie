package ro.metro.qe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.metro.qe.clients.GooglePlaces;
import ro.metro.qe.clients.Place;
import ro.metro.qe.clients.PlacesResult;
import ro.metro.qe.repositories.GooglePlaceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GooglePlacesService {

    private GooglePlaces googlePlaces;
    private final GooglePlaceRepository googlePlaceRepository;

    @Autowired
    public GooglePlacesService(GooglePlaces googlePlaces, GooglePlaceRepository googlePlaceRepository) {
        this.googlePlaces = googlePlaces;
        this.googlePlaceRepository = googlePlaceRepository;
    }


    public List<Place>  searchGooglePlace(String query) {
        PlacesResult placeResult = googlePlaces.searchText(query);
        List<Place> placeResults = placeResult.getResults();
/*        if (placeResult.getNextPageToken() != null) {
            System.out.println("Tokeeeen   "+placeResult.getNextPageToken());
            placesNextPage = googlePlaces.nextPageResults(placeResult.getNextPageToken()).getResults();
            placesNextPage.forEach(place -> System.out.println(place.getName()));
        }*/
        return googlePlaceRepository.createEntry(placeResults);

    }

    public List<Place> getAllGymsForWithRaiting(Double rating){
    return googlePlaceRepository.findAllEntries()
            .stream()
            .filter( entry ->
                    entry.getRating().equals(rating)
            )
            .collect(Collectors.toList());

    }
}
