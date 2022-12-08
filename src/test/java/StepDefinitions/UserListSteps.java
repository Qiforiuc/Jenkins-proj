package StepDefinitions;

import Context.ScenarioContext;
import Pojo.request.LoginBody;
import Pojo.response.LoginResponse;
import Pojo.response.UserProfile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static Hooks.Hooks.logger;
import static io.restassured.RestAssured.given;

public class UserListSteps {
    private ScenarioContext SCENARIO_CONTEXT = ScenarioContext.getInstance();

    @Given("The uri to end point is set as {string}")
    public void theUriToEndPointIsSet(String uri) {
        String uriString = (SCENARIO_CONTEXT.getData("urlString") + uri);
        SCENARIO_CONTEXT.saveData("uriString", uriString);
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
        int size = userProfiles.size();
        logger.info("Expected result: " + number);
        logger.info("Actual result: " + size);
        Assertions.assertEquals(number, size);
    }

    @And("First user name is {string}")
    public void firstUserNameIsMichael(String name) {
        List<UserProfile> userProfiles = (List<UserProfile>) SCENARIO_CONTEXT.getData("response");
        String username = userProfiles.get(0).getFirst_name();
        Assertions.assertEquals(name, username);
    }

    @And("The user {string} and {string} are set")
    public void theUserLoginAndPasswordAreSet(String email, String password) {
        LoginBody loginBody = new LoginBody(email, password);
        RequestSpecification requestSpecification = given()
                .when()
                .contentType("application/json")
                .body(loginBody);
        SCENARIO_CONTEXT.saveData("request", requestSpecification);
    }

    @And("Post request is sent")
    public void postRequestIsSent() {
        Response response = ((RequestSpecification)SCENARIO_CONTEXT.getData("request")).post((String) SCENARIO_CONTEXT.getData("uriString"));
        SCENARIO_CONTEXT.saveData("response", response);
    }

    @Then("User token is received")
    public void userTokenIsReceived() {
        LoginResponse loginResponse = ((Response)SCENARIO_CONTEXT.getData("response")).then().extract().as(LoginResponse.class);
        Assertions.assertNull(123);
    }
}
