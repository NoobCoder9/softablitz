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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Kushagra
 */

public class TeacherOptionController implements Initializable {
    
    @FXML private Label labelName;
    String first, last;
    
       
    
    public void receiveData(String firstname, String lastname){
        first = firstname;
        last = lastname;
    }
    public void hostButtonPushed(ActionEvent e)throws IOException{
        Parent hostParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherHost.fxml"));
        Scene hostScene = new Scene(hostParent);
        
        Stage hostWindow = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        hostWindow.setScene(hostScene);
        hostWindow.show();
    }
    public void queryButtonPushed(ActionEvent e)throws IOException{
        Parent hostParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherQuery.fxml"));
        Scene hostScene = new Scene(hostParent);
        
        Stage hostWindow = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        hostWindow.setScene(hostScene);
        hostWindow.show();
    }
    public void editButtonPushed(ActionEvent e)throws IOException{
        Parent hostParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherEdit.fxml"));
        Scene hostScene = new Scene(hostParent);
        
        Stage hostWindow = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        hostWindow.setScene(hostScene);
        hostWindow.show();
    }
    public void logoutButtonPushed(ActionEvent e)throws IOException{
        Parent hostParent = FXMLLoader.load(getClass().getResource("/teacher/SignUp.fxml"));
        Scene hostScene = new Scene(hostParent);
        
        Stage hostWindow = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        hostWindow.setScene(hostScene);
        hostWindow.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         labelName.setText(first + " " +last);
    }    
}
