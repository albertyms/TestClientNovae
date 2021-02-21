package com.client.novae.util;

import com.client.novae.request.CreditCardRequest;
import com.client.novae.response.CreditCardResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class ClientWebServiceUtil {
    private Logger logger = LogManager.getLogger(ClientWebServiceUtil.class);

    public String clientWebServiceGetPath(String url, Object param) {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(url).path(param instanceof Long ? String.valueOf((Long) param) : (String) param)
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            logger.error("Error: ", e);
            return null;
        }
    }

    public String clientWebServiceGetList(String url) {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } catch (Exception e) {
            logger.error("Error: ", e);
            return null;
        }
    }

    public Response clientWebServicePost(String url, CreditCardRequest object) {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(url).request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(object, MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error("Error: ", e);
            return null;
        }
    }

    public Response clientWebServicePut(String url, Long id, CreditCardRequest request) {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(url).path(String.valueOf(id))
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(request, MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            logger.error("Error: ", e);
            return null;
        }
    }

    public Response clientWebServiceDelete(String url, Long id) {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget baseTarget = client.target(url);
            WebTarget target = baseTarget.path(Long.toString(id));
            return target.request().delete();
        } catch (Exception e) {
            logger.error("Error: ", e);
            return null;
        }
    }

    public CreditCardResponse transformStrToJson(String objectStr) {
        JSONObject responseJson = new JSONObject(objectStr);
        CreditCardResponse response = new CreditCardResponse();
        response.setId(Long.parseLong(responseJson.get("id").toString()));
        response.setCardHolderName(responseJson.get("cardHolderName").toString());
        response.setCardNumber(responseJson.get("cardNumber").toString());
        response.setBillingAddress(responseJson.get("billingAddress").toString());
        response.setMaskNumber(responseJson.get("maskNumber").toString());
        response.setCvv(responseJson.get("cvv").toString());
        response.setExpireDate(responseJson.get("expireDate").toString());
        return response;
    }

}
