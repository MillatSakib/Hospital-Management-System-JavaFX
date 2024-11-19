package Controller.Patient.Appoinment;

import Controller.Main;
import Model.AppoinmentDoctorList;
import Model.MYSQLDatabaseOp;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Doctor;

public class AppoinmentController implements Initializable {

    @FXML
    private ComboBox<String> specialisDoctors;
    @FXML
    private TextArea getProblem;
    @FXML
    private TableView<AppoinmentDoctorList> doctorTableView;
    @FXML
    private TableColumn<AppoinmentDoctorList, String> doctorId;
    @FXML
    private TableColumn<AppoinmentDoctorList, String> name;

    ObservableList<AppoinmentDoctorList> list = FXCollections.observableArrayList();

    @FXML
    private void handleSearch(ActionEvent e) throws Exception {
        String selectedValue = specialisDoctors.getValue();
        String problem = getProblem.getText();
        if (selectedValue == null || problem == null || problem.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please provide all details!", ButtonType.OK);
            alert.show();
            return;
        }
        MYSQLDatabaseOp dbOp = new MYSQLDatabaseOp();
        String query;
        if("doctor".equals(Main.role)){
         query = "SELECT * FROM users WHERE Specialization='" + selectedValue + "' AND DoctorID!='"+Doctor.getDoctorID()+"'";
        }else{
         query = "SELECT * FROM users WHERE Specialization='" + selectedValue + "'";
        }
        ObservableList<AppoinmentDoctorList> doctors = dbOp.handleNeededDoctor(query);
        doctorTableView.setItems(doctors);
    }

    @FXML
    private void handleAppoinment(ActionEvent e) throws Exception {
        AppoinmentDoctorList selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a doctor!", ButtonType.OK);
            alert.show();
            return;
        }

        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        boolean success = database.bookAppointment(User.getName(), selectedDoctor.nameProperty().get(), User.getID(), selectedDoctor.idProperty().get(), getProblem.getText());
        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Completed");
            alert.setContentText("Appointment booked successfully!");
            alert.showAndWait();
            getProblem.setText("");
            list.clear();
            doctorTableView.setItems(list);
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING, "Failed to book the appointment.", ButtonType.OK);
            alert.show();
        }

        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        doctorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        doctorTableView.setItems(list); // Updated reference

        specialisDoctors.setItems(FXCollections.observableArrayList("Cardiologist", "Neurologist", "Dermatologist"));
    }
}
