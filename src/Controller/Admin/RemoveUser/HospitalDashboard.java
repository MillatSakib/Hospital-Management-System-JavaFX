/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin.RemoveUser;
import Model.MYSQLDatabaseOp;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalDashboard {

    public void showDashboard(Stage ownerStage) {
        MYSQLDatabaseOp dbOp = new MYSQLDatabaseOp();
        
        Stage stage = new Stage();  // NEW STAGE
        stage.initOwner(ownerStage); // Set main window as parent
        stage.initModality(Modality.APPLICATION_MODAL); // Make it modal

        // --- CARD VIEW DATA ---
        VBox cardBox = new VBox(10);
        cardBox.setPadding(new Insets(10));
        
        Label totalPatients = createCard("Total Patients", dbOp.intigerValueRetun("SELECT COUNT(UserID) FROM Users;"));
        Label totalDoctors = createCard("Total Doctors", dbOp.intigerValueRetun("SELECT COUNT(UserID) FROM Users WHERE RoleID=2;"));
        Label totalAppointments = createCard("Appointments This Month", dbOp.intigerValueRetun("SELECT COUNT(AppointmentID) FROM Appointments  WHERE MONTH(AppointmentDate) = MONTH(CURDATE()) AND YEAR(AppointmentDate) = YEAR(CURDATE());"));

        cardBox.getChildren().addAll(totalPatients, totalDoctors, totalAppointments);

        // --- PIE CHART ---
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Appointments by Specialization");
        pieChart.getData().add(new PieChart.Data("Cardiology", dbOp.intigerValueRetun("SELECT COUNT(AppointmentID) FROM Appointments A JOIN Doctors D ON A.DoctorID=D.DoctorID WHERE SpecializationID=1;")));
        pieChart.getData().add(new PieChart.Data("Neurology", dbOp.intigerValueRetun("SELECT COUNT(AppointmentID) FROM Appointments A JOIN Doctors D ON A.DoctorID=D.DoctorID WHERE SpecializationID=2;")));
        pieChart.getData().add(new PieChart.Data("Dermatology", dbOp.intigerValueRetun("SELECT COUNT(AppointmentID) FROM Appointments A JOIN Doctors D ON A.DoctorID=D.DoctorID WHERE SpecializationID=3;")));
        pieChart.getData().add(new PieChart.Data("Others", dbOp.intigerValueRetun("SELECT COUNT(AppointmentID) FROM Appointments A JOIN Doctors D ON A.DoctorID=D.DoctorID WHERE SpecializationID NOT IN(1,2,3);")));

        // --- LINE CHART ---
        // --- LINE CHART ---
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day of Month");
        yAxis.setLabel("Appointments");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Appointments Trend");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("This Month");

        // ✅ SQL to get appointments trend for fixed days
        String sql = """
            -- Generate 5-day intervals and count appointments
            SELECT d.DayOfMonth,
                   COALESCE(COUNT(a.AppointmentID), 0) AS TotalAppointments
            FROM (
                SELECT 5 AS DayOfMonth
                UNION ALL SELECT 10
                UNION ALL SELECT 15
                UNION ALL SELECT 20
                UNION ALL SELECT 25
                UNION ALL SELECT 31
            ) d
            LEFT JOIN Appointments a
                ON DAY(AppointmentDate) <= d.DayOfMonth
               AND DAY(AppointmentDate) > d.DayOfMonth - 5
            GROUP BY d.DayOfMonth
            ORDER BY d.DayOfMonth;
        """;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital-manament-system", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int day = rs.getInt("DayOfMonth");
                int total = rs.getInt("TotalAppointments");
                series.getData().add(new XYChart.Data<>(day, total));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        lineChart.getData().add(series);

        // --- CLOSE BUTTON ---
        Button closeBtn = new Button("Close Dashboard");
        closeBtn.setOnAction(e -> stage.close());

        VBox root = new VBox(20, cardBox, new HBox(20, pieChart, lineChart), closeBtn);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Hospital Dashboard");
        stage.showAndWait(); // BLOCKS until closed
    }

    private Label createCard(String title, int value) {
        Label card = new Label(title + ": " + value);
        card.setStyle("-fx-font-size: 18px; -fx-background-color: #f2f2f2; "
                + "-fx-padding: 15px; -fx-border-radius: 8px; "
                + "-fx-background-radius: 8px; -fx-font-weight: bold;");
        return card;
    }
}
