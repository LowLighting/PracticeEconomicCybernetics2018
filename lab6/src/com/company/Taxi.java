package com.company;
import java.io.Serializable;
public class Taxi extends PassengerCarrier implements Serializable{
    private static final long serialVersionUID = 1L;

    public Taxi(double distance ) throws Exception{
        super( distance, PassengerCarrier.Type.Car);
        setTime( distance / 90);
        setCost( 100 * getTime()  / 10);
    }

    public Taxi() {}
}
