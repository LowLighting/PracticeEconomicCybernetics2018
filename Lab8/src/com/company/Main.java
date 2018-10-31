package com.company;

import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws ParseException {
        ArrayList<Stock> magazine = new ArrayList<>();
        createArrayList(magazine);
        Map<Object,Long> map = Buffer.write(magazine);
        SortedMap sortedMap = new TreeMap(map);

        System.out.println("\nNot sorted Stock:");
        printMap(map);
        System.out.println("\nSort by key:(code)");
        printMap(sortedMap);

        System.out.println();
        System.out.println("Вывод объекта,находящегося ниже заданного");
        System.out.println(Buffer.moveLow(magazine.get(1).getCode(), map));
        System.out.println("Вывод объекта,находящегося выше заданного");
        System.out.println(Buffer.moveUp(magazine.get(1).getCode(), map));

        System.out.println("Remove by index");
        Buffer.removeByIndex(magazine.get(0).getCode(), map);
        for (var entry : map.entrySet()) {
            Stock record = Buffer.read(entry.getKey(),map);
            if (record != null) {
                System.out.println("Key: " + entry.getKey());
                System.out.println(Buffer.read(entry.getKey(), map));
            }
        }
    }

    private static void createArrayList(ArrayList<Stock> magazine) throws ParseException {
        Stock firstStock = new Stock(125, 1252, "Beef", 6, 120, 20);
        Stock secondStock = new Stock(125,1526,"Milk",7,190,3.4);
        Stock thirdStock = new Stock(125,156,"Cheese",18,100,12.6);

        magazine.add(firstStock);
        magazine.add(secondStock);
        magazine.add(thirdStock);
    }
    private static <K,V> void printMap(Map<K, V> map) {
        System.out.println("______________________________________");
        for(Map.Entry<K,V> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println(Buffer.read(entry.getKey(), (Map<Object, Long>) map));
        }
    }
}
