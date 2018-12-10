package com.company;

import java.util.StringTokenizer;

public class  Point  {
    public double x;
    public double y;
    public Point ()
    {
        x=0;
        y=0;
    }
    public Point (double a,double b)
    {
        x=a;
        y=b;
    }
    public static double lenght(Point a, Point b)
    {
        return(Math.pow(((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)),0.5));
    }
    static public Point vector(Point a, Point b)
    {
        return (new Point(b.x-a.x,b.y-a.y));
    }
    static public boolean kol(Point a, Point b)
    {
        if( (a.x*b.y - b.x*a.y)==0)
            return true;
        else
            return false;
    }
    static public boolean compare(Point a,Point b )
    {
        if((a.x!=b.x) || a.y!=b.y)
            return true;
        else
            return false;
    }
    @Override
    public String toString()
    {
        return (Double.toString(x)+", "+Double.toString(y));
    }
    Point(String str) throws Exception {
        StringTokenizer strtok = new StringTokenizer(str);
        if (strtok.countTokens() != 2)
            throw new Exception("Неверная строка: Неверное число лексем");
        try {
            x = Double.parseDouble(strtok.nextToken());
        } catch (Exception e) {
            throw new Exception("Неверная строка: Невозможно проинициализировать поле x");
        }
        try {
            y = Double.parseDouble(strtok.nextToken());
        } catch (Exception e) {
            throw new Exception("Неверная строка: Невозможно проинициализировать поле y");
        }
    }
}

