/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Doctor;

import Controller.Patient.BaseUIController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class BaseFeatureController extends UIComponents implements Initializable {
@FXML
@Override
void handleSceneAllPatients(ActionEvent e)throws Exception{
Parent updateRoleDoctor = FXMLLoader.load(getClass().getResource("/View/Doctor/AllPatient/AllPatient.fxml"));
BaseUIController.ActiveUIRef.getChildren().setAll(updateRoleDoctor);
}
@FXML
private void handleSceneAllAppoinment(ActionEvent e)throws Exception{
Parent updateRoleDoctor = FXMLLoader.load(getClass().getResource("/View/Doctor/Appoinment/AllApoinment.fxml"));
BaseUIController.ActiveUIRef.getChildren().setAll(updateRoleDoctor);
}
@FXML
@Override
void handleSceneAllPrescription(ActionEvent e)throws Exception{
Parent updateRoleDoctor = FXMLLoader.load(getClass().getResource("/View/Doctor/GivePrescription/GivePrescription.fxml"));
BaseUIController.ActiveUIRef.getChildren().setAll(updateRoleDoctor);
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
