package controller.component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeComponentController implements Initializable{

    @FXML
    private LineChart<String, Number> chartBox; // String X-Axis (CategoryAxis), Number Y-Axis (NumberAxis)

    @FXML
    private Button refreshButton; // Button to reload data

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupChart();
        loadChartData();

        refreshButton.setOnAction(event -> refreshChart()); // Reload chart on button click
    }

    // 📌 Initial Chart Setup
    private void setupChart() {
        chartBox.setTitle("Portfolio Growth Over Time"); // Chart title
        chartBox.setLegendVisible(true); // Show legend
        chartBox.setLegendSide(javafx.geometry.Side.BOTTOM); // Position legend at the bottom
    }

    // 📌 Load Data into Chart
    private void loadChartData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Growth");

        // Adding sample data points
        series.getData().add(new XYChart.Data<>("Jan", 23));
        series.getData().add(new XYChart.Data<>("Feb", 45));
        series.getData().add(new XYChart.Data<>("Mar", 67));
        series.getData().add(new XYChart.Data<>("Apr", 17));
        series.getData().add(new XYChart.Data<>("May", 90));
        series.getData().add(new XYChart.Data<>("Jun", 120));

        chartBox.getData().add(series);
    }

    // 📌 Refresh Chart Data
    private void refreshChart() {
        chartBox.getData().clear(); // Clear old data
        loadChartData(); // Reload new data
    }


}
