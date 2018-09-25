/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Divyanshu
 */
public class TeacherHelper {
    
    private MongoDatabase dbquiz;
    private MongoCollection<Document>  qCollection ;
    private MongoCollection<Document>  qDetail ;
    private JSONObject question ;  //Stores a particular JSON Question object
    private JSONObject section ;   //Stores a particular JSON Section object ;
    private JSONObject quiz ;//Stores a whole QUiz
    private JSONArray options;
    private MongoClient  mongoClient;
    public TeacherHelper(){
        
            MongoClientURI uri  = new MongoClientURI("mongodb://192.168.0.3:27017");
            mongoClient = new MongoClient(uri);
            dbquiz = mongoClient.getDatabase("QUIZ");
            qCollection = dbquiz.getCollection("quizCollection");
             qDetail = dbquiz.getCollection("quizDetail");
            System.out.println("Connected");
            question =new JSONObject();
            section =new JSONObject();
            quiz = new JSONObject();
            options = new JSONArray();
            
}
    
    public JSONObject createQuestion(String qNo, String question, JSONArray options, String answer, String marks){
            
            this.question.clear();
            this.question.put("question", question);
            this.question.put("options",options);
            this.question.put("answer",answer);
            this.question.put("marks", marks);
            this.question.put("qNo",qNo);
            System.out.println("SH : " +question);
          
            return this.question;
 }
  
   public JSONObject createSection(String sectionName, String time, JSONArray questions){
               
               this.section.clear();
               this.section.put("sectionName", sectionName);
               this.section.put("time", time);
               this.section.put("questions",questions);
               //System.out.println("SH : " +section);
               return this.section;
 }    
   
   public JSONObject createQuiz(String quizid, String teachername, String topicname, String subject, JSONArray sections){
       
                this.quiz.put("quizid",quizid);
                this.quiz.put("teachername", teachername);
                this.quiz.put("topicname", topicname);
                this.quiz.put("subject", subject);
                this.quiz.put("sections",sections);
                return this.quiz;
  }
   
   
     public JSONArray addOptions(String option1, String option2, String option3, String option4){
               
               this.options.clear();
               this.options.add(option1);
                this.options.add(option2);
                 this.options.add(option3);
                  this.options.add(option4);
                 // System.out.println("SH : " +options);
                return options;
        
    }
         
   public void hostQuiz(JSONObject quiz) {
                
                String jString = quiz.toString();
                Document d =Document.parse(jString);
                //Document doc = new Document("quiz", jString);
                qCollection.insertOne(d);
        
   }
   
   
   public void addQuizDetail(String subject, String topic ){
       
       String id ="hello";
           JSONObject temp =new JSONObject();
           temp.put("name", topic);
           temp.put("id",id);
      long count = qDetail.count(eq("subject", subject));
       if(count==0){
           Document d = new Document("subject",subject);
           System.out.println("Inside count = 0"+ count);
           JSONArray topicArray = new JSONArray();
           topicArray.add(temp);
           d.append("topics", topicArray);
           qDetail.insertOne(d);
           
 }else{
           
           System.out.println("Inside count = 1"+ count);
           
         FindIterable<Document> findIterable = qDetail.find(eq("subject", subject));
           
           Block<Document> printBlock = new Block<Document>() {
               @Override
               public void apply(Document t) {
                    
                   System.out.println(t.toString());
                    
                   
                    ArrayList<JSONObject> topics = (ArrayList<JSONObject>) t.get("topics");
                    topics.add(temp);
                    t.replace("topics", topics);
                   System.out.println( t.get("topics"));
                   qDetail.replaceOne(eq("subject", subject), t);
                  }
        };
           findIterable.forEach(printBlock); 
       }
    }
           
  
   
   
   
   public void close(){
           mongoClient.close();
       
       
   }
    
       
   }
    
     
