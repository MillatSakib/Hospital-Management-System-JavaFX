/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.User.UpdateProfile;

import Controller.Main;
import Controller.Patient.BaseUIController;
import Model.MYSQLDatabaseOp;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class UserProfileController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField imgURL;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private Button applyAsDoctorattr;

    @FXML
    private void handleUpdateProfile(ActionEvent e) throws Exception {
        String updateName = name.getText();
        String updateImgURL = imgURL.getText();
        String updatePhoneNumber = phoneNumber.getText();
        String updateGmail = email.getText();
        String updateAge = age.getText();
        String getGender = gender.getValue();
        if (updateValidation()) {

            MYSQLDatabaseOp updateUserData = new MYSQLDatabaseOp();
            if (updateUserData.handleUpdateUserData(User.getID(), updateName, updateImgURL, updateAge, getGender, null, updatePhoneNumber)) {
                User.setName(updateName);
                User.setImageURL(updateImgURL);
                User.setPhone(updatePhoneNumber);
                User.setEmail(updateGmail);
                User.setAge(updateAge);
                User.setGender(getGender);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your profile Updated Successfully!", ButtonType.OK);
                alert.show();
            }
        }
    }

    @FXML
    private void applyAsDoctor() throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/View/User/ApplyAsDoctor/ApplyAsDoctor.fxml"));
        BaseUIController.ActiveUIRef.getChildren().setAll(register);
    }

    /**
     * Initializes the controller class.
     */
    boolean updateValidation() {
        String updateName = name.getText();
        String updateGmail = email.getText();
        String updateAge = age.getText();
        String getGender = gender.getValue();

        if ("".equals(updateName) || updateName == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Name!", ButtonType.OK);
            alert.show();
            return false;
        }
        if ("".equals(getGender) || getGender == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide you are Male or Female!", ButtonType.OK);
            alert.show();
            return false;
        }
        if ("".equals(updateGmail) || updateGmail == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Email!", ButtonType.OK);
            alert.show();
            return false;
        }
        if ("".equals(updateAge) || updateAge == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Age!", ButtonType.OK);
            alert.show();
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (updateGmail == null || !pattern.matcher(updateGmail).matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide a valid email!", ButtonType.OK);
            alert.show();
            return false;
        }
        return true;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //For set User can input only number
        age.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!newValue.matches("\\d*")) {
                    age.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        phoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!newValue.matches("\\d*")) {
                    phoneNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        gender.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        if ("Male".equals(User.getGender()) || "Female".equals(User.getGender()) || "Other".equals(User.getGender())) {
            gender.setValue(User.getGender());
        }
        name.setText(User.getName());
        imgURL.setText(User.getImageURL());
        phoneNumber.setText(User.getPhone());
        email.setText(User.getEmail());
        age.setText(User.getAge());
        String updateName = name.getText();
        String updateGmail = email.getText();
        String updateAge = age.getText();
        String updateGetGender = gender.getValue();

        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        boolean hasApplied = false;
        try {
            hasApplied = database.hasAppliedAsDoctor(User.getID());
        } catch (SQLException ex) {
            System.err.println("Error checking doctor application status: " + ex.getMessage());
            // Handle the exception appropriately, perhaps by logging it or showing an error to the user
        }

        if (hasApplied || "".equals(updateName) || updateName == null || "".equals(updateGmail) || updateGmail == null || "".equals(updateAge) || updateAge == null || "".equals(updateGetGender) || updateGetGender == null) {
            applyAsDoctorattr.setDisable(true);
            if (hasApplied) {
                applyAsDoctorattr.setText("Already Applied");
            } else {
                applyAsDoctorattr.setText("Fill all required fields to apply");
            }
        } else {
            applyAsDoctorattr.setDisable(false);
            applyAsDoctorattr.setText("Apply As A Doctor");
        }
        
    }

}
