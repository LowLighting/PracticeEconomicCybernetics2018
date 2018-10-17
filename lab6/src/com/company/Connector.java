package com.company;
import java.io.*;
import java.util.ResourceBundle;

public class Connector {
    private String filename1;

    public Connector( String filename1 ) {
        this.filename1 = filename1;
    }

    public <T> void write (T m) throws IOException {
        FileOutputStream fos = new FileOutputStream (filename1);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {

            oos.writeObject(m);
            oos.flush();
        }
}

    public <T> T read(Class<T> klass) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename1);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            T result = klass.cast(oin.readObject());
            return (T) result;
        }
    }
}
