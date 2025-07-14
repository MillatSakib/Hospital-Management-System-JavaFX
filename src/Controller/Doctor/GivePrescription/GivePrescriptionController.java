/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Doctor.GivePrescription;

import Model.Doctor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import Model.GivePrescription;
import Model.MYSQLDatabaseOp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.MYSQLDatabaseOp;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class GivePrescriptionController implements Initializable {

    ObservableList<GivePrescription> getAllAppoinment = FXCollections.observableArrayList();
    @FXML
    private TableView<GivePrescription> appoinmentPatientList;
    @FXML
    private TextArea prescription;
    @FXML
    private TableColumn<GivePrescription, String> patientName;
    @FXML
    private TableColumn<GivePrescription, String> gender;
    @FXML
    private TableColumn<GivePrescription, String> patientID;
    @FXML
    private TableColumn<GivePrescription, String> age;
    @FXML
    private TableColumn<GivePrescription, String> problem;

    @FXML
    private void handleSubmit(ActionEvent e) throws Exception {
        System.out.println("give a prescription");
        GivePrescription selectPatient = appoinmentPatientList.getSelectionModel().getSelectedItem();
        String getPrescription = prescription.getText();
        if (selectPatient == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a Patient!", ButtonType.OK);
            alert.show();
            return;
        }
        if (getPrescription == null || "".equals(getPrescription)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please write prescription Properly!", ButtonType.OK);
            alert.show();
            return;
        }
        System.out.println(selectPatient.getPatientID() + " " + getPrescription);
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        boolean response = database.givePrescription(selectPatient.getAppointmentID(), getPrescription);
        if (response) {
            getAllAppoinment = database.handleAllAppoinmentForDoctor(Doctor.getID());
            appoinmentPatientList.setItems(getAllAppoinment);
            prescription.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Prescrition submitted successfully!", ButtonType.OK);
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR, "Error Occured!", ButtonType.OK);
        alert.show();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        patientName.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        patientID.setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        problem.setCellValueFactory(new PropertyValueFactory<>("Prolblem"));
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        try {
            getAllAppoinment = database.handleAllAppoinmentForDoctor(Doctor.getID());
            appoinmentPatientList.setItems(getAllAppoinment);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
