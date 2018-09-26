package lab_2_var_1; 
public class Main { 

public static void main(String[] args) { 
try { 
int n = 9; 
Vector[] m = new Vector[n]; 
for (int i = 0; i < n; i++) { 
m[i] = new Vector(); 
m[i].print(); 
System.out.println(); 
} 
for(int i=0;i<n-2;i++) { 
for (int j = i + 1; j < n - 1; j++) { 
for (int k = j + 1; k < n; k++) { 
Vector.coplanar(m[i], m[j], m[k]); 
System.out.println(); 
} 
} 
} 
} 
catch (Exception e) 
{ 
System.err.println(e.getMessage()); 
System.exit(1); 
} 
} 
}
