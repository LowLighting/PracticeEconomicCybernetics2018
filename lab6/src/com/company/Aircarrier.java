package com.company;
import java.io.Serializable;
public class Aircarrier extends Freightcarrier implements Serializable {
    private static final long serialVersionUID = 1L;

    public Aircarrier(String city,double distance )throws Exception {
        super(city,distance, Freightcarrier.Type.Aircraft);
        setTime( distance / 90);
        setCost( 10 * getTime() * 793);
    }

    public Aircarrier() {}
}
