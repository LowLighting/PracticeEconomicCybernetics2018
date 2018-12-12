package Lab8;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.lang.reflect.Field;

public class Stock implements Serializable {
    private Date date = new Date();
    private int numberstock;
    private double code;
    private String nameproduct;
    private int shelflife;
    private int numberofitems;
    private double price;
    private static String field = "code";
    private boolean deleted = false;

    public Stock(int numberStock, double code, String nameProduct, int shelfLife, int numberOfItems, double price) {
        this.numberstock = numberStock;
        this.code = code;
        this.nameproduct = nameProduct;
        this.shelflife = shelfLife;
        this.numberofitems = numberOfItems;
        this.price = price;
    }
    public Stock(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
        if (stringTokenizer.hasMoreTokens()) {

            this.numberstock = Integer.parseInt(stringTokenizer.nextToken());
            this.code = Double.parseDouble(stringTokenizer.nextToken());
            this.nameproduct = stringTokenizer.nextToken();
            this.shelflife = Integer.parseInt(stringTokenizer.nextToken());
            this.numberofitems = Integer.parseInt(stringTokenizer.nextToken());
            this.price = Double.parseDouble(stringTokenizer.nextToken());
        }
    }
    public void setCode(double code) {
        this.code = code;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public void setNumberstock(int numberstock) {
        this.numberstock = numberstock;
    }

    public void setNumberofitems(int numberofitems) {
        this.numberofitems = numberofitems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShelflife(int shelflife) {
        this.shelflife = shelflife;
    }

    public double getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberofitems() {
        return numberofitems;
    }

    public int getNumberstock() {
        return numberstock;
    }

    public int getShelflife() {
        return shelflife;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setField(String field1) {
        field = field1;
    }

    Object getField() throws ReflectiveOperationException {
        Field f = this.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return f.get(this);
    }

    boolean isDeleted() {
        return deleted;
    }

    void setDeleted() {
        this.deleted = true;
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
                outprice+"$";
    }


}