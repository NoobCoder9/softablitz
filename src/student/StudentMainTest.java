/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Divyanshu
 */
public class StudentMainTest {

public static void main(String args[])throws IOException{
           
    StudentHelper sh = new StudentHelper();
   /* ArrayList<String> a;
    a =sh.getSubjects();
    int i = a.size()-1;
   // System.out.println(a.get(0));
    while(i>=0){
        System.out.println(a.get(i));
        i--;
    }
         */
   String s = sh.getQuizId("Physics", "Water");
      System.out.println(s); 

    
    
}

}
