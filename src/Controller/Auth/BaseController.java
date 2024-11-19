package Controller.Auth;

import Model.MYSQLDatabaseOp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class BaseController implements Initializable {

    @FXML
    private AnchorPane authChangeView;
    private static AnchorPane staticAuthChangeView;

    public static void goToRegister() throws Exception {
        Parent register = FXMLLoader.load(BaseController.class.getResource("/View/Auth/Register/Register.fxml"));
        staticAuthChangeView.getChildren().setAll(register);
    }

    public static void goToLogin() throws Exception {
        Parent register = FXMLLoader.load(BaseController.class.getResource("/View/Auth/Login/Login.fxml"));
        staticAuthChangeView.getChildren().setAll(register);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            staticAuthChangeView = authChangeView;
            Parent login = FXMLLoader.load(getClass().getResource("/View/Auth/Login/Login.fxml"));
            authChangeView.getChildren().setAll(login);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
