package ro.metro.qe.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class TestRun {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("project")
    private String project;

    @JsonProperty("suiteName")
    private String suiteName;

    @JsonProperty("testName")
    private String testName;

    @JsonProperty("testStatus")
    private Integer testStatus;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("runDate")
    private String runDate;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getDomain() {
        return domain;
    }

    public UUID getId() {
        return id;
    }

    public String getTestName() {
        return testName;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public Integer getTestStatus() {
        return testStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public void setTestStatus(Integer testStatus) {
        this.testStatus = testStatus;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestRun testRun = (TestRun) o;
        return id.equals(testRun.id) &&
                Objects.equals(domain, testRun.domain) &&
                Objects.equals(project, testRun.project) &&
                Objects.equals(suiteName, testRun.suiteName) &&
                Objects.equals(testName, testRun.testName) &&
                Objects.equals(testStatus, testRun.testStatus) &&
                Objects.equals(errorMessage, testRun.errorMessage) &&
                Objects.equals(runDate, testRun.runDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain, project, suiteName, testName, testStatus, errorMessage, runDate);
    }

    @Override
    public String toString() {
        return "TestRun{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", project='" + project + '\'' +
                ", suiteName='" + suiteName + '\'' +
                ", testName='" + testName + '\'' +
                ", testStatus=" + testStatus +
                ", errorMessage='" + errorMessage + '\'' +
                ", runDate='" + runDate + '\'' +
                '}';
    }

}
