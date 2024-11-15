package Model;

import Controller.Auth.Login.LoginController;
import Controller.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MYSQLDatabaseOp {

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public MYSQLDatabaseOp() {
        this.URL = "jdbc:mysql://localhost:3306";
        this.USERNAME = "root";
        this.PASSWORD = "";
    }

    public MYSQLDatabaseOp(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void handleQuery(String dbName, String sqlCommand) throws SQLException {
        String fullURL = URL + "/" + dbName;
        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            if (sqlCommand.trim().toUpperCase().startsWith("SELECT")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String columnValue = resultSet.getString(1);
                        System.out.println("Column Value: " + columnValue);
                    }
                    System.out.println("Fulldata" + resultSet.getString(1));
                }
            } else {
                int affectedRows = statement.executeUpdate();
                System.out.println("Affected rows: " + affectedRows);
            }
        }
    }

    public void handleQueryLogin(String sqlCommand) throws Exception {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        UserData userData = null;
        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    String role = resultSet.getString("Role");
                    String imageURL = resultSet.getString("ImageURL");
                    String email = resultSet.getString("Email");
                    String password = resultSet.getString("Password");
                    userData = new UserData(id, name, role, imageURL, email, password);
                    System.out.println(userData.getName()+" "+userData.getRole()+" "+userData.getEmail()+" "+ userData.getPassword()+" "+userData.getImageURL());
                    
                    //closing the window after successfully login
                    Parent root = FXMLLoader.load(getClass().getResource("/View/DashBoard/DashBoard.fxml"));
                    Scene change = new Scene(root);
                    Main.stageRef.setScene(change);
                } else {
                    LoginController.setTextOther.setText("Wrong Email or Password");
                }
            }
        }
        
    }

}
