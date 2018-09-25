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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author Kushagra
 */

public class TeacherDashboardController implements Initializable {
    
       private String email;
       @FXML protected AnchorPane dashboardPane;
    
       
    
    public void receiveEmail(String email){
     this.email = email;
    }
    public void hostButtonPushed(ActionEvent e)throws IOException{
           Stage dashboard = (Stage)dashboardPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/teacher/TeacherHost.fxml"));
            Parent hostParent = loader.load();
            Scene hostScene = new Scene(hostParent);
            TeacherHostController controller = loader.getController();
            controller.receiveStage(dashboard);
           Stage hostWindow = new Stage();
            
            dashboard.hide();
            System.out.println("hidden");
           hostWindow.setScene(hostScene);
           hostWindow.show();
           //dashboard.show();
        
    }
    public void queryButtonPushed(ActionEvent e)throws IOException{
        Parent hostParent = FXMLLoader.load(getClass().getResource("/teacher/TeacherQuery.fxml"));
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
         
    }    
}
