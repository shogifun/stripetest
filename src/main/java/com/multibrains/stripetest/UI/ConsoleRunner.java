package com.multibrains.stripetest.UI;

import com.multibrains.stripetest.tools.ResultBuilder;
import com.multibrains.stripetest.tools.StripeTools;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;

import java.util.Scanner;


public class ConsoleRunner {
    private StripeTools stripeTools;
    private Scanner in=new Scanner(System.in);
    public ConsoleRunner() {
        try {
            stripeTools = new StripeTools();
            showStartVariants();
        } catch (CardException | APIException | AuthenticationException | InvalidRequestException | APIConnectionException
                e) {
            System.exit(-1);
        }

    }

    public void showStartVariants() {
        System.out.println("Enter 1 for creating charge");
        System.out.println("Enter 2 for adding card");
        System.out.println("Enter 3 for show your cards");
        System.out.println("Enter 4 for show charges");
        System.out.println("Enter 5 for exit");
        String answer = in.nextLine();
        switch (answer) {
            case "1":
                createCharge();
                //showStartVariants();
                break;
            case "2":
                addCard();

                //showStartVariants();
                break;
            case "3":
                showCards();
                showStartVariants();
                break;
            case "4":
                showCharges();
                showStartVariants();
                break;
            case "5":
                in.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
                showStartVariants();
                break;
        }
    }

    public void addCard() {
        String cardNumber;
        String exp_year;
        String exp_month;
        String cvv;
        System.out.println("Enter card number");
        cardNumber = in.nextLine();
        System.out.println("Enter  year");
        exp_year = in.nextLine();
        System.out.println("Enter month");
        exp_month = in.nextLine();
        System.out.println(" Enter cvc");
        cvv = in.nextLine();

        try {
            stripeTools.addCartToCustomer(cardNumber, exp_month, exp_year, cvv);
            System.out.println("Card successfully added");
        } catch (CardException | APIException | AuthenticationException | InvalidRequestException | APIConnectionException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            showStartVariants();

        }

    }

    public void showCards() {
        System.out.print(ResultBuilder.getCardsList(stripeTools.getCreditCards()));
    }

    public void createCharge() {
        if (stripeTools.getCustomer().getSources().getData().isEmpty()) {
            System.out.println("Credit card not found. You should add credit card first");
            showStartVariants();
        }
        System.out.println("Choose card for payment");
        showCards();
        int numberOfCard = 0;
        long amount = 0;
        try {
            numberOfCard = Integer.parseInt(in.nextLine());
            System.out.println("Enter amount of payment");
            amount = Long.parseLong(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of card");
            showStartVariants();
        }
        System.out.println("Enter currency");
        String currency = in.nextLine();
        System.out.println("Enter payment description");
        String description = in.nextLine();

        try {
            Charge resultCharge = stripeTools.doCharge(amount, currency, numberOfCard, description);
            System.out.println("Payment status " + resultCharge.getStatus());
        } catch (CardException | APIException | AuthenticationException | InvalidRequestException | APIConnectionException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally {
            showStartVariants();
        }

    }

    public void showCharges() {
        try {
            System.out.println(ResultBuilder.getChargeList(stripeTools.getChargesList()));
        } catch (CardException | APIException | AuthenticationException | InvalidRequestException | APIConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

}
