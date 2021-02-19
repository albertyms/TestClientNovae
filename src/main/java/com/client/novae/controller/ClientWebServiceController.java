package com.client.novae.controller;

import com.client.novae.request.CreditCardRequest;
import com.client.novae.response.CreditCardResponse;
import com.client.novae.util.ClientUrlEnum;
import com.client.novae.util.ClientWebServiceUtil;
import com.fasterxml.jackson.core.util.InternCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/client/creditCard")
@Validated
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientWebServiceController {
    private Logger logger = LogManager.getLogger(ClientWebServiceUtil.class);

    @Autowired
    ClientWebServiceUtil clientWebUtil;

    @PostMapping
    public ResponseEntity<CreditCardResponse> create(@Validated @RequestBody CreditCardRequest request) {
        try {
            String responseStr = clientWebUtil.ClientWebServicePost(ClientUrlEnum.URL_IP.getValue(), request).readEntity(String.class);
            CreditCardResponse response = clientWebUtil.transformStrToJson(responseStr);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<CreditCardResponse>> findAll() {
        String responseStr = clientWebUtil.ClientWebServiceGetList(ClientUrlEnum.URL_IP.getValue());
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
            String responseStr = clientWebUtil.ClientWebServiceGetPath(ClientUrlEnum.URL_IP.getValue(), id);
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
            String responseStr = clientWebUtil.ClientWebServicePut(ClientUrlEnum.URL_IP.getValue() + ClientUrlEnum.UPDATE_CARD.getValue(), id, request).readEntity(String.class);
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
            String responseStr = clientWebUtil.ClientWebServiceDelete(ClientUrlEnum.URL_IP.getValue() + ClientUrlEnum.DELETE_CARD.getValue(), id).readEntity(String.class);
            return ResponseEntity.ok(Long.parseLong(responseStr));
        } catch (Exception e) {
            logger.error("Error:", e);
            return null;
        }
    }

}
