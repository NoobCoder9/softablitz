
package student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class StudentDashboardController implements Initializable {

    
    // Main Pane
    @FXML private TextField mStudentName;
    @FXML private Pane mainPane ;
    @FXML private Button btnGo;
    @FXML private Button btnStart;
    @FXML private ChoiceBox cbQuizSubject;
    @FXML private ChoiceBox cbQuizTopic;
    
    private String studentname ;
    private String email;
    private  ArrayList<String> subjects;
    private ArrayList<String> topics;
    private String currentSubject;
    private StudentHelper sh;
    
    
    public void recieveEmail(String email){
        this.email = email;
   }
   
    
    public void buttonGoPushed(ChoiceBox<String> cb){
           currentSubject = cb.getValue();
           topics = sh.getTopics(currentSubject);
           cbQuizTopic.getItems().clear();
           cbQuizTopic.getItems().addAll(topics);
     }
    
    public void buttonStartPushed(ActionEvent e) throws IOException{
            FXMLLoader loader =new FXMLLoader();
            loader.setLocation(getClass().getResource("/student/StudentQuizPage.fxml"));
            Parent quizRoot = loader.load();
            Scene quizScene = new Scene(quizRoot,1080,720);
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(quizScene);
            stage.show();
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
            sh = new StudentHelper();
            subjects = sh.getSubjects();
            cbQuizSubject.setValue(subjects.get(0));
            cbQuizSubject.getItems().addAll(subjects);
            btnGo.setOnAction(e -> buttonGoPushed(cbQuizSubject));
        //cbQuizSubject.setItems((ObservableList) subjects);
        
    }    
    
}
