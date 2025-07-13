/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

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
public class FXMLController implements Initializable {
@FXML
private void changeSceneUpdateRole(ActionEvent e)throws Exception{
    Parent updateRoleAdmin = FXMLLoader.load(getClass().getResource("/View/Admin/UpdateRole/UpdateRole.fxml"));
    BaseUIController.ActiveUIRef.getChildren().setAll(updateRoleAdmin);
}

@FXML
private void handleRmoveUser(ActionEvent e)throws Exception{
    Parent updateRoleAdmin = FXMLLoader.load(getClass().getResource("/View/Admin/RemoveUser/RemoveUser.fxml"));
    BaseUIController.ActiveUIRef.getChildren().setAll(updateRoleAdmin);
}

@FXML
private void handleApproveDoctor(ActionEvent e)throws Exception{
    Parent approveDoctorAdmin = FXMLLoader.load(getClass().getResource("/View/Admin/ApproveDoctor.fxml"));
    BaseUIController.ActiveUIRef.getChildren().setAll(approveDoctorAdmin);
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
