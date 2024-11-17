package Model;

import Controller.Auth.Login.LoginController;
import Controller.Auth.Register.RegisterController;
import Controller.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        User userData = null;
        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    String role = resultSet.getString("Role");
                    String imageURL = resultSet.getString("ImageURL");
                    String email = resultSet.getString("Email");
                    String password = resultSet.getString("Password");
                    String phone = resultSet.getString("Phone");
                    String age = resultSet.getString("Age");
                    String gender = resultSet.getString("Gender");
                    userData = new User(id, name, role, imageURL, email, password, phone, age, gender);
                    Main.role = resultSet.getString("Role");
                    Main.imgURL = resultSet.getString("ImageURL");
                    //closing the window after successfully login
                    Parent root = FXMLLoader.load(getClass().getResource("/View/Patient/BaseUI.fxml"));
                    Scene change = new Scene(root);
                    Main.stageRef.setScene(change);
                } else {
                    LoginController.setTextOther.setText("Wrong Email or Password");
                }
            }
        } catch (SQLException e) {
        e.printStackTrace();
          LoginController.setTextOther.setText("Login Faild! Server Error!");
        throw new Exception("Error occurred during registration: " + e.getMessage());
    }
    }
    
    
    
public ObservableList<AppoinmentDoctorList> handleNeededDoctor(String sqlCommand) throws SQLException {
    String dbName = "hospital-manament-system";
    String fullURL = URL + "/" + dbName; 
    ObservableList<AppoinmentDoctorList> doctorList = FXCollections.observableArrayList();

    try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(sqlCommand);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            String doctorId = resultSet.getString("DoctorID");
            doctorList.add(new AppoinmentDoctorList(doctorId, name));
        }

        if (doctorList.isEmpty()) {
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Error!! No matching doctor found.");
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        Platform.runLater(() -> {
            LoginController.setTextOther.setText("Login Failed! Server Error!");
        });
        throw new SQLException("Error occurred while fetching doctors: " + e.getMessage(), e);
    }
    return doctorList;
}

        
        
    
public void handleRegister(String email, String password) throws Exception {
    String dbName = "hospital-manament-system";
    String fullURL = URL + "/" + dbName;

    String sqlCheck = "SELECT COUNT(*) FROM Users WHERE Email = ?";
    String sqlInsert = "INSERT INTO Users (Email, Password, Role) VALUES (?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD);
         PreparedStatement checkStatement = connection.prepareStatement(sqlCheck);
         PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

        checkStatement.setString(1, email);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next() && resultSet.getInt(1) > 0) {
            RegisterController.faildmsgSet.setText("Email already exists.");
            return;
        }

        insertStatement.setString(1, email);
        insertStatement.setString(2, password);
        insertStatement.setString(3, "user");
        int rowsInserted = insertStatement.executeUpdate();

        if (rowsInserted > 0) {
                Main.role = "user";
                Parent root = FXMLLoader.load(getClass().getResource("/View/Patient/BaseUI.fxml"));
                Scene change = new Scene(root);
                Main.stageRef.setScene(change);
        } else {
            RegisterController.faildmsgSet.setText("Registration Fainld!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
         RegisterController.faildmsgSet.setText("Registration Faild! Server Error!");
        throw new Exception("Error occurred during registration: " + e.getMessage());
    }
}


public boolean bookAppointment(String patientName, String doctorName, String patientID, String doctorID, String problem) {
   
    String sqlCommand = "INSERT INTO appoinmentdetails (PatientName, DoctorName, PatientID, DoctorID, Problem, Prescription, Visited, GenerateID) " +
                        "VALUES (?, ?, ?, ?, ?, NULL, 0, NULL);";

    String dbName = "hospital-manament-system";
    String fullURL = URL + "/" + dbName;

    try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

        statement.setString(1, patientName);
        statement.setString(2, doctorName);
        statement.setString(3, patientID); 
        statement.setString(4, doctorID); 
        statement.setString(5, problem);

        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            return true;
        } else {
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while handling the appointment: " + e.getMessage());
    }
    return false;
}




}