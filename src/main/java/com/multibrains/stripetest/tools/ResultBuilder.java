package com.multibrains.stripetest.tools;

import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03.05.2017.
 */
public class ResultBuilder {
    public static String getCardsList(List<ExternalAccount> cards){
        if(cards.isEmpty()){
            return "Credits cards not found"+"\n";
        }
        StringBuilder result=new StringBuilder();
        int i=1;

        for (ExternalAccount card:cards){

            result.append(i+". - " + ((Card)card).getBrand()+" **** **** **** " + ((Card)card).getLast4() +
            " exp "+((Card)card).getExpMonth()+"/"+((Card)card).getExpYear()+ "\n");
            i++;
        }
        return new String(result);
    }
}
