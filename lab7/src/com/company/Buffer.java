package com.company;

import java.io.*;

public class Buffer {

    private static byte[] toBytes(Stock stock) throws IOException {

        ByteArrayOutputStream aos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(aos);
        out.writeObject(stock);
        out.flush();

        return aos.toByteArray();
    }

    private static Stock fromBytes(byte[] bytes)  throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        Stock object = (Stock) in.readObject();
        return object;
    }

    static long[] write(Stock[] accounts) throws IOException {

        long[] product = new long[accounts.length];
        RandomAccessFile file = new RandomAccessFile("Stock.txt", "rw");
        int i = 0;
        for (Stock object : accounts) {
            long result = file.length();
            file.seek(result);
            product[i] = file.getFilePointer();
            i++;
            file.writeInt(toBytes(object).length);
            file.write(toBytes(object));
            file.setLength( file.getFilePointer());
        }
        return product;
    }

    static void read (long [] pointers) throws IOException, ClassNotFoundException {
        RandomAccessFile file = new RandomAccessFile("Stock.txt", "rw");
        for (int i = 0; i < pointers.length; i++)
        {
            long objectPointer = pointers[i];
            file.seek(objectPointer);
            byte [] buffer = new byte [file.readInt()];
            file.read(buffer);
            System.out.println(fromBytes(buffer));
        }
    }
}
