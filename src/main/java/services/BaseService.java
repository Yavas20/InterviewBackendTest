package services;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {

    protected static RequestSpecification defaultRequestSpecification(){

        return restAssured()
                .header("Accept","application/json" )
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer 8d46fc7ba50064866ae3e6149d068f5c9fc2f27bf59502485fbdab8145cb69c6");
    }


    protected static RequestSpecification restAssured() {

        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.urlEncodingEnabled = false;

        return given()
                .config(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }
}
