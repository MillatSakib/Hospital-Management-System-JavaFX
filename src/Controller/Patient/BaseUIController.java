/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Controller.Main;
import Model.Admin;
import Model.Doctor;
import Model.User;
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
//    @FXML
//    private ComboBox<String> specialisDoctors;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane ActiveUI;
    public static AnchorPane ActiveUIRef;
    @FXML
    private AnchorPane dynamicOption;

    @FXML
    private void changeScenePrescription(ActionEvent e) throws Exception {
        Parent prescription = FXMLLoader.load(getClass().getResource("/View/Patient/Prescriptions/Prescription.fxml"));
        ActiveUI.getChildren().setAll(prescription);
    }

    @FXML
    private void handleUpdateProfile(ActionEvent e) throws Exception {

        if ("admin".equals(Main.role)) {
            Parent register = FXMLLoader.load(getClass().getResource("/View/User/UpdateProfile/AdminProfile.fxml"));
            ActiveUI.getChildren().setAll(register);
        } else if ("doctor".equals(Main.role)) {
            Parent register = FXMLLoader.load(getClass().getResource("/View/User/UpdateProfile/DoctorProfile.fxml"));
            ActiveUI.getChildren().setAll(register);
        } else {
            
            Parent register = FXMLLoader.load(getClass().getResource("/View/User/UpdateProfile/UserProfile.fxml"));
            ActiveUI.getChildren().setAll(register);
        }

    }

    @FXML
    private void changeSceneAppoinment(ActionEvent e) throws Exception {
        appoinment();
    }

    @FXML
    private void handleSceneTakeBed(ActionEvent e) throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/View/Patient/AllAppoinment/AllAppoinment.fxml"));
        ActiveUI.getChildren().setAll(register);
    }

    public void setImage(String imageUrl) {
        // Create a new Image object with the URL
        Image imagee = new Image(imageUrl);

        // Set the Image object in the ImageView
        image.setImage(imagee);
    }

    @FXML
    private void handleLogOut(ActionEvent Event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Auth/Base.fxml"));
        Scene change = new Scene(root);
        String Role = Main.role;

        //After logout reset all Data from software
        if ("doctor".equals(Role)) {
            Doctor.resetDoctor();
        } else if ("admin".equals(Role)) {
            Admin.resetAdmin();
        } else {
            User.resetUser();
        }
        Main.stageRef.setScene(change);
    }

    public void addFeature() throws Exception {
        if ("doctor".equals(Main.role)) {
            Parent register = FXMLLoader.load(getClass().getResource("/View/Doctor/BaseFeature.fxml"));
            dynamicOption.getChildren().setAll(register);
        } else if ("admin".equals(Main.role)) {
            Parent register = FXMLLoader.load(getClass().getResource("/View/Admin/FXML.fxml"));
            dynamicOption.getChildren().setAll(register);
        }
    }

    public void appoinment() throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/View/Patient/Appoinment/Appoinment.fxml"));
        ActiveUI.getChildren().setAll(register);
    }


        
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActiveUIRef = ActiveUI;
        String result = (Main.imgURL != null && !"".equals(Main.imgURL)) ? Main.imgURL : "/View/images/person.png";
        setImage(result);
        try{
        if("".equals(User.getName()) || User.getName() == null || "".equals(User.getEmail()) || User.getEmail() == null ||"".equals(User.getAge()) || User.getAge() == null || "".equals(User.getGender()) || User.getGender() == null){
        Parent register = FXMLLoader.load(getClass().getResource("/View/User/UpdateProfile/UserProfile.fxml"));
            ActiveUI.getChildren().setAll(register);
            return;
        }}catch(Exception e){
            System.out.println(e);
        }
        try {
            addFeature();
        } catch (Exception ex) {
            Logger.getLogger(BaseUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            appoinment();
        } catch (Exception ex) {
            Logger.getLogger(BaseUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
