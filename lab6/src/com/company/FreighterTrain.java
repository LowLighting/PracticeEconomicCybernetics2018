package com.company;

import java.io.Serializable;

public class FreighterTrain extends Freightcarrier implements Serializable{
        private static final long serialVersionUID = 1L;

        public FreighterTrain(String city,double distance )throws Exception {
            super(city,distance, Freightcarrier.Type.Train);
            setTime( distance / 100);
            setCost( 200* getTime());
        }
        public FreighterTrain() {}
    }
