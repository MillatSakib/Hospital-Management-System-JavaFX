/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Doctor.AllPatient;

import Model.AllPatientForDoctor;
import Model.Doctor;
import Model.MYSQLDatabaseOp;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class AllPatientController implements Initializable {
@FXML
private TableView<AllPatientForDoctor> allPatientTable;
@FXML
private TableColumn<AllPatientForDoctor, String> name;
@FXML
private TableColumn<AllPatientForDoctor, String> id;
@FXML
private TableColumn<AllPatientForDoctor, String> email;
@FXML
private TableColumn<AllPatientForDoctor, String> gender;
@FXML
private TableColumn<AllPatientForDoctor, String> age;
@FXML
private TableColumn<AllPatientForDoctor, String> problem;
@FXML
private TableColumn<AllPatientForDoctor, String> prescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        problem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        prescription.setCellValueFactory(new PropertyValueFactory<> ("prescription"));
        
        try{
            MYSQLDatabaseOp database = new MYSQLDatabaseOp();
            String query = "SELECT * FROM appoinmentdetails WHERE DoctorID='"+Doctor.getDoctorID()+"' AND PatientName!='"+Doctor.getName()+"'";
            ObservableList<AllPatientForDoctor> allPatients = database.allPatient(query);
            allPatientTable.setItems(allPatients);
        }catch(SQLException ee){
            System.out.println(ee);
        }
    }    
    
}
