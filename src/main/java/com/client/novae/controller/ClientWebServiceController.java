package com.client.novae.controller;

import com.client.novae.request.CreditCardRequest;
import com.client.novae.response.CreditCardResponse;
import com.client.novae.util.ClientUrlEnum;
import com.client.novae.util.ClientWebServiceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/client/creditCard")
@Validated
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientWebServiceController {
    private Logger logger = LogManager.getLogger(ClientWebServiceUtil.class);

    @Value("${url.service.novae}")
    private String URL_IP;

    @Autowired
    ClientWebServiceUtil clientWebUtil;

    @PostMapping
    public ResponseEntity<CreditCardResponse> create(@Validated @RequestBody CreditCardRequest request) {
        try {
            String responseStr = clientWebUtil.clientWebServicePost(URL_IP, request).readEntity(String.class);
            CreditCardResponse response = clientWebUtil.transformStrToJson(responseStr);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<CreditCardResponse>> findAll() {
        String responseStr = clientWebUtil.clientWebServiceGetList(URL_IP);
        JSONArray responseJson = new JSONArray(responseStr);
        List<CreditCardResponse> responseList = new ArrayList<>();
        responseJson.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            CreditCardResponse response = new CreditCardResponse();
            response.setId(Long.parseLong(obj.get("id").toString()));
            response.setCardHolderName(obj.get("cardHolderName").toString());
            response.setCardNumber(obj.get("cardNumber").toString());
            response.setBillingAddress(obj.get("billingAddress").toString());
            response.setMaskNumber(obj.get("maskNumber").toString());
            response.setCvv(obj.get("cvv").toString());
            response.setExpireDate(obj.get("expireDate").toString());
            responseList.add(response);
        });
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> findById(@PathVariable Long id) {
        try {
            String responseStr = clientWebUtil.clientWebServiceGetPath(URL_IP, id);
            if (responseStr != null) {
                CreditCardResponse response = clientWebUtil.transformStrToJson(responseStr);
                return ResponseEntity.ok(response);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreditCardResponse> update(@PathVariable Long id, @Validated @RequestBody CreditCardRequest request) {
        try {
            String responseStr = clientWebUtil.clientWebServicePut(URL_IP + ClientUrlEnum.UPDATE_CARD.getValue(), id, request).readEntity(String.class);
            CreditCardResponse response = clientWebUtil.transformStrToJson(responseStr);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        try {
            String responseStr = clientWebUtil.clientWebServiceDelete(URL_IP + ClientUrlEnum.DELETE_CARD.getValue(), id).readEntity(String.class);
            return ResponseEntity.ok(Long.parseLong(responseStr));
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

    @GetMapping("/getCard/{cardNumber}")
    public ResponseEntity<CreditCardResponse> findByNumber(@PathVariable("cardNumber") String cardNumber) {
        try {
            String responseStr = clientWebUtil.clientWebServiceGetPath(URL_IP + ClientUrlEnum.GET_CARD_BY_NUMBER.getValue(), cardNumber);
            CreditCardResponse response = clientWebUtil.transformStrToJson(responseStr);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

}
