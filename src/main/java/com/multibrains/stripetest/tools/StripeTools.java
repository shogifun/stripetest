package com.multibrains.stripetest.tools;


import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StripeTools {
    private Customer customer;

    public List<ExternalAccount> getCreditCards() {
        return customer.getSources().getData();
    }

    public List<Charge> getChargesList() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> listParams = new HashMap<>();
        listParams.put("limit", 100);
        listParams.put("customer", customer.getId());
        return Charge.list(listParams).getData();
    }

    public StripeTools() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException, ArrayIndexOutOfBoundsException {
        Stripe.apiKey = "tGN0bIwXnHdwOa85VABjPdSn8nWY7G7I";
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("description", "test customer");
        customer = Customer.create(customerParams);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addCartToCustomer(String number, String exp_month, String exp_year, String cvv) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        customer = Customer.retrieve(customer.getId());
        Map<String, Object> params = new HashMap<>();

        params.put("source", createTokenForCard(number, exp_month, exp_year, cvv).getId());
        ExternalAccount card = customer.getSources().create(params);
        customer.getSources().getData().add(card);
        if (customer.getDefaultSource() == null) {
            customer.setDefaultSource(card.getId());
        }
    }

    public Token createTokenForCard(String number, String exp_month, String exp_year, String cvv) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> tokenParams = new HashMap<>();
        Map<String, Object> cardParams = new HashMap<>();

        cardParams.put("number", number);
        cardParams.put("exp_month", Integer.parseInt(exp_month));
        cardParams.put("exp_year",/*Integer.parseInt(exp_year)*/exp_year);
        cardParams.put("cvc", cvv);
        tokenParams.put("card", cardParams);

        Token token = Token.create(tokenParams);
        return token;
    }

    public Charge doCharge(long amount, String currency, int cardNumber, String description) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", currency);
        chargeParams.put("card", customer.getSources().getData().get(cardNumber - 1).getId());
        chargeParams.put("customer", customer.getId());
        chargeParams.put("description", description);
        Charge res = Charge.create(chargeParams);
        return res;
    }

}
