/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
    private JSONObject question ;  //Stores a particular JSON Question object
    private JSONObject section ;   //Stores a particular JSON Section object ;
    private JSONObject quiz ;//Stores a whole QUiz
    private JSONArray options;
    public TeacherHelper(){
        
            MongoClientURI uri  = new MongoClientURI("mongodb://localhost:27017");
            MongoClient mongoClient = new MongoClient(uri);
            dbquiz = mongoClient.getDatabase("QUIZ");
            qCollection = dbquiz.getCollection("quizCollection");
            System.out.println("COnnected");
            question =new JSONObject();
            section =new JSONObject();
            quiz = new JSONObject();
            options = new JSONArray();
            
}
    
    public JSONObject createQuestion(String qNo, String question, JSONArray options, String answer, String marks){
            
            this.question.put("question", question);
            this.question.put("options",options);
            this.question.put("answer",answer);
            this.question.put("marks", marks);
            this.question.put("qNo",qNo);
            return this.question;
 }
  
   public JSONObject createSection(String sectionName, String time, JSONArray questions){
     
               this.section.put("sectionName", sectionName);
               this.section.put("time", time);
               this.section.put("questions",questions);
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
   
   
     public JSONArray getOptions(String option1, String option2, String option3, String option4){
        
               this.options.add(option1);
                this.options.add(option2);
                 this.options.add(option3);
                  this.options.add(option4);
                return options;
        
    }
         
   public void hostQuiz(JSONObject quiz) {
                
                String jString = quiz.toString();
                Document d =Document.parse(jString);
                //Document doc = new Document("quiz", jString);
                qCollection.insertOne(d);
        
   }
    
       
   }
    
     
