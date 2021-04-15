package com.github.utilities;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pojos.responsePayload.branch.Branch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class APIUtils {

    private static RequestSpecification requestSpec() {
        return given().log().all()
                      .accept("application/vnd.github.v3+json")
                      .contentType(ContentType.JSON)
                      .header("Authorization", ConfigurationReader.get("access_token"));
    }

    private static ResponseSpecification responseSpecGet() {
        return expect()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    private static ResponseSpecification responseSpecPost() {
        return expect()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    public static void post(Object body, String endPoint) {
        given()
                .spec(requestSpec())
                .body(body)
        .when()
                .post(endPoint)
        .then()
                .spec(responseSpecPost())
                .log().all();
    }

    public static void post(Map<String, Object> body, String endPoint) {
        given()
                .spec(requestSpec())
                .body(body)
        .when()
                .post(endPoint)
        .then()
                .spec(responseSpecPost())
                .log().all();
    }

    public static Response post(Map<String, Object> body, Map<String, Object> pathParams, String endPoint) {
        return given()
                .spec(requestSpec())
                .pathParams(pathParams)
                .body(body)
        .when()
                .post(endPoint);
    }

    public static ValidatableResponse post(Object body, Map<String, Object> pathParams, String endPoint) {
        return given()
                .spec(requestSpec())
                .pathParams(pathParams)
                .body(body)
        .when()
                .post(endPoint)
        .then()
                .spec(responseSpecPost())
                .log().all();
    }

    public static ValidatableResponse get(String endPoint) {
        return given()
                    .spec(requestSpec())
                    .pathParam("owner","Abdujilil")
                .when()
                    .get(endPoint)
                .then()
                    .spec(responseSpecGet())
                    .log().all();
    }

    public static ValidatableResponse get(Map<String, Object> pathParams, String endPoint) {
        return given()
                      .spec(requestSpec())
                      .pathParam("owner","Abdujilil")
                      .pathParams(pathParams)
               .when()
                      .get(endPoint)
               .then()
                      .spec(responseSpecGet())
                      .log().all();
    }

    public static Response delete(String endPoint) {
        return given()
                    .spec(requestSpec())
                    .pathParam("owner","Abdujilil")
                .when()
                    .delete(endPoint)
                    .prettyPeek();
    }

    public static Response delete(Map<String, Object> pathParams, String endPoint) {
        return given()
                      .spec(requestSpec())
                      .pathParam("owner","Abdujilil")
                      .pathParams(pathParams)
               .when()
                      .delete(endPoint)
                      .prettyPeek();
    }

    public static void put(Object body, String endPoint) {
        given()
                .spec(requestSpec())
                .body(body)
        .when()
                .put(endPoint)
        .then()
                .spec(responseSpecGet())
                .log().all();
    }

    public static void put(Map<String, Object> body, String endPoint) {
        given()
                .spec(requestSpec())
                .body(body)
                .when()
                .put(endPoint)
                .then()
                .spec(responseSpecGet())
                .log().all();
    }

    public static void patch(Object body, Map<String, Object> pathParams, String endPoint) {
        given()
                .spec(requestSpec())
                .pathParam("owner","Abdujilil")
                .pathParams(pathParams)
                .body(body)
        .when()
                .patch(endPoint)
                .prettyPeek()
        .then()
                .spec(responseSpecGet())
                .log().all();
    }

    public static void patch(Map<String, Object> body, String endPoint) {
        given()
                .spec(requestSpec())
                .pathParam("owner","Abdujilil")
                .body(body)
        .when()
                .patch(endPoint)
        .then()
                .spec(responseSpecGet())
                .log().all();
    }

    public static void deleteRepo(String repoName) {
        Map<String, Object> pathParams = new LinkedHashMap<>();
        pathParams.put("owner","Abdujilil");
        pathParams.put("repo",repoName);
        given()
                .spec(requestSpec())
                .pathParams(pathParams)
        .when()
                .delete("/repos/{owner}/{repo}")
        .then()
                .statusCode(204);
    }

    public static List<Branch> getBranches(String repoName) {
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("repo",repoName);
        ValidatableResponse validatableResponse = get(pathParams, "/repos/{owner}/{repo}/branches");
        List<Branch> branches = validatableResponse.extract().jsonPath().getList("",Branch.class);
        return branches;
    }

    public static Branch getBranch(String repoName, String branchName) {
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("repo",repoName);
        pathParams.put("branch",branchName);
        ValidatableResponse validatableResponse = get(pathParams, "/repos/{owner}/{repo}/branches/{branch}");
        return validatableResponse.extract().body().as(Branch.class);
    }

    public static ValidatableResponse postBranch(Object branchBody, String repoName) {
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("owner","Abdujilil");
        pathParams.put("repo",repoName);
        return post(branchBody, pathParams,"/repos/{owner}/{repo}/git/refs");
    }

    public static ValidatableResponse renameBranch(String repoName ,String branchName, String newBranchName) {
        Map<String,Object> pathParams = new LinkedHashMap<>();
        pathParams.put("owner","Abdujilil");
        pathParams.put("repo",repoName);
        pathParams.put("branch",branchName);
        ObjectNode node = JsonUtils.createNode();
        node.put("new_name",newBranchName);
        return post(node,pathParams,"/repos/{owner}/{repo}/branches/{branch}/rename");
    }

}
