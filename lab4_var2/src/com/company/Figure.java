package com.company;

import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.Consumer;

abstract class Figure {
    // абстрактный класс фигура
// абстрактный метод для получения периметра
        public abstract double Perimeter();
// абстрактный метод для получения площади
        public abstract double Area();

    public abstract int compareTo(Trapezium a);
}
    // производный класс прямоугольника
    class Trapezium extends Figure implements Iterator<Object>, Comparable<Trapezium>
    {
        public  enum Cmp {widthup, widthlow, edge1,edge2,height}
        private Cmp compareBy;
        public Cmp str_to_comparator(String str) throws Exception
        {
            switch (str)
            {
                case "widthup":
                    return Cmp.widthup;
                case "widthlow":
                    return Cmp.widthlow;
                case "edge1":
                    return Cmp.edge1;
                case "edge2":
                    return Cmp.edge2;
                case "height":
                    return Cmp.height;
                    default:
                        throw new Exception("Невозможно преобразоватьисходную строку в Cmp");
            }
        }
        @Override
        public int compareTo(Trapezium a) {
            switch (compareBy){
                case widthup:
                    if(widthup>a.get_widthup())
                        return 1;
                    else if (widthup<a.get_widthup())
                        return -1;
                    return 0;
                case widthlow:
                    if(widthlow>a.get_widthlow())
                        return 1;
                    else if (widthlow<a.get_widthlow())
                        return -1;
                    return 0;
                case edge1:
                    if(edge1>a.get_edge1())
                        return 1;
                    else if (edge1<a.get_edge1())
                        return -1;
                    return 0;
                case edge2:
                    if(edge2>a.get_edge2())
                        return 1;
                    else if (edge2<a.get_edge2())
                        return -1;
                    return 0;
                case height:
                    if(height>a.get_height())
                        return 1;
                    else if (height<a.get_height())
                        return -1;
                    return 0;
                    default:
                        return 0;
            }
        }
        private int iter=0;
        private double  widthup;
        private double widthlow;
        private double edge1;
        private double edge2;
        private double height;
        public double get_widthup(){return widthup;}
        public double get_widthlow(){return widthlow;}
        public double get_edge1(){return edge1;}
        public double get_edge2(){return edge2;}
        public double get_height(){return height;}
        public void set_CompareBy(Cmp compareBy){this.compareBy= compareBy;}
        // конструктор с обращением к конструктору класса Figure
        public boolean Trapezium(Point a , Point b,Point c,Point d) {
            Point[] m = new Point[4];
            m[0] = a;
            m[1] = b;
            m[2] = c;
            m[3] = d;
            for (int i = 0; i < 3; i++)
                for (int j = i + 1; j < 4; j++)
                    if (!Point.compare(m[i], m[j]))
                        return false;
            for (int i = 0; i < 4; i++)
                for (int j = 3; j > i; j--)
                    if (m[j - 1].x > m[j].x) {
                        Point tmp = m[j - 1];
                        m[j - 1] = m[j];
                        m[j] = tmp;
                    }
            if (m[0].y > m[1].y) {
                Point tmp = m[0];
                m[0] = m[1];
                m[1] = tmp;
            }
            if (m[2].y > m[3].y) {
                Point tmp = m[2];
                m[2] = m[3];
                m[3] = tmp;
            }
            if (Point.kol(Point.vector(m[0], m[1]), Point.vector(m[2], m[3])) || Point.kol(Point.vector(m[0], m[2]), Point.vector(m[1], m[3])))
                return true;
            else if(Point.kol(Point.vector(m[0], m[1]), Point.vector(m[2], m[3])) && Point.kol(Point.vector(m[0], m[2]), Point.vector(m[1], m[3])))
                return false;
            else
                return false;
        }
        Trapezium(Point a , Point b,Point c,Point d) throws Exception
        {
            if(Trapezium(a,b,c,d))
            {
                Point[] k=new Point [4];
                k[0]=a;
                k[1]=b;
                k[2]=c;
                k[3]=d;
                for (int i=0; i<4;i++)
                    for (int j=3; j>i; j--)
                        if (k[j-1].x>k[j].x)
                        {
                            Point tmp = k[j-1];
                            k[j-1]=k[j];
                            k[j]=tmp;
                        }
                        if (k[0].y>k[1].y)
                        {
                            Point tmp = k[0];
                            k[0]=k[1];
                            k[1]=tmp;
                        }
                if (k[2].y>k[3].y)
                {
                    Point tmp = k[2];
                    k[2]=k[3];
                    k[3]=tmp;
                }
                boolean per=true;
                if (Point.kol(Point.vector(k[0],k[1]),Point.vector(k[2],k[3])))
                    per=false;
                if (per)
                {
                    Point tmp = k[1];
                    k[1]=k[2];
                    k[2]=tmp;
                }
                widthlow=Point.lenght(k[0],k[1]);
                widthup=Point.lenght(k[2],k[3]);
                edge1=Point.lenght(k[0],k[3]);
                edge2=Point.lenght(k[1],k[3]);
                height=Math.abs((k[0].y-k[1].y)*k[2].x-(k[0].x-k[1].x)*k[2].y+k[0].x*k[1].y-k[0].y*k[1].x)/Point.lenght(k[0],k[1]);

                compareBy=Cmp.height;
            }
            else
                throw new Exception("Точки не образуют трапецию");
        }
        Trapezium(String str) throws Exception{
            Point a= new Point();
            Point b= new Point();
            Point c= new Point();
            Point d= new Point();
            StringTokenizer strtok=new StringTokenizer(str);
            if(strtok.countTokens()!=8)
                throw new Exception("Неверная строка: Неверное число лексем");
                try{
                    a.x=Double.parseDouble(strtok.nextToken());
                    a.y=Double.parseDouble(strtok.nextToken());
                   }
            catch (Exception e)
            {
                throw new Exception("Неверная строка: Невозможно проинициализировать первую точку");
            }
            try{
                b.x=Double.parseDouble(strtok.nextToken());
                b.y=Double.parseDouble(strtok.nextToken());
            }
            catch (Exception e)
            {
                throw new Exception("Неверная строка: Невозможно проинициализировать вторую точку");
            }
            try{
                c.x=Double.parseDouble(strtok.nextToken());
                c.y=Double.parseDouble(strtok.nextToken());
            }
            catch (Exception e)
            {
                throw new Exception("Неверная строка: Невозможно проинициализировать третью точку");
            }
            try{
                d.x=Double.parseDouble(strtok.nextToken());
                d.y=Double.parseDouble(strtok.nextToken());
            }
            catch (Exception e)
            {
                throw new Exception("Неверная строка: Невозможно проинициализировать четвёртую точку");
            }
            Trapezium m=new Trapezium( a,b,c,d);
        }
        public double Perimeter()
        {
            return edge1 +edge2  + widthlow + widthup;
        }
        public double Area()
       {
            return  ((widthup+widthlow)/2*height);
       }
        @Override
        public boolean hasNext()
        {
            if (iter>4)
            {
                iter=0;
                return false;
            }
            else
                return true;
        }

        @Override
        public Object next() throws IndexOutOfBoundsException
        {
            if (iter>4)
                throw new IndexOutOfBoundsException("Выход за пределы массива");
            switch (iter)
            {
                case 0:
                    iter++;
                    return widthup;
                case 1:
                    iter++;
                    return widthlow;
                case 2:
                    iter++;
                    return edge1;
                case 3:
                    iter++;
                    return edge2;
                case 4:
                    iter++;
                    return height;
                    default:
                        return 0;
            }
        }

        @Override
        public void remove() {}
        public String to_String()
        {
            return ("Длинна верхнего основания- "+Double.toString(get_widthup())+"\n"+"Длинна нижнего основания- "+Double.toString(get_widthlow())+"\n"+"Длинна рёбер- "+Double.toString(get_edge1())+" ; "+Double.toString(get_edge2())+"\n"+"Высота- "+Double.toString(get_height())+"\n"+"Периметр- "+Double.toString(Perimeter())+"\n"+"Площадь- "+Double.toString(Area())+"\n") ;
        }
    }
