package main;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class ChallengeController {

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Button addOption;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button minusButton;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button plusButton;

    @FXML
    private Button remOption;

    @FXML
    private TableView<?> resTableView;

    @FXML
    private MenuItem resetMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private TextField searchField;

    @FXML
    private DatePicker startDate;

    @FXML
    private ToggleButton switchField;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Label valLabel;

    @FXML
    private HBox wheelBox;

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public Button getAddOption() {
        return addOption;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public Button getMinusButton() {
        return minusButton;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public Button getPlusButton() {
        return plusButton;
    }

    public Button getRemOption() {
        return remOption;
    }

    public TableView<?> getResTableView() {
        return resTableView;
    }

    public MenuItem getResetMenuItem() {
        return resetMenuItem;
    }

    public MenuItem getSaveMenuItem() {
        return saveMenuItem;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public ToggleButton getSwitchField() {
        return switchField;
    }

    public TreeView<String> getTreeView() {
        return treeView;
    }

    public Label getValLabel() {
        return valLabel;
    }

    public HBox getWheelBox() {
        return wheelBox;
    }
}
