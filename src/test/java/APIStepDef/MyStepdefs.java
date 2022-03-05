package APIStepDef;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_classes.users.CreateUser;
import pojo_classes.users.UpdateUser;
import utils.ConfigReader;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utils.ApiUtils.serializePOJO;
import static utils.ConfigReader.getProperty;

public class MyStepdefs {

    Response response;
    String actualUserId;


    @Given("Create user with {string} and {string}")
    public void createUserWithAnd(String name, String job) {
        CreateUser createUser = CreateUser.builder().name(name).job(job).build();

        response = RestAssured.given().log().all()
                .contentType(JSON)
                .header("Authorization", getProperty("Token"))
                .body(serializePOJO(createUser))
                .post(ConfigReader.getProperty("GoRestURL") + "api/users")
                .then().log().all().extract().response();

        actualUserId = JsonPath.read(response.asString(), "id");
    }

    @And("Validate that status code is {int}")
    public void validateThatStatusCodeIs(int expectedStatusCode) {

        int actualStatusCode = response.getStatusCode();

        assertThat(
                "I am expecting status code: " + expectedStatusCode,
                actualStatusCode,
                is(expectedStatusCode)
        );

    }

    @And("Updating the user with the following data")
    public void updatingTheUserWithTheFollowingData(Map<String, String> data) {
        UpdateUser updateUserWithLombok = UpdateUser
                .builder().name(data.get("name")).build();

        response = RestAssured.given().log().all()
                .contentType(JSON)
                .header("Authorization", getProperty("Token"))
                .body(serializePOJO(updateUserWithLombok))
                .patch(getProperty("GoRestURL") + "api/users" + actualUserId)
                .then().log().all().extract().response();
    }

    @When("I delete user")
    public void iDeleteUser() {
        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", getProperty("Token"))
                .delete(getProperty("GoRestURL") + "api/users" + actualUserId)
                .then().log().all().extract().response();
    }

    @And("Make GET call to get user with {string}")
    public void makeGETCallToGetUserWith(String url) {

        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", getProperty("Token"))
                .get(url + actualUserId)
                .then().log().all().extract().response();

        //actualEmail = JsonPath.read(response.asString(), "email");


    }
}
