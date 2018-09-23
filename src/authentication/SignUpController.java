/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import DBManagement.DatabaseTransaction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import teacher.TeacherOptionController;

/**
 *
 * @author Divyanshu
 */
public class SignUpController implements Initializable {
   
 private String user="" ;     
//Anchor pane
@FXML private AnchorPane ap;
@FXML AnchorPane signUp;
@FXML AnchorPane signIn;
@FXML Button abtnSignin ;
@FXML Button abtnSignup;
@FXML ImageView close;

//SignUp
@FXML Button btnSignup ;
@FXML TextField firstName;
@FXML TextField lastName;
@FXML TextField suEmail;
@FXML PasswordField suPassword;
@FXML CheckBox cbsuTeacher;
@FXML CheckBox cbsuStudent;

//Sign in
@FXML Button btnSignin ;
@FXML TextField siEmail;
@FXML PasswordField siPassword;
@FXML CheckBox cbsiTeacher;
@FXML CheckBox cbsiStudent;

    /**
     *
     */
    public void signInCalled(){
    abtnSignin.setStyle("-fx-background-color:  #aa00ff;-fx-font: 20 arial; ");
    abtnSignup.setStyle("-fx-background-color:    #6a1b9a; -fx-font: 15 arial;");
    signUp.setVisible(false);
        signIn.setVisible(true);
        
}

    /**
     *
     */
    public void signUpCalled(){
    
        abtnSignup.setStyle("-fx-background-color:  #aa00ff;-fx-font: 20 arial; ");
        abtnSignin.setStyle("-fx-background-color:    #6a1b9a; -fx-font: 15 arial;");
        signUp.setVisible(true);
        signIn.setVisible(false);
        
}

    /**
     *Register the user
     */
    public void registerNewUser(){
    System.out.println(user);
    DatabaseTransaction.registerUser(firstName.getText().trim(), lastName.getText().trim(), suEmail.getText().trim(), suPassword.getText(),user );
}

    /**
     *
     * @param e
     */
    public void signInUser(ActionEvent e){
   
   int res=1;
     try {
       // int res =  DatabaseTransaction.signinUser(siEmail.getText().trim(),  siPassword.getText(), user );
        if(res==1&& user.equals("teacher") ){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/teacher/TeacherOption.fxml"));
            Parent teacherRoot = loader.load();
            Scene scene = new Scene(teacherRoot,1080,720);
            TeacherOptionController controller = loader.getController();
            controller.receiveData("hello", "  hello");
            
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            
            
            //Stage stage = new Stage();
            
            stage.setScene(scene);
            
            stage.show();
            //closeUI();
        }
        
        
        
        
        
        
     } catch (IOException ex) {
         System.out.println("error in signin");
     }
}

    /**
     *
     */
    public void closeUI(){
    
Stage stage = (Stage) ap.getScene().getWindow();
stage.close();
}

    /**
     *
     * @param cb
     * @param b
     */
    public void buttonControl(CheckBox cb, Button b){
    
    if(cb == cbsuTeacher ||  cb==cbsiTeacher)
        user="student";
    else
        user="teacher";
    
    cb.setSelected(false);
    b.setDisable(false);
    if(cbsuTeacher.isSelected()&& cbsuStudent.isSelected()&& cbsiTeacher.isSelected()&&cbsiStudent.isSelected())
    b.setDisable(true);
    
    
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
          cbsuTeacher.setOnAction(e -> buttonControl(cbsuStudent , btnSignup));
          cbsuStudent.setOnAction(e -> buttonControl(cbsuTeacher , btnSignup));
          cbsiTeacher.setOnAction(e -> buttonControl(cbsiStudent , btnSignin));
          cbsiStudent.setOnAction(e -> buttonControl(cbsiTeacher , btnSignin));
      
        
        signUp.setVisible(true);
        signIn.setVisible(false);
        
        abtnSignin.setOnAction(e -> signInCalled());
          abtnSignup.setOnAction(e -> signUpCalled());       
         close.setOnMousePressed(e -> closeUI());
          btnSignup.setOnAction(e  -> registerNewUser());
          btnSignin.setOnAction( e -> signInUser(e));
        }    
    
}
