package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Buffer {
    static Stock moveLow(Object index, Map<Object, Long> map) {
        Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length - 1; i++) {
            if (objects[i].equals(index)) {
                try (RandomAccessFile randomAccessFile = new RandomAccessFile("Stock.txt", "rw")) {
                    int j = i - 1;
                    randomAccessFile.seek(map.get(objects[j]));
                    byte[] b = new byte[1024];
                    randomAccessFile.read(b);
                    Stock deserialize = deserialize(b);
                    while (Objects.requireNonNull(deserialize).isDeleted()) {
                        ++j;
                        if (j >= objects.length) return null;
                        randomAccessFile.seek(map.get(objects[j]));
                        byte[] array = new byte[1024];
                        randomAccessFile.read(array);
                        deserialize = deserialize(array);
                    }
                    return deserialize;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    static Stock moveUp(Object index, Map<Object, Long> map) {
        Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(index)) {
                try (RandomAccessFile randomAccessFile = new RandomAccessFile("Stock.txt", "rw")) {
                    int j = i + 1;
                    randomAccessFile.seek(map.get(objects[j]));
                    byte[] b = new byte[1024];
                    randomAccessFile.read(b);
                    Stock record = deserialize(b);
                    while (Objects.requireNonNull(record).isDeleted()) {
                        --j;
                        if (j < 0) {
                            return null;
                        }
                        randomAccessFile.seek(map.get(objects[j]));
                        byte[] array = new byte[1024];
                        randomAccessFile.read(array);
                        record = deserialize(array);
                    }
                    return record;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    static void removeByIndex(Object index, Map<Object, Long> users) {
        for (var entry : users.entrySet()) {
            if (entry.getKey().equals(index)) {
                try (RandomAccessFile randomAccessFile = new RandomAccessFile("Stock.txt", "rw")) {
                    randomAccessFile.seek(entry.getValue());
                    byte[] array = new byte[1024];
                    randomAccessFile.read(array);
                    Stock record = deserialize(array);
                    Objects.requireNonNull(record).setDeleted(true);
                    randomAccessFile.seek(entry.getValue());
                    randomAccessFile.write(Objects.requireNonNull(serialize(record)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static Map<Object, Long> write(ArrayList<Stock> magazine) {
        Map<Object, Long> map = new HashMap<>();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("Stock.txt", "rw")) {
            for (Stock record : magazine) {
                long dataPointer = randomAccessFile.getFilePointer();
                map.put(record.getField(), dataPointer);
                randomAccessFile.write(Objects.requireNonNull(serialize(record)));
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    static Stock read(Object index, Map<Object, Long> magazine) {
        Long dataPointer = magazine.get(index);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("Stock.txt", "rw")) {
            randomAccessFile.seek(dataPointer);
            byte[] array = new byte[1024];
            randomAccessFile.read(array);
            Stock record = deserialize(array);
            if (Objects.requireNonNull(record).isDeleted()) {
                return null;
            }
            return deserialize(array);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] serialize(Stock record) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(record);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Stock deserialize(byte[] byteArray) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray))) {
            Stock record = (Stock) ois.readObject();
            return record;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
