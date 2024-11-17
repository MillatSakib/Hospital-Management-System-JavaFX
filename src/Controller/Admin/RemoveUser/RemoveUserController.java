package Controller.Admin.RemoveUser;

import Model.MYSQLDatabaseOp;
import Model.RemoveUserContainer;
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
    private void handleRmoveUser(ActionEvent e) {
        RemoveUserContainer selectedUser = removeUserTable.getSelectionModel().getSelectedItem();
        MYSQLDatabaseOp database = new MYSQLDatabaseOp();
        String ID = selectedUser.getID();
        System.out.println(ID);
        String query = "DELETE FROM users WHERE ID = '" + ID + "'";
        try {
            boolean deleteUserFlag = database.handleRemoveUser(query);
            if (deleteUserFlag == true) {
                loadUser();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted Sucessfully", ButtonType.OK);
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
        String query = "Select * FROM users WHERE Role!='admin'";
        try {
            ObservableList<RemoveUserContainer> allUserOption = database.allUserForRemove(query);
            removeUserTable.setItems(allUserOption);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        loadUser();

    }

}
