package com.example.week8;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Locale;

public class BottleDispenser {


    private static BottleDispenser automaatti = new BottleDispenser();


    ArrayList<Bottle> listBottles = new ArrayList<>();

    private float balance;

    static BottleDispenser getInstance() {

        return automaatti;
    }

    private BottleDispenser() {

        balance = 0;

        listBottles.add(new Bottle("Pepsi Max", "Pepsi", 0.5, 1.8));
        listBottles.add(new Bottle("Pepsi Max", "Pepsi", 1.5, 2.2));
        listBottles.add(new Bottle("Sprite", "Sprite", 0.5, 1.8));
        listBottles.add(new Bottle("Sprite", "Sprite", 1.5, 2.2));
        listBottles.add(new Bottle("Sprite Zero", "Sprite", 0.5, 1.8));
        listBottles.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2.0));
        listBottles.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5));
        listBottles.add(new Bottle("Fanta Zero", "Coca-Cola", 0.5, 1.95));
    }

    String addMoney(float input) {

        input = input / 100;
        balance += input;
        return String.format(Locale.getDefault(), "Klink! Added %.2f!", input);
    }

    float getMoney(){
        return balance;
    }

    String buyBottle(Bottle choiced) {

        if(listBottles.size() == 0) {
            return "No bottles left!";
        }
        if(balance < choiced.getPrice()){
            return "Add money first!";
        }else {
            listBottles.remove(choiced);
            balance -= choiced.getPrice();
            String n = "KACHUNK! " + choiced.getName() + " came out of the dispenser!";
            return n;
        }
    }


    @SuppressLint("DefaultLocale")
    String returnMoney() {
        String n = String.format("Klink klink. Money came out! You got %,.2f€ back!", balance);
        balance = 0;
        return n;
    }

}