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
    public void handleQueryLogin(String email, String password) throws Exception {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        User userData = null;
        String sqlCommand = "SELECT u.UserID, u.Name, r.RoleName, u.ImageURL, u.Email, u.Password, u.ContactNumber, u.Age, u.Gender, u.Address, d.DoctorCode, s.SpecializationName " +
                            "FROM Users u " +
                            "JOIN Roles r ON u.RoleID = r.RoleID " +
                            "LEFT JOIN Doctors d ON u.UserID = d.UserID " +
                            "LEFT JOIN Specializations s ON d.SpecializationID = s.SpecializationID " +
                            "WHERE u.Email = ? AND u.Password = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("UserID");
                    String name = resultSet.getString("Name");
                    String role = resultSet.getString("RoleName");
                    String imageURL = resultSet.getString("ImageURL");
                    String userEmail = resultSet.getString("Email");
                    String userPassword = resultSet.getString("Password");
                    String phone = resultSet.getString("ContactNumber");
                    String age = resultSet.getString("Age");
                    String gender = resultSet.getString("Gender");
                    String address = resultSet.getString("Address");
                    String doctorCode = resultSet.getString("DoctorCode");
                    String specialization = resultSet.getString("SpecializationName");

                    if ("doctor".equals(role)) {
                        userData = new Doctor(id, name, imageURL, userEmail, userPassword, phone, age, gender, specialization, doctorCode, address);
                    } else if ("admin".equals(role)) {
                        userData = new Admin(id, name, imageURL, userEmail, userPassword, phone, age, gender, address);
                    } else {
                        userData = new User(id, name, imageURL, userEmail, userPassword, phone, age, gender);
                    }
                    userDataFileUpdaste(userEmail,userPassword);
                    Main.role = role;
                    Main.imgURL = isValidURL(imageURL) ? imageURL : "/View/images/person.png";
                    Main.DoctorID = doctorCode != null ? doctorCode : "";
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
            throw new Exception("Error occurred during login: " + e.getMessage());
        }
    }

    public ObservableList<AppoinmentDoctorList> handleNeededDoctor(String specialization) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AppoinmentDoctorList> doctorList = FXCollections.observableArrayList();
        String sqlCommand = "SELECT d.DoctorID, d.DoctorCode, u.Name FROM Doctors d " +
                            "JOIN Users u ON d.UserID = u.UserID " +
                            "JOIN Specializations s ON d.SpecializationID = s.SpecializationID " +
                            "WHERE s.SpecializationName = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, specialization);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String doctorCode = resultSet.getString("DoctorCode");
                int doctorId = resultSet.getInt("DoctorID");
                doctorList.add(new AppoinmentDoctorList(doctorCode, name, doctorId));
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
        String sqlInsert = "INSERT INTO Users (Email, Password, RoleID) VALUES (?, ?, (SELECT RoleID FROM Roles WHERE RoleName = 'user'))";

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

    public boolean bookAppointment(int patientId, int doctorId, String problem) {
    String dbName = "hospital-manament-system";
    String fullURL = URL + "/" + dbName;

    String checkPatientSql = "SELECT PatientID FROM Patients WHERE UserID = ?";
    String insertPatientSql = "INSERT INTO Patients (UserID) VALUES (?)";
    String sqlCommand = "INSERT INTO Appointments (PatientID, DoctorID, Problem, Visited) VALUES (?, ?, ?, 0)";

    try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD)) {
        connection.setAutoCommit(false);

        int patientTableId = -1;

        // Check if patient exists
        try (PreparedStatement checkStmt = connection.prepareStatement(checkPatientSql)) {
            checkStmt.setInt(1, patientId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                patientTableId = rs.getInt("PatientID");
            }
        }

        // If patient does not exist, insert them
        if (patientTableId == -1) {
            try (PreparedStatement insertStmt = connection.prepareStatement(insertPatientSql, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setInt(1, patientId);
                insertStmt.executeUpdate();
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    patientTableId = generatedKeys.getInt(1);
                }
            }
        }

        // Book appointment
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setInt(1, patientTableId);
            statement.setInt(2, doctorId);
            statement.setString(3, problem);
            int rowsInserted = statement.executeUpdate();
            connection.commit();
            return rowsInserted > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while handling the appointment: " + e.getMessage());
    }
    return false;
}

    public ObservableList<AllPrescription> prescriptions(int patientId) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AllPrescription> prescriptionList = FXCollections.observableArrayList();
        String query = "SELECT u.Name AS DoctorName, d.DoctorCode, a.Problem, p.PrescriptionText " +
                       "FROM Prescriptions p " +
                       "JOIN Appointments a ON p.AppointmentID = a.AppointmentID " +
                       "JOIN Doctors d ON a.DoctorID = d.DoctorID " +
                       "JOIN Users u ON d.UserID = u.UserID " +
                       "JOIN Patients pa ON a.PatientID = pa.PatientID " +
                       "WHERE pa.UserID = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String doctorName = resultSet.getString("DoctorName");
                String doctorId = resultSet.getString("DoctorCode");
                String problem = resultSet.getString("Problem");
                String prescription = resultSet.getString("PrescriptionText");

                prescriptionList.add(new AllPrescription(doctorName, doctorId, problem, prescription));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while fetching prescriptions: " + e.getMessage(), e);
        }
        return prescriptionList;
    }

    public ObservableList<AllAppoinment> allAppoinmentPatient(int patientId) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AllAppoinment> appointmentList = FXCollections.observableArrayList();
        String query = "SELECT u.Name AS DoctorName, d.DoctorCode, a.Problem, a.Visited, u.ContactNumber " +
                       "FROM Appointments a " +
                       "JOIN Doctors d ON a.DoctorID = d.DoctorID " +
                       "JOIN Users u ON d.UserID = u.UserID " +
                       "JOIN Patients p ON a.PatientID = p.PatientID " +
                       "WHERE p.UserID = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String contactNumber = resultSet.getString("ContactNumber");
                String doctorId = resultSet.getString("DoctorCode");
                String problem = resultSet.getString("Problem");
                boolean visited = resultSet.getBoolean("Visited");
                String doctorName = resultSet.getString("DoctorName");
                String visitedStatus = visited ? "Done" : "Pending";

                appointmentList.add(new AllAppoinment(doctorName, doctorId, problem, visitedStatus, contactNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while fetching appointments: " + e.getMessage(), e);
        }
        return appointmentList;
    }

    public ObservableList<RemoveUserContainer> allUserForRemove() throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<RemoveUserContainer> allUserList = FXCollections.observableArrayList();
        String query = "SELECT u.UserID, u.Name, r.RoleName, u.ContactNumber FROM Users u JOIN Roles r ON u.RoleID = r.RoleID WHERE r.RoleName != 'admin'";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String Id = resultSet.getString("UserID");
                String role = resultSet.getString("RoleName");
                String contactNumber = resultSet.getString("ContactNumber");

                allUserList.add(new RemoveUserContainer(name, Id, role, contactNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while fetching users: " + e.getMessage(), e);
        }
        return allUserList;
    }

    public boolean handleRemoveUser(int userId) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        String deleteUserQuery = "DELETE FROM Users WHERE UserID = ?";
        String deleteDoctorQuery = "DELETE FROM Doctors WHERE UserID = ?";
        String deletePatientQuery = "DELETE FROM Patients WHERE UserID = ?";
        String getPatientIdQuery = "SELECT PatientID FROM Patients WHERE UserID = ?";
        String getAppointmentsQuery = "SELECT AppointmentID FROM Appointments WHERE PatientID = ?";
        String deletePrescriptionsQuery = "DELETE FROM Prescriptions WHERE AppointmentID = ?";
        String deleteAppointmentsQuery = "DELETE FROM Appointments WHERE PatientID = ?";


        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD)) {
            connection.setAutoCommit(false);
            try{
                int patientId = -1;
                try (PreparedStatement getPatientIdStmt = connection.prepareStatement(getPatientIdQuery)) {
                    getPatientIdStmt.setInt(1, userId);
                    ResultSet rs = getPatientIdStmt.executeQuery();
                    if (rs.next()) {
                        patientId = rs.getInt("PatientID");
                    }
                }

                if (patientId != -1) {
                    try (PreparedStatement getAppointmentsStmt = connection.prepareStatement(getAppointmentsQuery)) {
                        getAppointmentsStmt.setInt(1, patientId);
                        ResultSet rs = getAppointmentsStmt.executeQuery();
                        while (rs.next()) {
                            int appointmentId = rs.getInt("AppointmentID");
                            try (PreparedStatement deletePrescriptionsStmt = connection.prepareStatement(deletePrescriptionsQuery)) {
                                deletePrescriptionsStmt.setInt(1, appointmentId);
                                deletePrescriptionsStmt.executeUpdate();
                            }
                        }
                    }

                    try (PreparedStatement deleteAppointmentsStmt = connection.prepareStatement(deleteAppointmentsQuery)) {
                        deleteAppointmentsStmt.setInt(1, patientId);
                        deleteAppointmentsStmt.executeUpdate();
                    }
                }

                try (PreparedStatement deleteDoctorStmt = connection.prepareStatement(deleteDoctorQuery)) {
                    deleteDoctorStmt.setInt(1, userId);
                    deleteDoctorStmt.executeUpdate();
                }

                try (PreparedStatement deletePatientStmt = connection.prepareStatement(deletePatientQuery)) {
                    deletePatientStmt.setInt(1, userId);
                    deletePatientStmt.executeUpdate();
                }

                try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery)) {
                    deleteUserStmt.setInt(1, userId);
                    int rowsAffected = deleteUserStmt.executeUpdate();
                    connection.commit();
                    return rowsAffected > 0;
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while removing user: " + e.getMessage(), e);
        }
    }

    public boolean handleUpdateRole(int userId, String roleName) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        String updateQuery = "UPDATE Users SET RoleID = (SELECT RoleID FROM Roles WHERE RoleName = ?) WHERE UserID = ?";
        String updateDoctorStatusQuery = "UPDATE Doctors SET Status = 'approved' WHERE UserID = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD)) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(updateQuery);
                 PreparedStatement updateDoctorStatusStmt = connection.prepareStatement(updateDoctorStatusQuery)) {
                statement.setString(1, roleName);
                statement.setInt(2, userId);
                statement.executeUpdate();

                updateDoctorStatusStmt.setInt(1, userId);
                int rowsAffected = updateDoctorStatusStmt.executeUpdate();

                connection.commit();
                return rowsAffected > 0;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while updating role: " + e.getMessage(), e);
        }
    }

    public ObservableList<AproveDoctor> allDoctorApply() throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AproveDoctor> appliedDoctor = FXCollections.observableArrayList();
        String query = "SELECT u.UserID, u.Name, u.Email, u.Age, u.Gender, u.ContactNumber, u.Address " +
                       "FROM Users u " +
                       "JOIN Doctors d ON u.UserID = d.UserID " +
                       "WHERE d.Status = 'pending'";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String Id = resultSet.getString("UserID");
                String email = resultSet.getString("Email");
                String age = resultSet.getString("Age");
                String gender = resultSet.getString("Gender");
                String phone = resultSet.getString("ContactNumber");
                String address = resultSet.getString("Address");

                appliedDoctor.add(new AproveDoctor(name, Id, email, age, gender, phone, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while fetching doctor applications: " + e.getMessage(), e);
        }
        return appliedDoctor;
    }

    public ObservableList<AllPatientForDoctor> allPatient(int userId) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<AllPatientForDoctor> allPatient = FXCollections.observableArrayList();
        String getDoctorIdQuery = "SELECT DoctorID FROM Doctors WHERE UserID = ?";
        String query = "SELECT u.Name AS PatientName, p.PatientID, u.Email, u.Age, u.Gender, a.Problem, pr.PrescriptionText " +
                       "FROM Appointments a " +
                       "JOIN Patients p ON a.PatientID = p.PatientID " +
                       "JOIN Users u ON p.UserID = u.UserID " +
                       "LEFT JOIN Prescriptions pr ON a.AppointmentID = pr.AppointmentID " +
                       "WHERE a.DoctorID = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD)) {
            int doctorId = -1;
            try (PreparedStatement getDoctorIdStmt = connection.prepareStatement(getDoctorIdQuery)) {
                getDoctorIdStmt.setInt(1, userId);
                ResultSet rs = getDoctorIdStmt.executeQuery();
                if (rs.next()) {
                    doctorId = rs.getInt("DoctorID");
                }
            }

            if (doctorId != -1) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, doctorId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        String name = resultSet.getString("PatientName");
                        String Id = resultSet.getString("PatientID");
                        String email = resultSet.getString("Email");
                        String age = resultSet.getString("Age");
                        String gender = resultSet.getString("Gender");
                        String problem = resultSet.getString("Problem");
                        String prescription = resultSet.getString("PrescriptionText");

                        allPatient.add(new AllPatientForDoctor(name, Id, email, gender, age, problem, prescription));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while fetching patients: " + e.getMessage(), e);
        }
        return allPatient;
    }

    public boolean handleUpdateUserData(int userId, String name, String age, String gender, String address, String contactNumber) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        String updateQuery = "UPDATE Users SET Name = ?, Age = ?, Gender = ?, Address = ?, ContactNumber = ? WHERE UserID = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, name);
            statement.setString(2, age);
            statement.setString(3, gender);
            statement.setString(4, address);
            statement.setString(5, contactNumber);
            statement.setInt(6, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while updating user data: " + e.getMessage(), e);
        }
    }

    public boolean handleApplyAsDoctor(int userId, String specializationName, String doctorCode) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        String insertDoctorQuery = "INSERT INTO Doctors (UserID, SpecializationID, DoctorCode, Status) VALUES (?, (SELECT SpecializationID FROM Specializations WHERE SpecializationName = ?), ?, 'pending')";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD); PreparedStatement insertDoctorStmt = connection.prepareStatement(insertDoctorQuery)) {
            insertDoctorStmt.setInt(1, userId);
            insertDoctorStmt.setString(2, specializationName);
            insertDoctorStmt.setString(3, doctorCode);
            int rowsAffected = insertDoctorStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while applying as doctor: " + e.getMessage(), e);
        }
    }
    
        public boolean givePrescription(int appointmentId, String prescriptionText) throws SQLException {
        String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        String prescriptionQuery = "INSERT INTO Prescriptions (AppointmentID, PrescriptionText) VALUES (?, ?)";
        String updateAppointmentQuery = "UPDATE Appointments SET Visited = 1 WHERE AppointmentID = ?";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD)) {
            connection.setAutoCommit(false);
            try (PreparedStatement prescriptionStmt = connection.prepareStatement(prescriptionQuery);
                 PreparedStatement updateAppointmentStmt = connection.prepareStatement(updateAppointmentQuery)) {

                prescriptionStmt.setInt(1, appointmentId);
                prescriptionStmt.setString(2, prescriptionText);
                prescriptionStmt.executeUpdate();

                updateAppointmentStmt.setInt(1, appointmentId);
                int rowsAffected = updateAppointmentStmt.executeUpdate();

                connection.commit();
                return rowsAffected > 0;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while giving prescription: " + e.getMessage(), e);
        }
    }
    
    public ObservableList<GivePrescription> handleAllAppoinmentForDoctor(int userId)throws Exception{
    String dbName = "hospital-manament-system";
        String fullURL = URL + "/" + dbName;
        ObservableList<GivePrescription> patientList = FXCollections.observableArrayList();
        String getDoctorIdQuery = "SELECT DoctorID FROM Doctors WHERE UserID = ?";
        String query = "SELECT u.Name AS PatientName, u.Gender, u.Age, a.Problem, p.PatientID, a.AppointmentID " +
                       "FROM Appointments a " +
                       "JOIN Patients p ON a.PatientID = p.PatientID " +
                       "JOIN Users u ON p.UserID = u.UserID " +
                       "WHERE a.DoctorID = ? AND a.Visited = 0";

        try (Connection connection = DriverManager.getConnection(fullURL, USERNAME, PASSWORD)) {
            int doctorId = -1;
            try (PreparedStatement getDoctorIdStmt = connection.prepareStatement(getDoctorIdQuery)) {
                getDoctorIdStmt.setInt(1, userId);
                ResultSet rs = getDoctorIdStmt.executeQuery();
                if (rs.next()) {
                    doctorId = rs.getInt("DoctorID");
                }
            }

            if (doctorId != -1) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, doctorId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        String name = resultSet.getString("PatientName");
                        String gender = resultSet.getString("Gender");
                        String age = resultSet.getString("Age");
                        String problem = resultSet.getString("Problem");
                        String patientID = resultSet.getString("PatientID");
                        int appointmentID = resultSet.getInt("AppointmentID");
                        patientList.add(new GivePrescription(name, gender, age, problem, patientID, appointmentID));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while fetching appointments for doctor: " + e.getMessage(), e);
        }
        return patientList;
 
    }

}
