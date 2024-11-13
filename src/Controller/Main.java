package Controller;

import Model.MYSQLDatabaseOp;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Md. Sohan Millat Sakib
 */

public class Main extends Application {
    
    @Override
    
    //database testing
    public void start(Stage primaryStage) throws Exception {
        MYSQLDatabaseOp dbOp = new MYSQLDatabaseOp();
        try {
            
            dbOp.handleQuery("vugijugi", "SELECT Roll,Name FROM students");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        Parent root = FXMLLoader.load(getClass().getResource("/View/Auth/Base.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setMaxHeight(593);
        primaryStage.setMaxWidth(990);
        primaryStage.setMinWidth(930);
        primaryStage.setMinHeight(490);
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
