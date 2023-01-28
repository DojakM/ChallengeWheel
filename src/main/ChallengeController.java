package main;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ChallengeController {

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private StackedBarChart<?, ?> barChart;

    @FXML
    private MenuItem catMenuItem;

    @FXML
    private DatePicker datePicker;

    @FXML
    private DatePicker fromDate;

    @FXML
    private GridPane logGrid;

    @FXML
    private MenuItem optMenuItem;

    @FXML
    private GridPane optionsGrid;

    @FXML
    private PieChart pieChart;

    @FXML
    private MenuItem resetMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private DatePicker tilDate;

    @FXML
    private HBox wheelBox;

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public StackedBarChart<?, ?> getBarChart() {
        return barChart;
    }

    public MenuItem getCatMenuItem() {
        return catMenuItem;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public DatePicker getFromDate() {
        return fromDate;
    }

    public GridPane getLogGrid() {
        return logGrid;
    }

    public MenuItem getOptMenuItem() {
        return optMenuItem;
    }

    public GridPane getOptionsGrid() {
        return optionsGrid;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public MenuItem getResetMenuItem() {
        return resetMenuItem;
    }

    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public DatePicker getTilDate() {
        return tilDate;
    }

    public HBox getWheelBox() {
        return wheelBox;
    }
}
