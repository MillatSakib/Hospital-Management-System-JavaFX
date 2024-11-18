/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient.AllAppoinment;

import Model.AllAppoinment;
import Model.MYSQLDatabaseOp;
import Model.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class AllAppoinmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<AllAppoinment> allAppoinment;
    @FXML
    private TableColumn<AllAppoinment,String> doctorName;
    @FXML
    private TableColumn<AllAppoinment,String> doctorID;
    @FXML
    private TableColumn<AllAppoinment, String> yourProblem;
    @FXML
    private TableColumn<AllAppoinment, String> PrescriptionStatus;
    @FXML
    private TableColumn<AllAppoinment, String> contactNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        doctorName.setCellValueFactory(new PropertyValueFactory<>("DoctorName"));
        doctorID.setCellValueFactory(new PropertyValueFactory<>("DoctorID"));   //here doctorID connected to tablecolumn and DoctorID are connected with AllAppoinmentController Class Object
        yourProblem.setCellValueFactory(new PropertyValueFactory<>("Problem"));
        PrescriptionStatus.setCellValueFactory(new PropertyValueFactory<>("Visited"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
        
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        String query = "SELECT * FROM appoinmentdetails WHERE PatientID='"+User.getID()+"'";
         try {
        ObservableList<AllAppoinment> allappoinment = database.allAppoinmentPatient(query);
        allAppoinment.setItems(allappoinment);
       
    } catch (SQLException ex) {
        Logger.getLogger(AllAppoinment.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
}
