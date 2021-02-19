package com.client.novae.util;

public enum ClientUrlEnum {

    URL_IP("http://localhost:8081/api/creditCard"),
    UPDATE_CARD("/update/"),
    DELETE_CARD("/delete/"),
    GET_CARD_BY_NUMBER("/getCard/");

    public final String value;

    private ClientUrlEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
