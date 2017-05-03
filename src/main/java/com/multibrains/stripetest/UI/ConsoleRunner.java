package com.multibrains.stripetest.UI;

import com.multibrains.stripetest.tools.ResultBuilder;
import com.multibrains.stripetest.tools.StripeTools;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;

import java.util.Scanner;


public class ConsoleRunner {
    private StripeTools stripeTools;

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
        System.out.println("Enter 4 for exit");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        switch (answer) {
            case "1":
                createCharge();
                showStartVariants();
                break;
            case "2":
                addCard();
                break;
            case "3":
                showCards();
                showStartVariants();
                break;
            case "4":
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
        Scanner in = new Scanner(System.in);
        System.out.println("Enter card number");
        cardNumber = in.nextLine();
        System.out.println("Enter  year");
        exp_year = in.nextLine();
        System.out.println("Enter month");
        exp_month = in.nextLine();
        System.out.println(" Enter cvc");
        cvv = in.nextLine();

        try {
            stripeTools.addCartToCustomer(cardNumber,exp_month,exp_year,cvv);
            System.out.println("Card successfully added");
        } catch (CardException | APIException| AuthenticationException | InvalidRequestException | APIConnectionException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());

        }
        finally {
            showStartVariants();
            in.close();
        }

    }
    public void showCards(){
        System.out.print(ResultBuilder.getCardsList(stripeTools.getCreditCards()));
    }
    public void createCharge(){
        System.out.println("Choose card for payment");
        showCards();
        Scanner in=new Scanner(System.in);
        int numberOfCard=0;
        long amount=0;
        try {
            numberOfCard=in.nextInt();
            System.out.println("Enter amount of payment");
           amount=in.nextLong();
        }catch (NumberFormatException e){
            System.out.println("Invalid number of card");
            in.close();
            showStartVariants();
        }
        System.out.println("Enter currency");
        String currency=in.nextLine();
        System.out.println("Enter payment description");
        String description=in.nextLine();

        try {
            Charge resultCharge=stripeTools.doCharge(amount,"usd",numberOfCard,description);
            System.out.println(resultCharge.getStatus());
        } catch (CardException | APIException | AuthenticationException | InvalidRequestException | APIConnectionException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

}
