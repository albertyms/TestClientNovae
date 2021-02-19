package com.client.novae.request;



import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class CreditCardRequest {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "cardHolderName")
    @NotNull(message = "cardHolderName cannot be null")
    private String cardHolderName;

    @JsonProperty(value = "cardNumber")
    @NotNull(message = "cardNumber Client cannot be null")
    private String cardNumber;

    @JsonProperty(value = "billingAddress")
    @NotNull(message = "billingAddress Client cannot be null")
    private String billingAddress;

    @JsonProperty(value = "expireDate")
    @NotNull(message = "expireDate Client cannot be null")
    private String expireDate;

    @JsonProperty(value = "cvv")
    @NotNull(message = "cvv Client cannot be null")
    private String cvv;

    @JsonProperty(value = "maskNumber")
    @NotNull(message = "maskNumber Client cannot be null")
    @NotBlank(message = "maskNumber Client cannot be null")
    private String maskNumber;

    public CreditCardRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getMaskNumber() {
        return maskNumber;
    }

    public void setMaskNumber(String maskNumber) {
        this.maskNumber = maskNumber;
    }
}
