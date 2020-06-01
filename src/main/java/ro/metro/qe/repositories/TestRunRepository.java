package ro.metro.qe.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.metro.qe.models.TestRun;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class TestRunRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    @Autowired
    private MongoClient client;
    private MongoCollection<TestRun> testRunsCollection;

    @PostConstruct
    void init() {
        testRunsCollection = client.getDatabase("qe_dashboard").getCollection("test_runs", TestRun.class);
    }

    public List<TestRun> findAllEntries() {
        return testRunsCollection.find().into(new ArrayList<>());
    }


    public TestRun createEntry(TestRun testRun) {
        testRun.setId(UUID.randomUUID());
        testRunsCollection.insertOne(testRun);
        return testRun;
    }

    public String deleteEntry(UUID id) {
        return testRunsCollection.deleteOne(eq("id", id)).getDeletedCount()+" run/runs deleted";
    }

    public TestRun findEntry(UUID id) {
        return testRunsCollection.find(eq("id", id)).first();
    }

    public List<TestRun> findRunsForDomain(String domain) {
        return  testRunsCollection.find(eq("id", domain)).into(new ArrayList<>());
    }
}
