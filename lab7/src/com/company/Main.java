package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File f = new File("Stock.txt");
        f.delete();
        Stock [] products = new Stock [4];
        products[0] = new Stock(1264,126.2,"Milk",5,1000,7.60);
        products[1] = new Stock(1268,280.1,"Salmon",20,100,30.50);
        products[2] = new Stock(1400,100,"Beef",8,160,15.50);
        products[3] = new Stock(1800,271,"Apple",9,300,7.25);

        try {
            long [] product = Buffer.write(products);
            Buffer.read(product);
        }

        catch (IOException e) {
            System.out.println(e);
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}