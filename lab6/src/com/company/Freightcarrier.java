package com.company;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.*;

public abstract class Freightcarrier implements Serializable {
    private String city;
    public String getCity() {
        return city;
    }
    transient public ResourceBundle message = ResourceBundle.getBundle("MSG");

    public void setMessage() {
        this.message = ResourceBundle.getBundle("MSG");
    }

    private double time;
    private double distance;

    public final Date creationDate = new Date();

    public String getCreationDate() {
        DateFormat fmt= DateFormat.getTimeInstance(DateFormat.FULL, Locale.getDefault());
        String dateOut = fmt.format(creationDate);
        return dateOut;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    private double cost;

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public enum Type {Aircraft, Train,Car}

    private Type type;

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    private String info = "";

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String toString() {
        String outputc = String.format("%.2f", cost);
        String outputt = String.format("%.2f", time);
        return message.getString("City") + " : " + city +
                "; " + message.getString("Cost") + "(USD): " + outputc +
                "; " + message.getString("Time") + "(" + message.getString("h") + "): " + outputt + "; " + message.getString("Type") + " : " + type.toString() + "; " + message.getString("Distance") + "(" + message.getString("km") + "): " + distance + "; " + message.getString("Info") + " : " + info + "; " + message.getString("Date") + " : " + getCreationDate();
    }

    public Freightcarrier() { }


    protected Freightcarrier(String city, double distance, Type type) throws Exception {
        if (!((city.isEmpty()) ||(distance < 0))) {
            this.city = city;
            this.type = type;
            this.distance = distance;
        }
        else
            throw new Exception("Incorrect fields");
    }
}
