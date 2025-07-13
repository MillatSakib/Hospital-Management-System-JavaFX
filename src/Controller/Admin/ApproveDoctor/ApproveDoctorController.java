package Controller.Admin.ApproveDoctor;

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

public class ApproveDoctorController implements Initializable {

    @FXML
    private TableView<AproveDoctor> allDoctorApplyTable;
    @FXML
    private TableColumn<AproveDoctor, String> name;
    @FXML
    private TableColumn<AproveDoctor, String> id;
    @FXML
    private TableColumn<AproveDoctor, String> email;
    @FXML
    private TableColumn<AproveDoctor, String> gender;
    @FXML
    private TableColumn<AproveDoctor, String> age;
    @FXML
    private TableColumn<AproveDoctor, String> phone;
    @FXML
    private TableColumn<AproveDoctor, String> address;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            MYSQLDatabaseOp database = new MYSQLDatabaseOp();
            ObservableList<AproveDoctor> allDoctorApply = database.allDoctorApply();
            allDoctorApplyTable.setItems(allDoctorApply);
        } catch (SQLException ee) {
            System.out.println(ee);
        }
    }

    @FXML
    private void handleApprove(ActionEvent e) throws Exception {
        AproveDoctor selectedDoctor = allDoctorApplyTable.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a doctor to approve!", ButtonType.OK);
            alert.show();
            return;
        }

        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        boolean success = database.handleUpdateRole(Integer.parseInt(selectedDoctor.getId()), "doctor");

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Doctor approved successfully!", ButtonType.OK);
            alert.show();
            // Refresh the table
            ObservableList<AproveDoctor> allDoctorApply = database.allDoctorApply();
            allDoctorApplyTable.setItems(allDoctorApply);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error approving doctor!", ButtonType.OK);
            alert.show();
        }
    }
}
