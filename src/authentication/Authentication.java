/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Collections.list;
import org.bson.Document;
import static resources.Constant.*;


/**
 *
 * @author Divyanshu
 */
public class Authentication {
    
     private MongoDatabase auth ;
    private MongoCollection student =null, teacher=null;
     private  String pwd;
     private MongoClient mongoClient ;
    public Authentication(){
        
            MongoClientURI uri = new MongoClientURI("mongodb://192.168.0.3:27017");
            mongoClient = new MongoClient(uri);
            auth = mongoClient.getDatabase("authentication");
            
}
    
    public int signUpUser(String firstname, String lastname, String email, String password, String user){
                    
                    String pwd = password ;  //encrypt here
                    if(user.equals("student")){
                               student = auth.getCollection("student");
                               System.out.println("Student Connected");
                               Document newUser =new Document("firstname", firstname)
                                                   .append("lastname",lastname)
                                                   .append("email", email)
                                                   .append("password", pwd);
                                student.insertOne(newUser);
                                return RESULT_OK;
                    }else if(user.equals("teacher")){
                                teacher = auth.getCollection("teacher");
                                System.out.println("Teacher Connected");
                                Document newUser =new Document("firstname", firstname)
                                                   .append("lastname",lastname)
                                                   .append("email", email)
                                                   .append("password", pwd);
                                teacher.insertOne(newUser);
                                return RESULT_OK;
                    }
                    return RESULT_NOK;
 }
             
    /**
     *
     * @param email gets the userEmail  
     * @param password
     * @param user IS either student or teacher
     * @return
     */
    public int signInUser(String email, String password, String user){
                    System.out.println(user);
                    String pwd = password ;  //encrypt here
                    long c =0 ;
                    if(user.equals("student")){
                                student = auth.getCollection("student");
                                 c = student.count(and(eq("email",email), eq("password", pwd)));
                                 System.out.println(c+" Signin");
   }
                               
                     else if(user.equals("teacher")){
                                teacher = auth.getCollection("teacher");
                                 c =teacher.count(and(eq("email",email), eq("password", pwd)));
       }
                   if(c==1)
                       return RESULT_OK;
                   else
                       return RESULT_NOK;
    }
    public void close(){
        mongoClient.close();
        
    }
    
    
   }
