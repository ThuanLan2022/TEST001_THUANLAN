package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commons.GlobalConstants;
import cucumberOption.Hooks;

import java.util.*;
import java.util.stream.Collectors;

public class ApiTestSteps {

    private static final Logger logger = LoggerFactory.getLogger(ApiTestSteps.class);

    private Response response;
    private List<Map<String, Object>> repos;

    @Given("Call the GitHub API for SeleniumHQ repositories")
    public void callGithubApi() {
        response = RestAssured
                .given()
                .when()
                .get(GlobalConstants.BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String msg = "✅ Successfully called GitHub API for SeleniumHQ repositories";
        logger.info(msg);
        Hooks.logToReport(msg);
    }

    @When("Retrieve all repositories")
    public void retrieveAllRepositories() {
        repos = response.jsonPath().getList("$");
        Assert.assertNotNull(repos);

        String msg = "✅ Retrieved " + repos.size() + " repositories";
        logger.info(msg);
        Hooks.logToReport(msg);
    }

    @Then("Should see the total number of open issues")
    public void shouldSeeTotalOpenIssues() {
        int totalOpenIssues = repos.stream()
                .mapToInt(repo -> ((Integer) repo.get("open_issues_count")))
                .sum();

        String msg = "✅ Total open issues: " + totalOpenIssues;
        logger.info(msg);
        Hooks.logToReport(msg);

        Assert.assertTrue(totalOpenIssues >= 0);
    }

    @Then("Should see repositories sorted by last updated date")
    public void shouldSeeReposSortedByLastUpdated() {
        List<Map<String, Object>> sortedRepos = repos.stream()
                .sorted((r1, r2) -> ((String) r2.get("updated_at"))
                        .compareTo((String) r1.get("updated_at")))
                .collect(Collectors.toList());

        logger.info("✅ Top 5 repos by updated date:");
        Hooks.logToReport("✅ Top 5 repos by updated date:");
        sortedRepos.stream().limit(5).forEach(repo -> {
            String line = " - " + repo.get("name") + " | updated_at: " + repo.get("updated_at");
            logger.info(line);
            Hooks.logToReport(line);
        });

        String first = (String) sortedRepos.get(0).get("updated_at");
        String last = (String) sortedRepos.get(sortedRepos.size() - 1).get("updated_at");
        Assert.assertTrue(first.compareTo(last) >= 0);
    }

    @Then("Should see the repository with the most watchers")
    public void shouldSeeRepoWithMostWatchers() {
        Map<String, Object> mostWatchedRepo = repos.stream()
                .max(Comparator.comparingInt(r -> (Integer) r.get("watchers_count")))
                .orElseThrow();

        String msg = "✅ Repo with most watchers: " + mostWatchedRepo.get("name")
                + " | watchers: " + mostWatchedRepo.get("watchers_count");
        logger.info(msg);
        Hooks.logToReport(msg);

        Assert.assertNotNull(mostWatchedRepo.get("name"));
    }
}
