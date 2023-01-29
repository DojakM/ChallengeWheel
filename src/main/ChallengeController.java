package main;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class ChallengeController {

    @FXML
    private TextField valueField;
    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TableColumn<?, ?> catTable;

    @FXML
    private TableColumn<?, ?> dateTable;

    @FXML
    private TableColumn<?, ?> doneTable;

    @FXML
    private TableColumn<?, ?> optTable;

    @FXML
    private PieChart pieChart;

    @FXML
    private MenuItem resetMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private Button addOption;

    @FXML
    private Button remOption;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private HBox wheelBox;

    public TextField getValueField() {
        return valueField;
    }

    public Button getAddOption() {
        return addOption;
    }

    public Button getRemOption() {
        return remOption;
    }

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public TableColumn<?, ?> getCatTable() {
        return catTable;
    }

    public TableColumn<?, ?> getDateTable() {
        return dateTable;
    }

    public TableColumn<?, ?> getDoneTable() {
        return doneTable;
    }

    public TableColumn<?, ?> getOptTable() {
        return optTable;
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

    public TreeView<String> getTreeView() {
        return treeView;
    }

    public HBox getWheelBox() {
        return wheelBox;
    }
}
