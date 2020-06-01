package ro.metro.qe.controllers;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.metro.qe.models.TestRun;
import ro.metro.qe.services.TestRunService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/runs")
@Api(value = "/runs", description = "the test-run API")
public class TestRunController {

    private final TestRunService testRunService;

    public TestRunController(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{domain}/{project}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<TestRun> getAllRunsForProjectAndDates(@PathVariable(value = "domain") final String domain,
                                                      @PathVariable(value = "project") final String project,
                                                      @PathVariable(value = "date") final String date) {
        if (Strings.isNullOrEmpty(domain) || Strings.isNullOrEmpty(project)) {
            throw new IllegalArgumentException("Domain or project cannot be empty");
        }

        return testRunService.getAllTestRunsForProjectAndDates(domain, project, date);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/run")
    @ResponseStatus(HttpStatus.CREATED)
    public TestRun createRun(@RequestBody TestRun testRun) {
        return testRunService.persistTestRun(validate(testRun));
    }

    private TestRun validate(final TestRun testRun) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(testRun.getDomain()), "domain empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(testRun.getProject()), "project empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(testRun.getRunDate()), "date empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(String.valueOf(testRun.getTestStatus())), "test result empty");
        return testRun;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletetestrun/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteRun(@PathVariable UUID id) {
        return testRunService.deleteTestRun(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/findtestrun/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestRun getRun(@PathVariable UUID id) {
        return testRunService.findTestRun(id);
    }

}
