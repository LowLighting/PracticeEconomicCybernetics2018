package com.company;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    public static void main(String[] args)
    {
        try
        {
            Scanner in = new Scanner(System.in);
            Trapezium tr = new Trapezium(new Point(0,0),new Point(1,1),new Point(4,1),new Point(1,0));
            System.out.println("Ввывод полей через итератор");
           while (tr.hasNext())
                System.out.println(tr.next());
            System.out.print(tr.to_String());
            System.out.println();
            Trapezium[] trapeze = new Trapezium[4];
            trapeze[0]=new Trapezium(new Point(0,0),new Point(1,1),new Point(4,1),new Point(1,0));
            trapeze[1]=new Trapezium(new Point(0,0),new Point(1,1),new Point(4,1),new Point(1,0));
            trapeze[2]=new Trapezium(new Point(0,0),new Point(2,1),new Point(4,1),new Point(3,0));
            trapeze[3]=new Trapezium(new Point(0,0),new Point(1,1),new Point(3,1),new Point(2,0));
            for(int i=0;i<4;i++)
            {
                System.out.print(trapeze[i].to_String());
                System.out.println();
            }
            System.out.println("Выберете поле для сравнения\n1 — widthup\n2 — widthlow\n3 — edge1\n4 — edge2\n5 — height");
            int ch = in.nextInt();
            switch (ch)
            {
                case 1:
                    for (int i=0;i<4;i++)
                    {
                        trapeze[i].set_CompareBy(Trapezium.Cmp.widthup);
                    }
                    break;
                case 2:
                    for (int i=0;i<4;i++)
                    {
                        trapeze[i].set_CompareBy(Trapezium.Cmp.widthlow);
                    }
                    break;
                case 3:
                    for (int i=0;i<4;i++)
                    {
                        trapeze[i].set_CompareBy(Trapezium.Cmp.edge1);
                    }
                    break;
                case 4:
                    for (int i=0;i<4;i++)
                    {
                        trapeze[i].set_CompareBy(Trapezium.Cmp.edge2);
                    }
                    break;
                case 5:
                    for (int i=0;i<4;i++)
                    {
                        trapeze[i].set_CompareBy(Trapezium.Cmp.height);
                    }
                    break;
                default:
                    throw new Exception("Неверная команда");
            }
            System.out.println("Отсортированный массив");
            System.out.println();
            Arrays.sort(trapeze);
            for(int i=0;i<4;i++)
            {
                System.out.print(trapeze[i].to_String());
                System.out.println();
            }
            System.out.println("Введите строку для инициализации объкта\nФормат: число число ");
            in = new Scanner(System.in);
            String str = new String(in.nextLine());
            Trapezium posl3 = new Trapezium(str);
            System.out.println("Созданный объект:\n"+posl3.to_String());
        }
	    catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
