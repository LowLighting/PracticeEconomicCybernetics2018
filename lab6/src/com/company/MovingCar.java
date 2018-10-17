package com.company;
import java.io.Serializable;
public class MovingCar extends Freightcarrier implements Serializable{ ;
    private static final long serialVersionUID = 1L;

    public MovingCar(String city,double distance )throws Exception {
        super(city,distance, Freightcarrier.Type.Car);
        setTime( distance / 60);
        setCost( 50 * getTime() * 45 / 100);
    }

    public MovingCar() {}
}
