package com.company;
import java.io.Serializable;
public class PassengerTrain extends PassengerCarrier implements Serializable{
    private static final long serialVersionUID = 1L;

    public PassengerTrain(double distance ) throws Exception {
        super( distance, PassengerCarrier.Type.Train);
        setTime( distance / 120);
        setCost(  20 * getTime());
    }

    public PassengerTrain() {}
}
