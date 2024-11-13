/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Auth;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class BaseController implements Initializable {
    @FXML
    private AnchorPane authChangeView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try{
        Parent login = FXMLLoader.load(getClass().getResource("/View/Auth/Login/Login.fxml"));
        authChangeView.getChildren().setAll(login);
        }catch(IOException ex){
            System.out.println(ex);
        }
        
        
    }    
    
}
