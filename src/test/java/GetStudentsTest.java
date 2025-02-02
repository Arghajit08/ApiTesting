import com.maxsoft.testngtestresultsanalyzer.TestAnalyzeReportListener;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;


@Listeners(TestAnalyzeReportListener.class)
public class UsersTest {

    private final String FIRST_NAME = "Testing";
    private final String MIDDLE_NAME = "M";
    private final String LAST_NAME = "Bhowmik";
    private final String DOB = "08/10/2001";
    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setup() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
    }

    @Test()
    public void testGetStudentDetail() {
        final JSONObject reqJson = new JSONObject();
        reqJson.put("first_name", FIRST_NAME);
        reqJson.put("middle_name", MIDDLE_NAME);
        reqJson.put("last_name", LAST_NAME);
        reqJson.put("date_of_birth", DOB);

        ValidatableResponse response = given()
                .spec(requestSpecification)
                .body(reqJson.toString())
                .when().log().all()
                .post(Constants.GET_STUDENT_ENDPOINT)
                .then().log().all()
                .statusCode(SC_OK);
      
        USER_ID = response.extract().body().jsonPath().get("data.id").toString();
    }

    

    @AfterClass
    public void afterProcess() {
        
    }
}
