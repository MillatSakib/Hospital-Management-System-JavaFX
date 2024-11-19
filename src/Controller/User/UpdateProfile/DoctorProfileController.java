/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.User.UpdateProfile;

import Model.Admin;
import Model.MYSQLDatabaseOp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Model.Doctor;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class DoctorProfileController implements Initializable {

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
    private TextArea address;
    @FXML
    private ComboBox<String> gender;

    @FXML
    private void handleUpdateProfile(ActionEvent e) throws Exception {
        String updateName = name.getText();
        String updateImgURL = imgURL.getText();
        String updatePhoneNumber = phoneNumber.getText();
        String updateGmail = email.getText();
        String updateAge = age.getText();
        String getGender = gender.getValue();
        String updateAddress = address.getText();
        if (updateValidation()) {

            String query = "UPDATE users SET Name='" + updateName + "', ImageURL='" + updateImgURL + "', ContactNumber='" + updatePhoneNumber + "', Email='" + updateGmail + "', Gender='" + getGender + "', Age='" + updateAge + "', Address='" + updateAddress + "' where ID=" + Doctor.getID() + "";
            MYSQLDatabaseOp updateUserData = new MYSQLDatabaseOp();
            if (updateUserData.handleUpdateUserData(query)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your profile Updated Successfully!", ButtonType.OK);
                alert.show();
            }
        }
    }

    boolean updateValidation() {
        String updateName = name.getText();
        String updatePhoneNumber = phoneNumber.getText();
        String updateGmail = email.getText();
        String updateAge = age.getText();
        String getGender = gender.getValue();
        String updateAddress = address.getText();

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
        if ("".equals(updateAddress) || updateAddress == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Address!", ButtonType.OK);
            alert.show();
            return false;
        }

        if ("".equals(updateName) || updateName == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Name!", ButtonType.OK);
            alert.show();
            return false;
        }
        if ("".equals(updateName) || updateName == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Name!", ButtonType.OK);
            alert.show();
            return false;
        }
        if ("".equals(updatePhoneNumber) || updatePhoneNumber == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide your Phone Number!", ButtonType.OK);
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

    /**
     * Initializes the controller class.
     */
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
        name.setText(Doctor.getName());
        imgURL.setText(Doctor.getImageURL());
        phoneNumber.setText(Doctor.getPhone());
        email.setText(Doctor.getEmail());
        age.setText(Doctor.getAge());
        address.setText(Doctor.getAddress());

        gender.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        if ("Male".equals(Doctor.getGender()) || "Female".equals(Doctor.getGender()) || "Other".equals(Doctor.getGender())) {
            gender.setValue(Doctor.getGender());
        }

    }

}
