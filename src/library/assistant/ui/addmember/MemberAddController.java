/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.ui.addmember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author ahmedaliqureshi
 */
public class MemberAddController implements Initializable {
     DatabaseHandler handler;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton savebtn;
    @FXML
    private JFXButton cancelbtn;
    @FXML
    private AnchorPane rootPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = DatabaseHandler.getInstance();
        
    }    


    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPanel.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void addMember(ActionEvent event) {
        String mName = name.getText();
        String mID = id.getText();
        String mMobile = mobile.getText();
        String mEmail = email.getText();
        Boolean flag = mName.isEmpty()||mID.isEmpty()||mMobile.isEmpty()||mEmail.isEmpty();
        if(flag){ 
        Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Please Enter in all fields");
           alert.showAndWait();
           return;
        }
        String st = "INSERT INTO MEMBER VALUES (" 
                + "'" + mID + "'," +
                "'"+ mName + "'," +
                "'"+ mMobile + "'," +
                "'"+ mEmail + "'" +
                ")";
        System.out.println(st);
        if(handler.execAction(st)){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText(null);
           alert.setContentText("Saved");
           alert.showAndWait();
           
        }else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Error Occured");
           alert.showAndWait();
        }
                
    }
}
