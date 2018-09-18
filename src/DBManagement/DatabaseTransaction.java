
package DBManagement;

import java.sql.*;



public class DatabaseTransaction {
    
    private static Connection conn=null;
    private static  String connString ="jdbc:mysql://localhost:3306/squiz";
    private static  ResultSet rs= null;
public static void registerUser(String firstname, String lastname, String email, String password, String user) 
{  
   
        try {
    conn = DriverManager.getConnection(connString,"root","root");
    if(conn!=null){
        System.out.println("Connected");
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(password);
        
    String regUser = "INSERT INTO signup"+user+"(firstname, lastname, email, password) VALUES(?,?,?,?);" ;
    PreparedStatement ps = conn.prepareStatement(regUser);
   ps.setString(1,firstname);
   ps.setString(2,lastname);
   ps.setString(3,email);
   ps.setString(4,password);
    
    
   ps.executeUpdate();
   
 
    conn.close();
    }
    } catch (SQLException e){
        System.out.println("Error in Signup");
    }  
       
}

public static void signinUser(String email, String password, String user){
    
    try {
    conn = DriverManager.getConnection(connString,"root","root");
    if(conn!=null){
        
       
    String signinUser = "SELECT email, password FROM signup"+user+" WHERE email='"+email+"' AND password='"+password+"'" ;
   PreparedStatement ps = conn.prepareStatement(signinUser, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
   rs = ps.executeQuery();
 

      if(rs.next()==true) 
       System.out.println("LOGIN SUCCESSFUL");
      else 
       System.out.println("LOGIN UNSUCCESSFUL");

   conn.close();
   rs.close();
    }
    } catch (SQLException e){
        System.out.println("Error in Signup");
    }  
    
}




}

   
       

