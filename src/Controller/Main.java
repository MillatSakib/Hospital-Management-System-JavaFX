package Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stageRef;
    public static String role = "";
    public static String imgURL = "";
    public static String DoctorID = "";
    @Override
    
    //database testing
    public void start(Stage primaryStage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/View/Auth/Base.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setMaxHeight(593);
        primaryStage.setMaxWidth(990);
        primaryStage.setMinWidth(930);
        primaryStage.setMinHeight(490);
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
        stageRef = primaryStage;
    }

    public static void main(String[] args) {
        
        launch(args);
    }
    
}
