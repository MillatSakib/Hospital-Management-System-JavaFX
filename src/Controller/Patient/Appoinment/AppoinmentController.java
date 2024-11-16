/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient.Appoinment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class AppoinmentController implements Initializable {
@FXML
    private ComboBox<String> specialisDoctors;
    
@FXML
public static void handleAll(){
    System.out.println("hello");
}

@FXML
private void handleButtonClick(ActionEvent e){
    System.out.println("Button Clicked");
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        specialisDoctors.setItems(FXCollections.observableArrayList("Cardiologist", "Neurologist", "Dermatologist"));
        
        // TODO
    }    
    
}
