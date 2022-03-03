package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class API {
    Response response;
    ObjectMapper objectMapper = new ObjectMapper();

    private static Logger logger = LogManager.getLogger(API.class);

    @BeforeSuite
    public void testingStarts() {
        logger.info("Starting API Automation Test with the TestNG");
    }

    @BeforeTest
    public void beforeTesting() {
        RestAssured.baseURI = utils.ConfigReader.getProperty("PetsURL");
    }


}
