package com.multibrains.stripetest;

import com.multibrains.stripetest.UI.ConsoleRunner;
import com.multibrains.stripetest.tools.StripeTools;
import com.stripe.exception.*;

/**
 * Created by user on 03.05.2017.
 */
public class StartApp {
    public static void main(String[] args) {

        /*try {
            StripeTools tools=new StripeTools();
            tools.addCartToCustomer("4242424242424242","12","2019","123");
            tools.addCartToCustomer("5200828282828210","11","2020","563");
            tools.addCartToCustomer("4000000000000341","02","2018","999");
            //System.out.print(tools.getCustomer());

            tools.doCharge(9999999,"usd",0,"test charge");
            tools.doCharge(2200,"usd",1," second test");


        } catch (CardException e) {
            //e.printStackTrace();
            System.out.print(e.getMessage());
        } catch (APIException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        }
        //System.out.print(tools.getCustomer());
        ;*/
        ConsoleRunner runner=new ConsoleRunner();
    }
}
