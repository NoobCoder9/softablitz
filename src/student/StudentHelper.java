/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class StudentHelper{
    
    private MongoDatabase dbquiz, dbSRecord ; 
    private MongoCollection<Document>  qCollection , qDetail; //Collection of Quizes and short description 
    
    
    
    public StudentHelper (){
       
        MongoClientURI uri = new MongoClientURI("mongodb://192.168.0.3:27017");
        MongoClient mongoClient = new MongoClient(uri);
        dbquiz = mongoClient.getDatabase("QUIZ");
        dbSRecord = mongoClient.getDatabase("STUDENT");
        qCollection = dbquiz.getCollection("quizCollection");  // contains all quiz
        qDetail = dbquiz.getCollection("quizDetail");  //contains subjects and topics
 }
    
    /**
     *   Get all the subjects present in Quiz 
     * @return list of All the subjects 
     */
    public ArrayList getSubjects(){
     
      ArrayList<String> subjects = new ArrayList<String>();
     FindIterable<Document> findIterable = qDetail.find(new Document());   //finds all documents 
     /**
      * findIterable = qDetail.find(eq(key , value ));  
      * Finds a particular document consisting key value pair 
      * 
      */
     Block<Document>  printBlock = new Block<Document>(){
         
         JSONObject  resultObject;
         @Override
         public void apply(Document t) {
                System.out.println(t.toJson());
             try { 
               resultObject = (JSONObject) new JSONParser().parse(t.toJson());
               String subject = (String) resultObject.get("subject");
               subjects.add(subject);
                //System.out.println(sub);
             } catch (ParseException ex) {
                 System.out.println("Error in parsing ");
             }
         }
 };
     
          findIterable.forEach(printBlock);
          return subjects ; 
 }
   
  
   /*
    *Get all the topics of a prticular subject
    * @return List of All the topics under Subject.
    */ 
  public ArrayList getTopics(String subject){
    
      ArrayList<String> topics = new ArrayList<String>();
     FindIterable<Document> findIterable = qDetail.find(eq("subject" ,subject));   //finds all documents 
     //Bson sort = descending("topics");
     //findIterable.sort(sort);
     /**
      * findIterable = qDetail.find(eq(key , value ));  
      * Finds a particular document consisting key value pair 
      * 
      */
     
     Block<Document>  printBlock = new Block<Document>(){
         
         JSONObject  resultObject; 
         JSONArray topicArray ; 
         
         @Override
         public void apply(Document t) {
                System.out.println(t.toJson());
             try { 
               resultObject = (JSONObject) new JSONParser().parse(t.toJson());
                topicArray = (JSONArray)resultObject.get("topics");
               for(int i = 0 ; i<topicArray.size() ; i++){
                   JSONObject topic = (JSONObject) topicArray.get(i);
                   String topicName = (String) topic.get("name");
                   topics.add(topicName);
               }
               // System.out.println(sub);
             } catch (ParseException ex) {
                 System.out.println("Error in parsing ");
             }
         }
 };
     
          findIterable.forEach(printBlock);
          return topics ; 
      
      
      
      
      
  }
    

 /*
  *Returns only the first occurance of topic ID
  */
  public String  getQuizId(String subject, String topic){
                  
      ArrayList<String> id =new ArrayList<String>() ; 
       FindIterable<Document> findIterable = qDetail.find(and(eq("subject" ,subject), eq("topics.name",topic)));   //finds all documents 
      Block<Document>  printBlock = new Block<Document>(){
          
   
         JSONObject  resultObject; 
         JSONArray topicArray ; 
         
         @Override
         public void apply(Document t) {
                System.out.println(t.toJson());
             try { 
                resultObject = (JSONObject) new JSONParser().parse(t.toJson());
                topicArray = (JSONArray)resultObject.get("topics");
            
                for(int i = 0 ; i<topicArray.size() ; i++){
                   JSONObject Topic = (JSONObject) topicArray.get(i);
                   String topicID = (String)Topic.get("id");
                  // System.out.println(topicID);
                   
                    if(Topic.get("name").equals(topic)){
                      //  System.out.println(topicID);
                      id.add(topicID);
                    }
             }
             }  catch (ParseException ex) {
                 System.out.println("Error in parsing ");
             }
         }
 };
  
           System.out.println("hello");
           findIterable.forEach(printBlock);
          
      
     return id.get(0);
         }
  
  public void getQuiz(String quizID){
      
      
           FindIterable<Document> findIterable = qCollection.find(eq("quizid", quizID));
           Block<Document> printBlock = new Block < Document>(){
               @Override
               public void apply(Document t) {
                     System.out.println(t.toJson());
               }
               
               
           };
      
           findIterable.forEach(printBlock);
          /// qCollection.find(eq("quizid", quizID));





}
  
  
}


