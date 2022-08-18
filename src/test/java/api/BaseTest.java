package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {
    private static final String path = "http://test-api.d6.dev.devcaz.com";

    private static final String username = "front_2d6b0a8391742f5d789d7d915755e09e";

    private static String accessToken;
    private static String refreshToken;
    private static int user_id;

    public static int randomInt() {
        Random rn = new Random();
        return rn.nextInt();
    }

    @BeforeClass(description = "#1 Получить токен гостя (Client Credentials Grant)")

    public void getAccessToken() {

        Response response = given().log().all().auth().preemptive().basic(username, "")
                //.contentType(ContentType.JSON)
                .formParam("grant_type", "client_credentials")
                .when()
                .post(path + "/v2/oauth2/token")
                .then().log().all()
                .assertThat()
                .body("access_token", notNullValue())
                .statusCode(200)
                .extract().response();


        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        accessToken = jsonObject.get("access_token").toString();
        System.out.println("token  ==>" + accessToken);

    }


    @Test(description = "#2 Зарегистрировать игрока")//тест падает - невозможно получить игрока

    public void createNewUser() {
        int num = randomInt();
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .formParam("grant_type", "password")
                .formParam("username", "newuser" + num)
                .formParam("password_change", "amFuZWRvZTEyMw==")
                .formParam("password_repeat", "amFuZWRvZTEyMw==")
                .formParam("email", "testUser" + num + "@te.st")
                .formParam("name", "New")
                .formParam("surname", "User")
                .when().log().all()
                .post(path + "/v2/players")
                .then().log().all()
                .statusCode(201)
                .body("username", equalTo("newuser" + num))
                .body("email", equalTo("testUser" + num + "@te.st"))
                .body("name", equalTo("New"))
                .body("surname", equalTo("User"))
                .body("bonuses_allowed", equalTo(true))
                .body("is_verified", equalTo(false))
                .extract().response();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        user_id = (int) jsonObject.get("id");

    }

    @Test(description = "#3 Resource Owner Password Credentials Grant")
    public void login() {

        given().log().all().auth().preemptive().basic(username, "")
                .formParam("grant_type", "password")
                .formParam("username", "johndoe")
                .formParam("password", "amFuZWRvZTEyMw==")
                .when().log().all()
                .post(path + "/v2/oauth2/token")
                .then().log().all()
                .assertThat()
                .body("refresh_token", notNullValue())
                .statusCode(200)
                .extract().response();

    }

    @Test(description = "#4 Запросить данные профиля игрока")//тест падает - невозможно получить игрока
    public void getRegisteredUser() {
        given()
                .header("Authorization", "Bearer " + accessToken)
                .get(path + "/v2/players/" + user_id)
                .then().log().all()
                .statusCode(200);
    }

    @Test(description = "#5 Запросить данные другого игрока")
    public void getAnotherUser() {
        given()
                .header("Authorization", "Bearer " + accessToken)
                .get(path + "/v2/players")
                .then().log().all()
                .statusCode(404);
    }
}