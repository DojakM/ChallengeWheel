package main;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class ChallengeController {

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
    private TreeView<?> treeView;

    @FXML
    private HBox wheelBox;

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

    public TreeView<?> getTreeView() {
        return treeView;
    }

    public HBox getWheelBox() {
        return wheelBox;
    }
}
