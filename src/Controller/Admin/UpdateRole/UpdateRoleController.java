/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin.UpdateRole;

import Model.AproveDoctor;
import Model.MYSQLDatabaseOp;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sakib
 */
public class UpdateRoleController implements Initializable {

    @FXML
    private void handleAproveDoctor(ActionEvent e) {
        AproveDoctor selectAproveDoctor = doctorApply.getSelectionModel().getSelectedItem();

        if (selectAproveDoctor == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select an Doctor first!", ButtonType.OK);
            alert.show();
            return;
        }
        String id = selectAproveDoctor.getId();
        try {
            MYSQLDatabaseOp database = new MYSQLDatabaseOp();
            boolean updateFlag = database.handleUpdateRole(Integer.parseInt(id), "doctor");
            if (updateFlag == true) {
                loadUser();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Aproved Sucessfully", ButtonType.OK);
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not Found", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException ee) {
            System.out.println(ee);
        }
    }
    @FXML
    private void handleDeclineDoctor(ActionEvent e) {
        AproveDoctor selectAproveDoctor = doctorApply.getSelectionModel().getSelectedItem();

        if (selectAproveDoctor == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select an Doctor first!", ButtonType.OK);
            alert.show();
            return;
        }
        String id = selectAproveDoctor.getId();
        try {
            MYSQLDatabaseOp database = new MYSQLDatabaseOp();
            boolean updateFlag = database.handleUpdateRole(Integer.parseInt(id), "user");
            if (updateFlag == true) {
                loadUser();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Declined Sucessfully", ButtonType.OK);
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not Found", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException ee) {
            System.out.println(ee);
        }
    }

    void loadUser() {
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        try {
            ObservableList<AproveDoctor> appplyDoctor = database.allDoctorApply();
            doctorApply.setItems(appplyDoctor);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private TableView<AproveDoctor> doctorApply;
    @FXML
    private TableColumn<AproveDoctor, String> name;
    @FXML
    private TableColumn<AproveDoctor, String> id;
    @FXML
    private TableColumn<AproveDoctor, String> email;
    @FXML
    private TableColumn<AproveDoctor, String> age;
    @FXML
    private TableColumn<AproveDoctor, String> gender;
    @FXML
    private TableColumn<AproveDoctor, String> phone;
    @FXML
    private TableColumn<AproveDoctor, String> address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        loadUser();
    }

}
