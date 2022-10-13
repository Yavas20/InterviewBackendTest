import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;

import models.CreateUserModel;
import org.junit.jupiter.api.*;
import services.GoRestService;

import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class test {

    final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", "male", "qwswswdwtadrdrdbsswafasaaaaaqsaqawwaawqaaaaaaaaaaaaaaaaaaa@test.com", "active");

    int userId;

    final CreateUserModel updateUserWithPut = new CreateUserModel("Gino Paloma", "male", "qwadatawsaswwwaqqwaaaqaaaaaaaaaaaaaaaaaaaa2@test.com", "inactive");

    final CreateUserModel updateUserWithPatch = new CreateUserModel( "qaaawaswqsawaqatadaaawwaqwaaaaaaaaaaaaa3@test.com", "active");


    @DisplayName("Create a new user")
    @Test
    @Order(1)
    public void Users_CreateUsers_Success(){


            GoRestService.createUser(createUserModel)
                    .then()
                    .statusCode(SC_CREATED)
                    .contentType("application/json; charset=utf-8")
                    .body("data.id", notNullValue())
                    .body("data.name", equalTo(createUserModel.getName()))
                    .body("data.gender", equalTo(createUserModel.getGender()))
                    .body("data.email", equalTo(createUserModel.getEmail()))
                    .body("data.status", equalTo(createUserModel.getStatus()))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("goRestSchema.json"))
                    .time(both(greaterThan(100L)).and(lessThanOrEqualTo(9500L))).log().body();


        JsonPath jsonPath = GoRestService.createUser(createUserModel).jsonPath();

        List<Map<String, Object>> datas = jsonPath.getList("data");

        System.out.println(datas);
       //System.out.println( GoRestService.createUser(createUserModel).path("data.name").toString());
        //System.out.println(GoRestService.createUser(createUserModel).path("data.message").toString());


    }


    @DisplayName("Get created user info")
    @Test()
    @Order(2)
    public void verify_Created_UserInfo(){


            GoRestService.getCreatedUser(userId)
                    .then()
                    .statusCode(SC_OK)
                    .contentType("application/json; charset=utf-8")
                    .body("data.name", equalTo(createUserModel.getName()))
                    .body("data.gender", equalTo(createUserModel.getGender()))
                    .body("data.email", equalTo(createUserModel.getEmail()))
                    .body("data.status", equalTo(createUserModel.getStatus()))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("goRestSchema.json"))
                    .time(both(greaterThan(100L)).and(lessThanOrEqualTo(1000L)));;



    }


    @DisplayName("Update created user with Put")
    @Test
    @Order(3)
    public void Update_UserInfo_With_Put(){


            GoRestService.updateUserWithPut(updateUserWithPut, userId)
                    .then()
                    .statusCode(SC_OK)
                    .contentType("application/json; charset=utf-8")
                    .body("data.id", notNullValue())
                    .body("data.name", equalTo(updateUserWithPut.getName()))
                    .body("data.gender", equalTo(updateUserWithPut.getGender()))
                    .body("data.email", equalTo(updateUserWithPut.getEmail()))
                    .body("data.status", equalTo(updateUserWithPut.getStatus()))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("goRestSchema.json"))
                    .time(both(greaterThan(100L)).and(lessThanOrEqualTo(1000L)));;



    }


    @DisplayName("Update created user with Patch")
    @Test
    @Order(4)
    public void Update_UserInfo_With_Patch(){


            GoRestService.updateUserWithPatch(updateUserWithPatch, userId)
                    .then()
                    .statusCode(SC_OK)
                    .contentType("application/json; charset=utf-8")
                    .body("data.id", notNullValue())
                    .body("data.email", equalTo(updateUserWithPatch.getEmail()))
                    .body("data.status", equalTo(updateUserWithPatch.getStatus()))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("goRestSchema.json"))
                    .time(both(greaterThan(100L)).and(lessThanOrEqualTo(1000L)));;


    }


    @DisplayName("Delete created user")
    @Test
    @Order(5)
    public void delete_User(){



            GoRestService.deleteUser(userId)
                    .then()
                    .statusCode(SC_NO_CONTENT)
                    .time(both(greaterThan(100L)).and(lessThanOrEqualTo(1000L)));;



    }


    @DisplayName("Get deleted user info - negative test case")
    @Test
    @Order(6)
    public void verify_Deleted_UserInfo(){


            GoRestService.getCreatedUser(userId)
                    .then()
                    .statusCode(SC_NOT_FOUND)
                    .body("data.message", equalTo("Resource not found"))
                    .time(both(greaterThan(100L)).and(lessThanOrEqualTo(1000L)));;



    }



}
