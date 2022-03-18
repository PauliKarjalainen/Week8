package com.example.week8;


public class Bottle {
    private double total_energy = 0.3;
    private double price = 1.00;
    private double size = 0.5;
    private String name = "Pepsi Max";
    private String manufacturer = "Pepsi";




    public Bottle(String n, String m, double ikoko, double ihinta){
        name = n;
        manufacturer = m;
        total_energy = ikoko;
        price = ihinta;
    }

    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public double getEnergy(){
        return total_energy;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return total_energy;
    }

    public String toString(){
        String s = name + ", " + total_energy + "l, " + price + "€";
        return s;
    }
}