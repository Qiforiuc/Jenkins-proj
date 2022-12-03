package StepDefinitions;

import Context.ScenarioContext;
import Pojo.response.UserProfile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static Hooks.Hooks.logger;
import static io.restassured.RestAssured.given;

public class UserListSteps {
    private ScenarioContext SCENARIO_CONTEXT = ScenarioContext.getInstance();

    @Given("The uri to end point is set as {string}")
    public void theUriToEndPointIsSet(String uri) {
        SCENARIO_CONTEXT.saveData("uriString", SCENARIO_CONTEXT.getData("uriString") + uri);
        logger.info("The Uri to end point is set as: " + SCENARIO_CONTEXT.getData("uriString"));
    }

    @And("The get user list request is build")
    public void theGetUserListRequestIsBuild() {
        List<UserProfile> userProfiles = given()
                .when()
                .get((String) SCENARIO_CONTEXT.getData("uriString"))
                .then().log().all()
                .extract()
                .body()
                .jsonPath()
                .getList("data", UserProfile.class);
        SCENARIO_CONTEXT.saveData("response", userProfiles);
    }

    @Then("User List contains {int} users")
    public void userListContainsUsers(int number) {
        List<UserProfile> userProfiles = (List<UserProfile>) SCENARIO_CONTEXT.getData("response");
        int counter=0;
        for(UserProfile element : userProfiles)
        {
            counter ++;
        }
        logger.info("Expected result: " + number);
        logger.info("Actual result: " + counter);
        Assertions.assertEquals(number, counter);
    }
}
