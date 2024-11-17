/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient.Prescriptions;

import Model.AllPrescription;
import Model.AppoinmentDoctorList;
import Model.MYSQLDatabaseOp;
import Model.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class PrescriptionController implements Initializable {


@FXML
private TableView<AllPrescription> AllPrescriptionTable;
@FXML
private TableColumn<AllPrescription, String> doctorName;
@FXML
private TableColumn<AllPrescription, String> DoctorID;
@FXML
private TableColumn<AllPrescription, String> yourProblem;
@FXML
private TableColumn<AllPrescription, String> doctorPrescription;

    ObservableList<AllPrescription> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                // Configure the TableColumn bindings to properties of AllPrescription class
        doctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        DoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        yourProblem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        doctorPrescription.setCellValueFactory(new PropertyValueFactory<>("prescription"));
        
        
        
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
         String query = "SELECT * FROM appoinmentdetails WHERE PatientID='"+User.getID()+"' AND Visited='"+1+"'";
    try {
        ObservableList<AllPrescription> prescriptions = database.prescriptions(query);
        AllPrescriptionTable.setItems(prescriptions);
    } catch (SQLException ex) {
        Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
}
