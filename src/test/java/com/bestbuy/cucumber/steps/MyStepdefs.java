package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.StoresSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {
    static ValidatableResponse response;
    static String name = "Sia" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = "55 Proctor Close";
    static String address2 = "Mk, UK";
    static String city = "Mk";
    static String state = "Buckinghamshire";
    static String zip = "nw9 5ot";
    static double lat = 44.969658;
    static double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9;Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int storeID;

    @Steps
    StoresSteps storesSteps;
    @Given("^User is on stores page endpoint$")
    public void userIsOnStoresPageEndpoint() {

    }

    @When("^User sends a GET request to the stores endpoint$")
    public void userSendsAGETRequestToTheStoresEndpoint() {
        response = storesSteps.getAllStores().statusCode(200);
    }

    @Then("^User must get back a valid status response code (\\d+) from stores endpoint$")
    public void userMustGetBackAValidStatusResponseCodeFromStoresEndpoint(int statusCode) {
        response.statusCode(statusCode);
        response.assertThat().statusCode(statusCode);
    }

    @When("^User sends a POST request with a valid payload to the stores endpoint$")
    public void userSendsAPOSTRequestWithAValidPayloadToTheStoresEndpoint() {
        HashMap<Object,Object> serviceData = new HashMap<>();
        response = storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours,serviceData);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @Then("^User must get back a valid status response code (\\d+) from create stores endpoint$")
    public void userMustGetBackAValidStatusResponseCodeFromCreateStoresEndpoint(int statusCode) {
        response.statusCode(statusCode);
    }

    @When("^User send a GET request to newly created store with store ID$")
    public void userSendAGETRequestToNewlyCreatedStoreWithStoreID() {
        HashMap<String, ?> storeMap = storesSteps.getStoreInfoByName(storeID);
    }

    @And("^User should verify if the store is created with correct details$")
    public void userShouldVerifyIfTheStoreIsCreatedWithCorrectDetails() {
        HashMap<String, ?> storeMap = storesSteps.getStoreInfoByName(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        Assert.assertThat(storeMap, hasValue(type));
        Assert.assertThat(storeMap, hasValue(city));
    }

    @When("^User send a PUT request to newly created store with store ID$")
    public void userSendAPUTRequestToNewlyCreatedStoreWithStoreID() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        storesSteps.updateStoreInfo(storeID,name,type,address);
    }

    @And("^User should verify if the stores details are updated with correct details$")
    public void userShouldVerifyIfTheStoresDetailsAreUpdatedWithCorrectDetails() {
        HashMap<String, ?> productList = storesSteps.getStoreInfoByName(storeID);
        Assert.assertThat(productList, hasValue(name));
    }
    @When("^User send a DELETE request to newly created store with store ID$")
    public void userSendADELETERequestToNewlyCreatedStoreWithStoreID() {
        storesSteps.deleteStore(storeID).statusCode(200);
    }

    @And("^User should verify if the store is deleted$")
    public void userShouldVerifyIfTheStoreIsDeleted() {
        storesSteps.getStoreByID(storeID).statusCode(404);
    }

    @When("^User creates new store record with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCreatesNewStoreRecordWith(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours) {
        HashMap<Object,Object> serviceData = new HashMap<>();
        response = storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours,serviceData);
        response.log().all().statusCode(201);
    }

    @And("^User can search new record using store \"([^\"]*)\"$")
    public void userCanSearchNewRecordUsingStore(String name) {
        HashMap<String,Object> storeRecord = storesSteps.getStoreInformationWithName(name);

        storeID = (int) storeRecord.get("id");
        Assert.assertThat(storeRecord,hasValue(name));
    }

    @And("^User can update new record using storeID, name, \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCanUpdateNewRecordUsingStoreIDName(String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours) {
        // HashMap<Object,Object> serviceData = new HashMap<>();
        name = name+ TestUtils.getRandomValue();
        storesSteps.updateStoreInfo(storeID,name,type,address);
    }

    @And("^User can delete new record using storeID$")
    public void userCanDeleteNewRecordOfStoreID() {
        storesSteps.deleteStore(storeID).log().all().statusCode(200);
    }

    @Then("^User is able to run successful CRUD operation on store details$")
    public void userIsAbleToRunSuccessfulCRUDOperationOnStoreDetails() {
        storesSteps.verifyStoreRecord(storeID).log().all().statusCode(404);
    }

}
