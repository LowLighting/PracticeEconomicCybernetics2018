package com.company;
import java.io.Serializable;

public abstract class PassengerCarrier extends Freightcarrier implements Serializable {

    public String toString() {
        String outputc = String.format("%.2f", getCost());
        String outputt = String.format("%.2f", getTime());
        return message.getString("Cost") + "(USD): " + outputc +
                "; " + message.getString("Time") + "(" + message.getString("h") + "): " + outputt + "; " + message.getString("Type") + " : " + getType().toString() + "; " + message.getString("Distance") + "(" + message.getString("km") + "): " + getDistance() + "; " + message.getString("Info") + " : " + getInfo() + "; " + message.getString("Date") + " : " + getCreationDate();
    }

    public PassengerCarrier() {}

    protected PassengerCarrier( double distance, Type type ) throws Exception {
        if (distance > 0) {
            setDistance(distance);
            setType(type);
            }
        else
           throw new Exception("Incorrect fields");
    }
}
