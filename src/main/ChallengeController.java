package main;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

public class ChallengeController {

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem catMenuItem;

    @FXML
    private DatePicker datePicker;

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

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public MenuItem getCatMenuItem() {
        return catMenuItem;
    }

    public DatePicker getDatePicker() {
        return datePicker;
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
}
