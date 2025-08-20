package Controller.Admin.RemoveUser;

import Model.MYSQLDatabaseOp;
import Model.RemoveUserContainer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;   // ✅ Import Scene
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveUserController implements Initializable {

    @FXML
    private TableView<RemoveUserContainer> removeUserTable;
    @FXML
    private TableColumn<RemoveUserContainer, String> userName;
    @FXML
    private TableColumn<RemoveUserContainer, String> userID;
    @FXML
    private TableColumn<RemoveUserContainer, String> role;
    @FXML
    private TableColumn<RemoveUserContainer, String> contactNumber;

    @FXML
    private void handleOpenDashboard(ActionEvent e) {
        HospitalDashboard dashboard = new HospitalDashboard();
        dashboard.showDashboard((Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow());
    }

    @FXML
    private void handleRmoveUser(ActionEvent e) {
        RemoveUserContainer selectedUser = removeUserTable.getSelectionModel().getSelectedItem();
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();

        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select a user!", ButtonType.OK);
            alert.show();
            return;
        }

        String ID = selectedUser.getID();
        try {
            boolean deleteUserFlag = database.handleRemoveUser(Integer.parseInt(ID));
            if (deleteUserFlag) {
                loadUser();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "User not Found", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException ee) {
            System.out.println(ee);
        }
    }

    private void loadUser() {
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        try {
            ObservableList<RemoveUserContainer> allUserOption = database.allUserForRemove();
            removeUserTable.setItems(allUserOption);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        loadUser();
    }
}
