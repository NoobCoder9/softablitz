/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Kushagra
 */
public class TeacherHostController implements Initializable {
    
    private TeacherHelper th;
    private static  int qno;
   private JSONArray questions;
    private JSONArray sections;
    //private JSONObject q;
    //private JSONObject s;
    private String sectionName;
    private Stage dashboard;
    
    
    
    
      @FXML private TextField textfieldSubject;
      @FXML private TextField textfieldMarks;
      @FXML private TextField textfieldQuizName;
      @FXML private TextField textfieldSection;
      @FXML private TextField textfieldTime; 
      @FXML private TextField textfieldop1;
      @FXML private TextField textfieldop2;
      @FXML private TextField textfieldop3; 
      @FXML private TextField textfieldop4;
      @FXML private TextField textfieldans;
      @FXML private TextArea textareaQues;
      @FXML private Button btnChangeSection;
    
      public void receiveStage(Stage dashboard){
           
           this.dashboard =dashboard;
           System.out.println("Recieved dashboard");
      }
      
    public void pressedAddbutton(ActionEvent e){
        //save to database and continue on same scene
        JSONObject  q= th.createQuestion(Integer.toString(qno), textareaQues.getText().trim(), (JSONArray) th.addOptions(textfieldop1.getText().trim(),textfieldop2.getText().trim(),textfieldop3.getText().trim(),textfieldop4.getText().trim()).clone(), textfieldans.getText().trim(), textfieldMarks.getText().trim());
        System.out.println(q);
         
        qno++;
        questions.add(q.clone());
        System.out.println(questions);
        textareaQues.setText("");
        textfieldop1.setText("");
        textfieldop2.setText("");
        textfieldop3.setText("");
        textfieldop4.setText("");
        textfieldans.setText("");
        textfieldMarks.setText("");
        //textfieldTime.setText("");
        //q.clear();
        btnChangeSection.setDisable(false);
        textfieldSection.setDisable(true);
        textfieldTime.setDisable(true);
        System.out.println(questions);
        
}
    public void pressedHostExitbutton(ActionEvent e)throws IOException{
        //save to database and go back to TeacherOption
          pressedChangeSection(e);
          JSONObject quiz =th.createQuiz("ABC123", "Mr.X", textfieldQuizName.getText().trim(), textfieldSubject.getText().trim(), sections);
          th.hostQuiz(quiz);
          th.addQuizDetail(textfieldSubject.getText().trim(),textfieldQuizName.getText().trim() );
          th.close();
        
           
       // Parent saveExitroot = FXMLLoader.load(getClass().getResource("/teacher/TeacherDashboard.fxml"));
        //Scene scene = new Scene(saveExitroot);
          Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
           dashboard.show();
        //stage.setScene(scene);
          stage.close();
    }
    
    public void pressedChangeSection(ActionEvent e){
        
        //sections.add();
          //questions = new JSONArray();
          //sections =new JSONArray();
          qno=1;
        System.out.println("change section");
        textfieldTime.setDisable(false); 
           //JSONArray qs = (JSONArray) questions.clone();
           JSONObject  s=th.createSection(textfieldSection.getText().trim(), textfieldTime.getText().trim(), (JSONArray) questions.clone() );
           System.out.println(questions.toJSONString());
           
           System.out.println(questions);
          sections.add(s.clone());
          questions.clear();
          //qs.clear();
        //s.clear();
           System.out.println(sections);
           textfieldSection.setDisable(false);
           btnChangeSection.setDisable(true);
 }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
               th = new TeacherHelper();
               questions = new JSONArray();
               sections =new JSONArray();
               qno=1;
        
    }    
    
}
