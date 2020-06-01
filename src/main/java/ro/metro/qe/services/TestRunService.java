package ro.metro.qe.services;


import org.springframework.stereotype.Service;
import ro.metro.qe.models.TestRun;
import ro.metro.qe.repositories.TestRunRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TestRunService {

    private final TestRunRepository testRunRepository;

    public TestRunService(TestRunRepository testRunRepository) {
        this.testRunRepository = testRunRepository;
    }

    public List<TestRun> getAllRuns() {
        return (List<TestRun>) testRunRepository.findAllEntries();
    }

    public TestRun persistTestRun(TestRun testRun) {
        return testRunRepository.createEntry(testRun);
    }

    public String deleteTestRun(UUID id) {
        return testRunRepository.deleteEntry(id);
    }

    public TestRun findTestRun(UUID id) {
        return testRunRepository.findEntry(id);
    }

    public List<TestRun> getAllTestRunsForProjectAndDates(final String domain,
                                                          final String project,
                                                          final String date) {

        return testRunRepository.findAllEntries()
                .stream()
                .filter( entry -> entry.getDomain().equals(domain) &&
                                  entry.getProject().equals(project) &&
                                  entry.getRunDate().equals(date))
                .collect(Collectors.toList());

    }

}
