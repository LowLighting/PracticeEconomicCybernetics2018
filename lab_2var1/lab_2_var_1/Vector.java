package lab_2_var_1; 
import java.util.Random; 
public class Vector { 
int a1; 
int a2; 
int a3; 
public Vector() 
{ 
Random rnd = new Random(); 
int temp = rnd.nextInt(); 
a1 =Math.abs( temp % (5 + 1)); 
temp = rnd.nextInt(); 
a2 = Math.abs(temp % (5+1)); 
temp = rnd.nextInt(); 
a3 =Math.abs( temp % (5+1)); 
} 
public Vector(int a,int b,int c) { 
a1=a; 
a2=b; 
a3=c; 
} 
public void print() 
{ 
System.out.print("("+ a1+","+a2+","+a3+"); "); 
} 
public static void coplanar(Vector p1,Vector p2,Vector p3) 
{ 
int det; 
det=p1.a1*(p2.a2*p3.a3-p3.a2*p2.a3)-p1.a2*(p2.a1*p3.a3-p3.a1*p2.a3)+p1.a3*(p2.a1*p3.a2-p3.a1*p2.a2); 
if(det==0) 
{ 
System.out.print("Тройка векторов "); 
p1.print(); 
p2.print(); 
p3.print(); 
System.out.print(" компланарна"); 
} 
else 
{ 
System.out.print("Тройка векторов "); 
p1.print(); 
p2.print(); 
p3.print(); 
System.out.print(" некомпланарна"); 
} 
} 
}