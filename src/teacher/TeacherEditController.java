/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Kushagra
 */
public class TeacherEditController implements Initializable {
    
    @FXML private Button btnsaveExit;
    @FXML private Button btnsaveCont;
    @FXML private TextField textfieldSubject, textfieldQuizName, textfieldSection, textfieldTime, textfieldQuesNo, textfieldop1, textfieldop2, textfieldop3,textfiledop4, textfieldans;
    @FXML private TextArea textareaQues;
    
    public void pressedSaveContbutton(ActionEvent e){
        //save to database and continue on same scene
    }
    public void pressedSaveExitbutton(ActionEvent e)throws IOException{
        //save to database and go back to TeacherOption
        Parent saveExitroot = FXMLLoader.load(getClass().getResource("/teacher/TeacherOption.fxml"));
        Scene scene = new Scene(saveExitroot);
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public void entertextfieldSubject()throws IOException{
        String s = br.readLine();
        textfieldSubject.setText(s);
    }
    public void entertextfieldQuizName()throws IOException{
        String s = br.readLine();
        textfieldQuizName.setText(s);
    }
    public void entertextfieldSection()throws IOException{
        String s = br.readLine();
        textfieldSection.setText(s);
    }
    public void entertextfieldTime()throws IOException{
        String s = br.readLine();
        textfieldTime.setText(s);
    }
    public void entertextfieldQuesNo()throws IOException{
        String s = br.readLine();
        textfieldQuesNo.setText(s);
    }
    public void entertextfieldOp1()throws IOException{
        String s = br.readLine();
        textfieldop1.setText(s);
    }
    public void entertextfieldOp2()throws IOException{
        String s = br.readLine();
        textfieldop2.setText(s);
    }
    public void entertextfieldOp3()throws IOException{
        String s = br.readLine();
        textfieldop3.setText(s);
    }
    public void entertextfieldOp4()throws IOException{
        String s = br.readLine();
        textfiledop4.setText(s);
    }
    public void entertextfieldAnswer()throws IOException{
        String s = br.readLine();
        textfieldans.setText(s);
    }
    public void entertextareaQuestion()throws IOException{
        String s = br.readLine();
        textareaQues.setText(s);
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
  
    
}
