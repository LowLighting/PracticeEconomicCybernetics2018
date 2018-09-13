package com.company;
// вариан 2
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static String rem(String s)
    {
        String r = "";
        int i = 0;
        while (i != s.length())
        {
            if (s.charAt(i) == '.')
            {
                if (Character.isDigit(s.charAt(i + 1)) && Character.isDigit(s.charAt(i + 2)))
                {
                    r += s.substring(i, i + 3);
                    i += 4;
                    while (i<s.length() && Character.isDigit(s.charAt(i)))
                        i++;
                    i--;

                }
                else
                    r += s.charAt(i);

            }
            else {
                r += s.charAt(i);
            }
            i++;
        }
        return r;
    }
        public static void main (String[]args){
            Scanner input;
            File file = new File("t.txt");
            try {

                input = new Scanner(file);
                while (input.hasNextLine()) {
                    String str1 = input.nextLine();
                    System.out.println(rem(str1));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

