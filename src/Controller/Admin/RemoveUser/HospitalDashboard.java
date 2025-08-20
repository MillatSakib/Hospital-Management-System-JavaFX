/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin.RemoveUser;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author sakib
 */

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HospitalDashboard {

    public void showDashboard(Stage ownerStage) {
        Stage stage = new Stage();  // NEW STAGE
        stage.initOwner(ownerStage); // Set main window as parent
        stage.initModality(Modality.APPLICATION_MODAL); // Make it modal

        // --- CARD VIEW DATA ---
        VBox cardBox = new VBox(10);
        cardBox.setPadding(new Insets(10));

        Label totalPatients = createCard("Total Patients", "120");
        Label totalDoctors = createCard("Total Doctors", "25");
        Label totalAppointments = createCard("Appointments This Month", "340");

        cardBox.getChildren().addAll(totalPatients, totalDoctors, totalAppointments);

        // --- PIE CHART ---
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Appointments by Specialization");
        pieChart.getData().add(new PieChart.Data("Cardiology", 100));
        pieChart.getData().add(new PieChart.Data("Neurology", 80));
        pieChart.getData().add(new PieChart.Data("Orthopedics", 50));
        pieChart.getData().add(new PieChart.Data("Others", 110));

        // --- LINE CHART ---
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day of Month");
        yAxis.setLabel("Appointments");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Appointments Trend");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("August 2025");
        series.getData().add(new XYChart.Data<>(1, 10));
        series.getData().add(new XYChart.Data<>(5, 25));
        series.getData().add(new XYChart.Data<>(10, 40));
        series.getData().add(new XYChart.Data<>(15, 30));
        series.getData().add(new XYChart.Data<>(20, 50));
        series.getData().add(new XYChart.Data<>(25, 60));

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

    private Label createCard(String title, String value) {
        Label card = new Label(title + ": " + value);
        card.setStyle("-fx-font-size: 18px; -fx-background-color: #f2f2f2; "
                + "-fx-padding: 15px; -fx-border-radius: 8px; "
                + "-fx-background-radius: 8px; -fx-font-weight: bold;");
        return card;
    }
}
