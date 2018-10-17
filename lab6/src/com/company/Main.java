package com.company;
import java.util.Locale;
import java.util.ResourceBundle;
public class Main {
    public static void main(String[] args) {
            try {
                Locale.setDefault(new Locale(args[0], args[1]));

                ResourceBundle message = ResourceBundle.getBundle("MSG",new Locale(args[0], args[1]));

                Aircarrier transport = new Aircarrier( "Minsk",5300);
                transport.setInfo( "Boing 747" );
                MovingCar m = new MovingCar( "Minsk",2300 );
                m.setInfo( "Renault Trafic" );
                System.out.println(m.toString());

                Aircarrier k = new Aircarrier( "Cair",16000 );
                k.setInfo("Boing-747");
                System.out.println(k.toString());

                Taxi taxi = new Taxi( 200);

                Connector con = new Connector("Freightcarrier.dat");

                con.write(transport);

                Aircarrier trans =con.read(Aircarrier.class);
                System.out.println(message.getString("TheTransport"));
                    trans.setMessage();
                    System.out.println( trans );
                Connector connector = new Connector("PassengerCarrier.dat");
                connector.write(taxi);
                Taxi passenger=connector.read(Taxi.class);
                System.out.println(message.getString("ThePassenger"));
                passenger.setMessage();
                System.out.println( passenger );
            }
            catch ( Exception e ) {
                System.err.println(e);
            }
        }
    }
