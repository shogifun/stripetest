package com.multibrains.stripetest.Tools;

import com.multibrains.stripetest.tools.StripeTools;
import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by user on 04.05.2017.
 */
public class TestStripeTools {
    private StripeTools stripeTools;

    @Before
    public void initStripeTools() {
        try {
            stripeTools = new StripeTools();
            stripeTools.addCartToCustomer("4242424242424242", "12", "2020", "111");
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = CardException.class)
    public void testAddCardToCustomerWithInvalidYear() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        stripeTools.addCartToCustomer("4242424242424242", "12", "2000", "111");
    }

    @Test(expected = CardException.class)
    public void testAddCartToCustomerWithInvalidMonth() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        stripeTools.addCartToCustomer("4242424242424242", "13", "2019", "111");
    }

    @Test(expected = CardException.class)
    public void testAddCartToCustomerWithInvalidCVC() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        stripeTools.addCartToCustomer("4242424242424242", "11", "2019", "aaa");
    }

    @Test(expected = InvalidRequestException.class)
    public void testDoChargeWithInvalidAmount() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        stripeTools.doCharge(2, "usd", 1, "test");
    }

    @Test(expected = InvalidRequestException.class)
    public void testDoChargeWithInvalidCurrencyName() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        stripeTools.doCharge(20000,"uss",1,"TEST");
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDoChargeWithInvalidCardNumber() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        stripeTools.doCharge(20000,"usd",-1,"test");

    }

}
