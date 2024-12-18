package Model;

import Controller.Auth.Login.LoginController;
import Controller.Auth.Register.RegisterController;
import Controller.Main;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;
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


    private static boolean isValidURL(String urlString) {
        try {
            new URL(urlString);
            return true; 
        } catch (MalformedURLException e) {
            return false; 
        }
    }
    
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

    private void userDataFileUpdaste(String email, String pass){
        
    try (FileWriter writer = new FileWriter("confedintioal.data")) {
            writer.write(email+"\n"+pass);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
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
                    String phone = resultSet.getString("ContactNumber");
                    String age = resultSet.getString("Age");
                    String gender = resultSet.getString("Gender");
                    String specialization = resultSet.getString("Specialization");
                    String doctorID = resultSet.getString("DoctorID");
                    String address = resultSet.getString("Address");
                    if ("doctor".equals(role)) {
                        userData = new Doctor(id, name, role, imageURL, email, password, phone, age, gender, specialization, doctorID, address);
                    } else if ("admin".equals(role)) {
                        userData = new Admin(id, name, role, imageURL, email, password, phone, age, gender, address);
                    } else {
                        userData = new User(id, name, role, imageURL, email, password, phone, age, gender);
                    }
                    userDataFileUpdaste(email,password);
                    Main.role = resultSet.getString("Role");
                    Main.imgURL = isValidURL(imageURL) ? imageURL : "/View/images/person.png";
                    Main.DoctorID = doctorID != null ? doctorID : "";
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

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand); ResultSet resultSet = statement.executeQuery()) {
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

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement checkStatement = connection.prepareStatement(sqlCheck); PreparedStatement insertStatement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS)) {

            checkStatement.setString(1, email);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                RegisterController.faildmsgSet.setText("Email already exists.");
                return;
            }
            User.Email=email;
            Main.role = "user";
            Main.imgURL="/View/images/person.png";
            insertStatement.setString(1, email);
            insertStatement.setString(2, password);
            insertStatement.setString(3, "user");
            int rowsInserted = insertStatement.executeUpdate();

            if (rowsInserted > 0) {
                  // Retrieve the generated primary key
            ResultSet keys = insertStatement.getGeneratedKeys();
            if (keys.next()) {
                int userId = keys.getInt(1);
                User.ID = userId;
            }
                Main.role = "user";
                Parent root = FXMLLoader.load(getClass().getResource("/View/Patient/BaseUI.fxml"));
                Scene change = new Scene(root);
                Main.stageRef.setScene(change);
                userDataFileUpdaste(email,password);
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

        String sqlCommand = "INSERT INTO appoinmentdetails (PatientName, DoctorName, PatientID, DoctorID, Problem, Prescription, Visited, GenerateID, ContactNumber, Email, Gender, Age) "
                + "VALUES (?, ?, ?, ?, ?, NULL, 0, NULL, ?, ?, ?, ?);";

        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

            statement.setString(1, patientName);
            statement.setString(2, doctorName);
            statement.setString(3, patientID);
            statement.setString(4, doctorID);
            statement.setString(5, problem);
            statement.setString(6, User.Phone);
            statement.setString(7, User.Email);
            statement.setString(8, User.Gender);
            statement.setString(9, User.Age);

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

    public ObservableList<AllPrescription> prescriptions(String query) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AllPrescription> prescriptionList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String doctorName = resultSet.getString("DoctorName");
                String doctorId = resultSet.getString("DoctorID");
                String problem = resultSet.getString("Problem");
                String prescription = resultSet.getString("Prescription");

                prescriptionList.add(new AllPrescription(doctorName, doctorId, problem, prescription));
            }

            if (prescriptionList.isEmpty()) {
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
        return prescriptionList;
    }

    public ObservableList<AllAppoinment> allAppoinmentPatient(String query) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AllAppoinment> prescriptionList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String contactNumber = resultSet.getString("ContactNumber");
                String doctorId = resultSet.getString("DoctorID");
                String problem = resultSet.getString("Problem");
                String visited = resultSet.getString("Visited");
                String doctorName = resultSet.getString("DoctorName");
                if ("1".equals(visited)) {
                    visited = "Done";
                } else {
                    visited = "Pending";
                }
                prescriptionList.add(new AllAppoinment(doctorName, doctorId, problem, visited, contactNumber));
            }

            if (prescriptionList.isEmpty()) {
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
        return prescriptionList;
    }

    public ObservableList<RemoveUserContainer> allUserForRemove(String query) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<RemoveUserContainer> allUserList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String Id = resultSet.getString("ID");
                String role = resultSet.getString("Role");
                String contactNumber = resultSet.getString("ContactNumber");

                allUserList.add(new RemoveUserContainer(name, Id, role, contactNumber));
            }

            if (allUserList.isEmpty()) {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("Error!! No matching found.");
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Login Failed! Server Error!");
            });
            throw new SQLException("Error occurred while fetching doctors: " + e.getMessage(), e);
        }
        return allUserList;
    }

    public boolean handleRemoveUser(String deleteQuery) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if (rowsAffected > 0) {
                return true;
            } else {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("No records found to delete.");
                });
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Error executing delete query.");
            });
            throw new SQLException("Error occurred while executing delete query: " + e.getMessage(), e);
        }
    }

    public boolean handleUpdateRole(String deleteQuery) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if (rowsAffected > 0) {
                return true;
            } else {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("No records found to delete.");
                });
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Error executing delete query.");
            });
            throw new SQLException("Error occurred while executing delete query: " + e.getMessage(), e);
        }
    }

    public ObservableList<AproveDoctor> allDoctorApply(String query) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AproveDoctor> appliedDoctor = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String Id = resultSet.getString("ID");
                String email = resultSet.getString("Email");
                String age = resultSet.getString("Age");
                String gender = resultSet.getString("Gender");
                String phone = resultSet.getString("ContactNumber");
                String address = resultSet.getString("Address");

                appliedDoctor.add(new AproveDoctor(name, Id, email, age, gender, phone, address));
            }

            if (appliedDoctor.isEmpty()) {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("Error!! No matching found.");
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Login Failed! Server Error!");
            });
            throw new SQLException("Error occurred while fetching doctors: " + e.getMessage(), e);
        }
        return appliedDoctor;
    }

    public ObservableList<AllPatientForDoctor> allPatient(String query) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AllPatientForDoctor> allPatient = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("PatientName");
                String Id = resultSet.getString("PatientID");
                String email = resultSet.getString("Email");
                String age = resultSet.getString("Age");
                String gender = resultSet.getString("Gender");
                String problem = resultSet.getString("Problem");
                String prescription = resultSet.getString("Prescription");

                allPatient.add(new AllPatientForDoctor(name, Id, email, gender, age, problem, prescription));
            }

            if (allPatient.isEmpty()) {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("Error!! No matching found.");
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Login Failed! Server Error!");
            });
            throw new SQLException("Error occurred while fetching doctors: " + e.getMessage(), e);
        }
        return allPatient;
    }

    public boolean handleUpdateUserData(String deleteQuery) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if (rowsAffected > 0) {
                return true;
            } else {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("No records found to delete.");
                });
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Error executing delete query.");
            });
            throw new SQLException("Error occurred while executing delete query: " + e.getMessage(), e);
        }
    }

    public boolean handleApplyAsDoctor(String deleteQuery) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if (rowsAffected > 0) {
                return true;
            } else {
                Platform.runLater(() -> {
                    LoginController.setTextOther.setText("No records found to delete.");
                });
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                LoginController.setTextOther.setText("Error executing delete query.");
            });
            throw new SQLException("Error occurred while executing delete query: " + e.getMessage(), e);
        }
    }
    
        public boolean givePrescription(String prescriptionQuery) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(prescriptionQuery);
            if (rowsAffected > 0) {
                return true;
            } else {
                Platform.runLater(() -> {
                    System.out.println("No records found to delete.");
                });
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                System.out.println("Error executing submit query.");
            });
            throw new SQLException("Error occurred while executing delete query: " + e.getMessage(), e);
        }
    }
    
    public ObservableList<GivePrescription> handleAllAppoinmentForDoctor(String query)throws Exception{
    String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<GivePrescription> patientList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("PatientName");
                String gender = resultSet.getString("Gender");
                String age = resultSet.getString("Age");
                String problem = resultSet.getString("Problem");
                String patientID = resultSet.getString("PatientID");
                patientList.add(new GivePrescription(name, gender, age, problem,patientID));
            }

            if (patientList.isEmpty()) {
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
        return patientList;
 
    }

}
