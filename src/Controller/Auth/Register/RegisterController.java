/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Auth.Register;

import Controller.Auth.BaseController;
import Model.MYSQLDatabaseOp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class RegisterController implements Initializable {

    @FXML
    private Label faildmsg;
    public static Label faildmsgSet;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;

    @FXML
    private void handleLogin() throws Exception {
        BaseController.goToLogin();
    }

    @FXML
    private void resetLable(){
    faildmsg.setText("");
    }
    
    
    @FXML
    private void handleRegister() throws Exception {
        String email = emailField.getText();
        String pass = passField.getText();

        if ("".equals(email) || "".equals(pass)) {
            faildmsg.setText("Enter Email and Password Properly");
            return;
        }

        if (isValidEmail(email)) {

            //System.out.println("SELECT * FROM users where email='"+email+"' AND Password='"+pass+"'");
            MYSQLDatabaseOp dbOp = new MYSQLDatabaseOp();
            dbOp.handleRegister(email, pass);
        } else {
            faildmsg.setText("Invalid Email! Enter a valid email.");
        }
    }

    public boolean isValidEmail(String email) {
        // Define the regex pattern for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Check if the email matches the pattern
        return email != null && pattern.matcher(email).matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        faildmsgSet = faildmsg;
        // TODO
    }

}
