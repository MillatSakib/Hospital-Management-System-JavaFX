/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Controller.Main;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class BaseUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane dynamicOption;
    public void setImage(String imageUrl) {
        // Create a new Image object with the URL
        Image imagee = new Image(imageUrl);

        // Set the Image object in the ImageView
        image.setImage(imagee);
    }
    @FXML
    private void handleLogOut(ActionEvent Event)throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("/View/Auth/Base.fxml"));
    Scene change = new Scene(root);
    Main.stageRef.setScene(change);
    }
    
    public void addFeature()throws Exception{
     if("doctor".equals(Main.role)){
             Parent register = FXMLLoader.load(getClass().getResource("/View/Doctor/BaseFeature.fxml"));
                dynamicOption.getChildren().setAll(register);
         }else if("admin".equals(Main.role)){
             Parent register = FXMLLoader.load(getClass().getResource("/View/Admin/FXML.fxml"));
                dynamicOption.getChildren().setAll(register);
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String result = (Main.imgURL != null && !"".equals(Main.imgURL)) ? Main.imgURL : "/View/images/person.png";
         setImage(result);
        try {
            addFeature();
        } catch (Exception ex) {
            Logger.getLogger(BaseUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
