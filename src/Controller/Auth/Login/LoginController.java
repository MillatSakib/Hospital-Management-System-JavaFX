/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Auth.Login;

import Controller.Auth.BaseController;
import Model.MYSQLDatabaseOp;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class LoginController implements Initializable {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passField;
    
    @FXML
    private Label setText;
    
    public static Label setTextOther;
    @FXML
    private void handleRegister() throws Exception{
        
        BaseController.goToRegister();
    }
 
    @FXML
    private void handleLogin() throws Exception{
        String email = emailField.getText();
        String pass = passField.getText();
        if("".equals(email) || "".equals(pass)){
           setText.setText("Enter Email and Password Properly");
           return;
           }
        //System.out.println("SELECT * FROM users where email='"+email+"' AND Password='"+pass+"'");
        MYSQLDatabaseOp dbOp = new MYSQLDatabaseOp();
        dbOp.handleQueryLogin("SELECT * FROM users where email='"+email+"' AND Password='"+pass+"'");
        
    }

    @FXML
    private void handleChange(){
    setText.setText("");
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTextOther=setText;
    }    
    
}
