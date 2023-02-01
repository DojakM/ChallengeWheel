package main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.ChallengeData;
import main.model.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ChallengePresenter {
    ChallengeController controller;
    ChallengeData challengeData = new ChallengeData();
    ChallengePresenter(ChallengeController controller) throws FileNotFoundException {
        this.controller = controller;
        setup();
        // Just MenuItem Functiality
        controller.getResetMenuItem().setOnAction(event -> {
            try {
                setup();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        controller.getSaveMenuItem().setOnAction(event -> {
            try {
                challengeData.writeFile();
                challengeData.writeResults();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        controller.getAddOption().setOnAction(e -> addOption());
        controller.getRemOption().setOnAction(e -> remOption());
        controller.getAboutMenuItem().setOnAction(e -> {
            try {
                aboutMenu();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        controller.getPlusButton().setOnAction(e->{
            if (!controller.getTreeView().getSelectionModel().isEmpty()){
                String opt = controller.getTreeView().getSelectionModel().getSelectedItem().getValue();
                SimpleStringProperty sio = challengeData.getOpt_val_map().get(opt);
                Integer val_sio = Integer.parseInt(sio.get()) + 1;
                sio.setValue(String.valueOf(val_sio));
            }
        });
        controller.getMinusButton().setOnAction(e->{
            if (!controller.getTreeView().getSelectionModel().isEmpty()){
                String opt = controller.getTreeView().getSelectionModel().getSelectedItem().getValue();
                SimpleStringProperty sio = challengeData.getOpt_val_map().get(opt);
                Integer val_sio = Integer.parseInt(sio.get()) - 1;
                if (val_sio >= 0){sio.setValue(String.valueOf(val_sio));}
            }
        });

        // Add Listener to Wheel Tab, so it refreshes itself
        controller.getWheelTab().selectedProperty().addListener((o,v,e) ->{
            if (e){
                setUpWheelPage();
            }
        });
    }
    private void setup() throws FileNotFoundException {
        //Loading data in
        challengeData.loadFile();
        challengeData.loadResult();
        //Setup Result Page
        setUpResults();
        setUpWheelPage();
        setUpOption();
    }
    public void setUpOption(){
        TreeItem<String> root = new TreeItem<>();
        for (int i = 0; i < challengeData.getCategoriesArrayList().size(); i++) {
            TreeItem<String> cat = new TreeItem<>(challengeData.getCategoriesArrayList().get(i));
            ObservableList<String> options =
                    challengeData.getCat_opt_map().get(challengeData.getCategoriesArrayList().get(i));
            for (String option : options) {
                TreeItem<String> opt = new TreeItem<>(option);
                cat.getChildren().add(opt);
            }
            root.getChildren().add(cat);
        }
        controller.getTreeView().getSelectionModel().selectedItemProperty().addListener((o, v, e) -> {
            if (e != null){
                if (e.isLeaf()){
                    controller.getValLabel().textProperty().unbind();
                    StringProperty value = challengeData.getOpt_val_map().get(e.getValue());
                    controller.getValLabel().textProperty().bind(value);
                }
            }
        });
        controller.getTreeView().setRoot(root);
        controller.getTreeView().setShowRoot(false);
    }
    public void addOption(){
        Stage window = new Stage();
        window.setTitle("Add new Option");
        GridPane gridPane = new GridPane();
        Label category = new Label("Enter Category:\t");
        TextField cat = new TextField();

        Label option = new Label("Enter Option:\t");
        TextField opt = new TextField();
        Label value = new Label("Enter Value:\t");
        TextField val = new TextField();
        Button go = new Button("GO!");
        cat.textProperty().addListener(event-> {
            if (!cat.textProperty().get().equals("")&&!opt.textProperty().get().equals("")&&!val.textProperty().get().equals("")){
                go.setDisable(false);
            }
        });
        opt.textProperty().addListener((o, v, n)-> {
            if (!challengeData.getOptlist().contains(n)&&!n.equals("Already used")){
                if (!cat.textProperty().get().equals("")&&!opt.textProperty().get().equals("")&&!val.textProperty().get().equals("")){
                    go.setDisable(false);
                }
            } else {
                opt.setText("Already used");
            }

        });
        val.textProperty().addListener(event-> {
            if (!cat.textProperty().get().equals("")&&!opt.textProperty().get().equals("")&&!val.textProperty().get().equals("")){
                go.setDisable(false);
            }
        });
        go.setDisable(true);
        val.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                val.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        go.setOnAction(event -> {
            boolean found = false;
            for (TreeItem<String> treeCell:
                    controller.getTreeView().getRoot().getChildren()) {
                if (treeCell.getValue().equals(cat.textProperty().getValue())){
                    treeCell.getChildren().add(new TreeItem<>(opt.textProperty().get()));
                    challengeData.addCat(cat.textProperty().get(), opt.textProperty().get(),
                            new SimpleStringProperty(val.textProperty().getValue()));
                    found = true;
                    break;
                }
            }
            if (!found){
                TreeItem<String> stringTreeItem = new TreeItem<>(cat.textProperty().get());
                stringTreeItem.getChildren().add(new TreeItem<>(opt.textProperty().get()));
                challengeData.addCat(cat.textProperty().get(), opt.textProperty().get(),
                        new SimpleStringProperty(val.textProperty().getValue()));
                controller.getTreeView().getRoot().getChildren().add(stringTreeItem);
            }
            window.close();
        });
        gridPane.add(category, 0, 0);
        gridPane.add(cat, 1, 0);
        gridPane.add(option, 0, 1);
        gridPane.add(opt, 1, 1);
        gridPane.add(value, 0, 2);
        gridPane.add(val, 1, 2);
        gridPane.add(go, 2, 1);
        Scene scene = new Scene(gridPane, 300, 90);
        window.setScene(scene);
        window.show();
    }
    public void remOption(){
        Stage window = new Stage();
        window.setTitle("Are you sure?");
        VBox vBox = new VBox();
        vBox.alignmentProperty().setValue(Pos.CENTER);
        vBox.getChildren().add(new Label("Remove: " +
                controller.getTreeView().getSelectionModel().getSelectedItems().get(0).getValue() +
                "?"));
        Scene scene = new Scene(vBox, 150, 40);
        Button remButton = new Button("REMOVE");
        remButton.setOnAction(event -> {
            boolean isFound = false;
            for (TreeItem<String> categoryItem:
                    controller.getTreeView().getRoot().getChildren()) {
                if (isFound){
                    break;
                }
                if (!categoryItem.getValue().equals(
                        controller.getTreeView().getSelectionModel().getSelectedItems().get(0).getValue())){
                    for (TreeItem<String> option : categoryItem.getChildren()) {
                        if (option.getValue().equals(
                                controller.getTreeView().getSelectionModel().getSelectedItems().get(0).getValue())){
                            categoryItem.getChildren().remove(option);
                            challengeData.removeCat(categoryItem.getValue(), option.getValue());
                            if (categoryItem.getChildren().isEmpty()){
                                controller.getTreeView().getRoot().getChildren().remove(categoryItem);
                            }
                            isFound = true;
                            break;
                        }
                    }
                } else if (categoryItem.getValue().equals(
                        controller.getTreeView().getSelectionModel().getSelectedItems().get(0).getValue())){
                    challengeData.remAll(categoryItem.getValue());
                    controller.getTreeView().getRoot().getChildren().remove(categoryItem);
                    break;
                }
            }
            window.close();
        });
        vBox.getChildren().add(remButton);
        window.setScene(scene);
        window.show();

    }
    public void aboutMenu() throws FileNotFoundException {
        Stage about = new Stage();
        about.setTitle("Help");
        Pane pane = new Pane();
        StringBuilder stringBuilder = new StringBuilder();
        Scanner sc = new Scanner(new File("src/main/about.txt"));
        while(sc.hasNextLine()){
            stringBuilder.append(sc.nextLine());
            stringBuilder.append("\n");
        }
        Label label = new Label(stringBuilder.toString());
        label.setWrapText(true);
        pane.getChildren().add(label);
        label.setMaxWidth(600);
        Scene scene = new Scene(pane, 600, 250);
        about.setScene(scene);
        about.show();
    }
    public void setUpResults(){
        TableColumn<Result, LocalDate> dateColumn = new TableColumn<>("Date");
        TableColumn<Result, String> optionColumn = new TableColumn<>("Option");
        TableColumn<Result, Boolean> doneColumn = new TableColumn<>("is Done");
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("localDateObjectProperty")
        );
        optionColumn.setCellValueFactory(
                new PropertyValueFactory<>("option")
        );
        doneColumn.setCellValueFactory(
                new PropertyValueFactory<>("Done")
        );
        FilteredList<Result> filteredList = new FilteredList<>(challengeData.getResList(), p -> true);
        controller.getStartDate().setOnAction(e->{
            filteredList.setPredicate(result -> {
                if (controller.getStartDate().getValue() == null){
                    return true;
                } else return !result.getLocalDateObjectProperty().isBefore(controller.getStartDate().getValue());
            });
        });
        controller.getEndDate().setOnAction(e->{
            filteredList.setPredicate(result -> {
                if (controller.getStartDate().getValue() == null){
                    return true;
                } else return !result.getLocalDateObjectProperty().isAfter(controller.getStartDate().getValue());
            });
        });
        controller.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(result -> {
                if (controller.getStartDate().getValue() == null){
                    return true;
                } else return result.getOption().toLowerCase().contains(newValue.toLowerCase());
            });
        });
        controller.getSwitchField().setOnAction(e->{
            if(controller.getSwitchField().isSelected()){
                controller.getSwitchField().setText("True");
                filteredList.setPredicate(Result::isDone);
            } else {
                controller.getSwitchField().setText("False");
                filteredList.setPredicate(result -> !result.isDone());
            }
        });

        SortedList<Result> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(controller.getResTableView().comparatorProperty());
        dateColumn.setMinWidth(200);
        optionColumn.setMinWidth(200);
        doneColumn.setMinWidth(200);
        controller.getResTableView().getColumns().add(dateColumn);
        controller.getResTableView().getColumns().add(optionColumn);
        controller.getResTableView().getColumns().add(doneColumn);
        controller.getResTableView().setItems(sortedList);
    }
    public void setUpWheelPage(){
        if (challengeData.getCategoriesArrayList().isEmpty()){
            controller.getWheelPane().getChildren().add(new Label("No options yet"));
        } else{
            controller.getWheelPane().getChildren().clear();
            controller.getWheelBar().getItems().clear();
            for (String category:
                    challengeData.getCategoriesArrayList()) {
                Button button = new Button(category);
                ObservableList<String> elements = challengeData.getCat_opt_map().get(category);
                button.setOnAction(event -> {
                    setUpWheel(category, elements);
                });
                controller.getWheelBar().getItems().add(button);
            }
        }

    }
    public void setUpWheel(String category, ObservableList<String> strings){

    }

}
