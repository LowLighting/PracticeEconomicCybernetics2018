package com.company;
import java.io.Serializable;
import java.util.Date;
import java.text.*;

public class Stock implements Serializable{
    private Date date = new Date();
    private int numberstock;
    private double code;
    private String nameproduct;
    private int shelflife;
    private int numberofitems;
    private double price;

    public Stock(int numberStock, double code, String nameProduct, int shelfLife, int numberOfItems, double price) {
        this.numberstock = numberStock;
        this.code = code;
        this.nameproduct = nameProduct;
        this.shelflife = shelfLife;
        this.numberofitems = numberOfItems;
        this.price = price;
    }

    public String toString() {

        String outprice = String.format("%.2f", price);
        SimpleDateFormat dateFormat =  new SimpleDateFormat("dd.MM.yyyy");

        return numberstock + " | " +
                code + " | " +
                nameproduct + " | " +
                dateFormat.format( date ) + " | " +
                shelflife + " | " +
                numberofitems + " | " +
                outprice;
    }
}
