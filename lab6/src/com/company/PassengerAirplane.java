package com.company;
import java.io.Serializable;
public class PassengerAirplane extends PassengerCarrier implements Serializable {
    private static final long serialVersionUID = 1L;

    public PassengerAirplane(double distance ) throws Exception{
        super( distance, PassengerCarrier.Type.Aircraft);
        setTime ( distance / 900);
        setCost(  getTime() * 792.45);
    }

    public PassengerAirplane() {}
}
