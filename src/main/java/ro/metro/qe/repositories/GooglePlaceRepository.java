package ro.metro.qe.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.metro.qe.clients.Place;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GooglePlaceRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    @Autowired
    private MongoClient client;
    private MongoCollection<Place> placesCollection;

    @PostConstruct
    void init() {
        placesCollection = client.getDatabase("ana_disertatie").getCollection("google_places", Place.class);
    }

    public List<Place> createEntry(List<Place> places) {
        places.forEach(place -> placesCollection.insertOne(place));
        return places;
    }

    public List<Place> findAllEntries() {
        return placesCollection.find().into(new ArrayList<>());
    }


}