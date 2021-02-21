package com.client.novae.util;

public enum ClientUrlEnum {

    UPDATE_CARD("/update/"),
    DELETE_CARD("/delete/"),
    GET_CARD_BY_NUMBER("/getCard/");

    public final String value;

    ClientUrlEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
