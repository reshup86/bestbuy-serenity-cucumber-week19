package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {

    @Step("Creating Products with name : {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lng: {8}, hours: {9}, servicesDate: {10}")
    public ValidatableResponse createStore(String name,
                                           String type,
                                           String address,
                                           String address2,
                                           String city,
                                           String state,
                                           String zip,
                                           Double lat,
                                           Double lng,
                                           String hours,
                                           HashMap<Object, Object> servicesData) {

        StoresPojo storesPojo = StoresPojo.getStoresPojo(name,type,address,address2,city,state,zip,lat,lng,hours, servicesData);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_STORES)
                .then();
    }

    @Step ("Get the store information with Id : {0}")
    public  HashMap<String, Object> getStoreById(int storeID){
        return SerenityRest.given().log().all()
                .when()
                .pathParam("storeID", storeID)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("\"Update Products with name")
    public ValidatableResponse updateStore(int storeID,String name) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .pathParam("storeID",storeID)
                .when()
                .patch(EndPoints.UPDATE_SINGLE_STORE_BY_ID)
                .then();
    }
    @Step("Deleting Store with ID {0}")
    public ValidatableResponse deleteStore(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.DELETE_SINGLE_STORE_BY_ID)
                .then();
    }

    @Step("Getting Store with ID {0}")
    public ValidatableResponse getStoreByID(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

    @Step("Getting All the Stores")
    public ValidatableResponse getAllStores() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then()
                .statusCode(200)
                .log().all();

    }

    @Step("Get the Store information with ID :{0}")
    public HashMap<String, ?>getStoreInfoByName(int storeID){
        HashMap<String,?>storeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("storeID",storeID)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return storeMap;
    }

    @Step("update store with store ID:{0}, name : {1}, type :{2},address:{3}")
    public ValidatableResponse updateStoreInfo(int storeID, String name, String type, String address){
        StoresPojo storePojo = new StoresPojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .pathParam("storeID",storeID)
                .when()
                .patch(EndPoints.UPDATE_SINGLE_STORE_BY_ID)
                .then().log().ifValidationFails();
    }


    @Step ("Get store information with store name: {0}")
    public HashMap<String,Object> getStoreInformationWithName(String name){

        return SerenityRest.given()
                .log().all()
                .queryParam("name",name)
                .when()
                .get(Path.STORES)
                .then()
                .extract()
                .path("data.findAll{it.name='"+name+"'}.get(0)");

    }

    @Step ("Change store information for store ID: {0}, name: {1}, type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}," +
            "zip: {7}, lat: {8}, lng: {9}, hours: {10} ")
    public ValidatableResponse changeStoreInfo(int storeID, String name, String type, String address, String address2, String city,
                                               String state, String zip, double lat, double lng, String hours){

        StoresPojo storesPojo = StoresPojo.storesPojo(name,type,address,address2,city,state,zip,lat,lng,hours);

        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("storeID",storeID)
                .body(storesPojo)
                .when()
                .put(Path.STORES+EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();

    }

    @Step ("Verify store record has been deleted for storeID: {0}")
    public ValidatableResponse verifyStoreRecord(int storeID){
        return SerenityRest.given()
                .log().all()
                .pathParam("storeID",storeID)
                .when()
                .get(Path.STORES+ EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

    public ValidatableResponse createNewStore(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours) {

        StoresPojo storesPojo = StoresPojo.storesPojo(name,type,address,address2,city,state,zip,lat,lng,hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_STORES)
                .then();
    }
}
