/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.User.ApplyAsDoctor;

import Controller.Main;
import Controller.Patient.BaseUIController;
import Model.MYSQLDatabaseOp;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class ApplyAsDoctorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField doctorID;
    @FXML
    private ComboBox<String> specialization;

    @FXML
    private void handleApplyAsDoctor(ActionEvent e) throws Exception {

        String specializationText = specialization.getValue();
        String doctorIDText = doctorID.getText();
        if ("".equals(specializationText) || specializationText == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Specialization!", ButtonType.OK);
            alert.show();
            return;
        }
        if ("".equals(doctorIDText) || doctorIDText == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Doctor ID!", ButtonType.OK);
            alert.show();
            return;
        }
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        boolean flag = database.handleApplyAsDoctor(User.getID(), specializationText, doctorIDText);
        if (flag) {
            Main.DoctorID=doctorIDText;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Applied Successfully!", ButtonType.OK);
            Parent forword = FXMLLoader.load(getClass().getResource("/View/Patient/Appoinment/Appoinment.fxml"));
            BaseUIController.ActiveUIRef.getChildren().setAll(forword);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Database Error!", ButtonType.OK);
            alert.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        doctorID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!newValue.matches("\\d*")) {
                    doctorID.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        specialization.setItems(FXCollections.observableArrayList("Cardiologist", "Neurologist", "Dermatologist"));
    }

}
