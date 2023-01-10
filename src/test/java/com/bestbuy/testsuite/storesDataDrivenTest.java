package com.bestbuy.testsuite;

import com.bestbuy.bestbuyinfo.StoresSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;


@Concurrent(threads = "2x")//is working like core cpu speed (System information)
@UseTestDataFrom("src/test/java/resources/testdata/stores.csv")
@RunWith(SerenityParameterizedRunner.class)
public class storesDataDrivenTest extends TestBase {
    static String name;
    static String type;
    static String address;
    static String address2;
    static String city;
    static String state;
    static String zip;
    static double lat;
    static double lng;
    static String hours;


    @Steps
    StoresSteps storesSteps;

    @Title("Data driven test for adding multiple stores to the application")
    @Test
    public void createMultipleStores(){
        storesSteps.createNewStore(name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(201);
    }
}
