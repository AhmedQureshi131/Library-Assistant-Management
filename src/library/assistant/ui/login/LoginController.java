/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.settings.Preferences;
import library.assistant.ui.main.MainController;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author ahmedaliqureshi
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
     
    Preferences preferences;
    @FXML
    private Label titleLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferences = Preferences.getPreferences();
        
    }    

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
         titleLabel.setText("Library Login Panel");
         titleLabel.setStyle("-fx-background-color:black;-fx-text-fill:white");

        String uname = username.getText();
        String pword = DigestUtils.shaHex(password.getText());
        if(uname.equals(preferences.getUsername()) && pword.equals(preferences.getPassword())){
            closeStage();
            loadMain();
            
        }else{
            titleLabel.setText("Invalid Credentials");
            titleLabel.setStyle("-fx-background-color:black;-fx-text-fill:red");
            
            
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage)username.getScene().getWindow()).close();
    }
       void loadMain(){
        try{
        Parent parent = FXMLLoader.load(getClass().getResource("/library/assistant/ui/main/main.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Welcome To Library Assistant");
        stage.setScene(new Scene(parent));
        stage.show();
        } catch (IOException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            
    }
}
    
}
