/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class StudentQuizPageController implements Initializable {

//END PANE
 @FXML Button btnRate;
 @FXML Label labelMarks;
 @FXML TextArea textareaQuery;
 @FXML Button btnSendQuery;
 @FXML TextField teachername;
 @FXML  Slider slider;
 @FXML Label rating;

// Top Pane 
@FXML TextField sName;
@FXML ChoiceBox cbSection;
@FXML Button btnGo;
@FXML TextField timer;

//Question pane 
@FXML TextArea displayQuestion;
@FXML Button btnNext;
@FXML TextField quesNo;
@FXML TextField marks;
@FXML RadioButton op1;
@FXML RadioButton op2;
@FXML RadioButton op3;
@FXML RadioButton op4;
@FXML Button btnSave;
@FXML Button btnEnd;

//StartPane 
@FXML Button btnStart;

//Panes
@FXML Pane startPane;
@FXML Pane quizPane;
@FXML Pane optionPane;
@FXML Pane TFPane;
@FXML Pane EndPane;

@FXML ToggleGroup optionsGroup;

  private Stage dashboard;
  private String quizid;
  private StudentHelper sh ;
  private JSONObject quiz;
  private ArrayList<JSONObject> sections;
  private JSONObject currentSection;
  private JSONObject currentQuestion;
  private String Canswer;
  
  private ArrayList<JSONObject> questions;
  private ArrayList<String> options;
  static int countS =0, countQ=0;
  private static int countMarks = 0;
  
  private ArrayList<ArrayList<Integer>> secQ=new ArrayList<ArrayList<Integer>>(); // keeps track of section and question
  private ArrayList<Integer> qTrack = new ArrayList<Integer>();  
    
        
        
    public void receiveDetails(Stage dashboard, String quizId) throws ParseException{
            sh =new StudentHelper();
            this.dashboard =dashboard;
            this.quizid = quizId;
            System.out.println("Recieved dashboard and id "+quizId );
            quiz =sh.getQuiz(quizId);
            sections =sh.getSectionArray(quiz);
           // System.out.println(sections);
            int i =0;
            System.out.println(sections.size());
            while(i<sections.size()){
                qTrack.add(i,0);
                i++;
            }
            System.out.println("Qtrack "+qTrack);
            currentSection = sections.get(countS);
            questions = sh.getQuestionArray(currentSection);
            Collections.shuffle(questions);
            
            currentQuestion = questions.get(qTrack.get(countS));
            displayQuestion.setText((String) currentQuestion.get("question"));
            options  =sh.getOptionsArray(currentQuestion);
            op1.setText(options.get(0).toString());
            op2.setText(options.get(1).toString());
            op3.setText(options.get(2).toString());
            op4.setText(options.get(3).toString());
            
            marks.setText(currentQuestion.get("marks").toString());
           // countMarks = countMarks +Integer.parseInt(marks.getText());
           // secQ.add(new ArrayList<Integer>());
            //secQ.get(0).add(countS);
            //secQ.get(0).add(countQ);
            
               
            //sh.getSectionArray(quiz);
            //sh.close();
          // System.out.println(quiz);
  }

    public void startQuiz(ActionEvent e){
        startPane.setVisible(false);
    
        quizPane.setVisible(true);
  }

    public void nextQuestionPressed(ActionEvent e){
            
            
            countQ++;
           
            qTrack.set(countS, countQ);
            currentSection = sections.get(countS);
             System.out.println("Cuurent S "+currentSection);
            currentQuestion = questions.get(qTrack.get(countS));
            displayQuestion.setText((String) currentQuestion.get("question"));
            options  =sh.getOptionsArray(currentQuestion);
            op1.setText(options.get(0).toString());
            op2.setText(options.get(1).toString());
            op3.setText(options.get(2).toString());
            op4.setText(options.get(3).toString());
            marks.setText(currentQuestion.get("marks").toString());
            if(countQ==questions.size()-1 )
              changeSection(countS);
              
      }
    
    public void  changeSection(int i){
            
            i ++;
            if(i<sections.size()) {
             countS = i;
             System.out.println("changeSe" +countS);
          
            currentSection = sections.get(countS);
            questions = sh.getQuestionArray(currentSection);
            Collections.shuffle(questions);
            currentQuestion =  questions.get(qTrack.get(countS));
            countQ = qTrack.get(countS)-1;
             System.out.println("Qtrack "+qTrack);
          //  displayQuestion(int i );
           //return currentSection;
            //currentQuestion = 
            }
            else{
                btnNext.setDisable(true);
            }
        
        
        
    }
   
    public void changeQuestion(int i){
        
        currentQuestion = questions.get(i);
        displayQuestion.setText((String) currentQuestion.get("question"));
            options  =sh.getOptionsArray(currentQuestion);
            op1.setText(options.get(0).toString());
            op2.setText(options.get(1).toString());
            op3.setText(options.get(2).toString());
            op4.setText(options.get(3).toString());
            marks.setText(currentQuestion.get("marks").toString());
        
        
    }
    
    public void saveButtonPressed(ActionEvent e){
         
        
            
            Canswer = (String) currentQuestion.get("answer");
            System.out.println(Canswer+" cans");
            if(optionsGroup.getSelectedToggle().equals(op1) && Canswer.equals(op1.getText())){
                 countMarks = countMarks +Integer.parseInt(marks.getText()); 
                 System.out.println(countMarks+" "+Integer.parseInt(marks.getText()) );
           } else if(optionsGroup.getSelectedToggle().equals(op2) && Canswer.equals(op2.getText())){
                 countMarks = countMarks +Integer.parseInt(marks.getText()); 
                 System.out.println(countMarks);
           }else  if(optionsGroup.getSelectedToggle().equals(op3) && Canswer.equals(op3.getText())){
                 countMarks = countMarks +Integer.parseInt(marks.getText()); 
                 System.out.println(countMarks);
           } else if(optionsGroup.getSelectedToggle().equals(op4) && Canswer.equals(op4.getText())){
                 countMarks = countMarks +Integer.parseInt(marks.getText()); 
                 System.out.println(countMarks);
           } 
    }
    
    
    public void endButtonPressed(ActionEvent e){
        System.out.println(countMarks);
  /// send marks to record  
       quizPane.setVisible(false);
       EndPane.setVisible(true);
       labelMarks.setText(Integer.toString(countMarks));
       teachername.setText(quiz.get("teachername").toString());
       slider.setValue(0);
       rating.setText(new Double(0).toString());
       
       rating.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
       //Stage stage =(Stage)((Node)e.getSource()).getScene().getWindow();
       //this.dashboard.show();
       //stage.close();
        
  }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          //  System.out.println("Recieved dashboard and id "+this.quizid );
       //    sh =new StudentHelper();
           //while(this.quizid!=null)
          // sh.getQuiz(this.quizid);
          optionsGroup = new ToggleGroup();
         op1.setToggleGroup(optionsGroup);
         op2.setToggleGroup(optionsGroup);
         op3.setToggleGroup(optionsGroup);
         op4.setToggleGroup(optionsGroup);
          
          
        
        
        
        
    }    
    
}
