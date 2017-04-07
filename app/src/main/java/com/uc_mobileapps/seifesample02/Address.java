package com.uc_mobileapps.seifesample02;

import com.weebmeister.seife.anno.SeifeEmbeddable;
import com.weebmeister.seife.anno.SeifeField;

/**
 * Created by Klaus on 04.04.2017.
 */
@SeifeEmbeddable
public class Address {

    @SeifeField
    private String houseNumber;

    @SeifeField
    private String street;

    @SeifeField
    private String zipcode;

    @SeifeField
    private String city;

    @SeifeField
    private String state;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
