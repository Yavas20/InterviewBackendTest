package services;

import io.restassured.response.Response;
import models.CreateUserModel;


public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){

        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }

    public static Response getCreatedUser(int userId){

        return defaultRequestSpecification()
                .given().pathParam("id", userId)
                .when()
                .get("/public/v1/users/{id}");

    }

    public static Response updateUserWithPut(final CreateUserModel createUserModel, int userId){

        return defaultRequestSpecification()
                .given().pathParam("id", userId)
                .body(createUserModel)
                .when()
                .put("/public/v1/users/{id}");
    }

    public static Response updateUserWithPatch(final CreateUserModel createUserModel, int userId){

        return defaultRequestSpecification()
                .given().pathParam("id", userId)
                .body(createUserModel)
                .when()
                .patch("/public/v1/users/{id}");
    }


    public static Response deleteUser(int userId){

        return defaultRequestSpecification()
                .given().pathParam("id", userId)
                .when()
                .delete("/public/v1/users/{id}");

    }




}
