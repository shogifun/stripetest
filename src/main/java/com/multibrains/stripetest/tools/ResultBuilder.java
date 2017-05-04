package com.multibrains.stripetest.tools;

import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03.05.2017.
 */
public class ResultBuilder {
    public static String getCardsList(List<ExternalAccount> cards) {
        if (cards.isEmpty()) {
            return "Credits cards not found" + "\n";
        }
        StringBuilder result = new StringBuilder();
        int i = 1;

        for (ExternalAccount card : cards) {

            result.append(i + ". - " + ((Card) card).getBrand() + " **** **** **** " + ((Card) card).getLast4() +
                    " exp " + ((Card) card).getExpMonth() + "/" + ((Card) card).getExpYear() + "\n");
            i++;
        }
        return new String(result);
    }

    public static String getChargeList(List<Charge> charges) {
        if (charges.isEmpty()) {
            return "Charge list is empty";
        }
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Charge charge : charges) {
            result.append(i + ". Description " + charge.getDescription() + ", amount - " + charge.getAmount() + ", status " + charge.getStatus() +"\n");
            i++;
        }
        return new String(result);
    }
}
