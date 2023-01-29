package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.model.ChallengeData;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChallengePresenter {
    ChallengeController controller;
    ChallengeData challengeData = new ChallengeData();

    ChallengePresenter(ChallengeController controller) throws FileNotFoundException {
        this.controller = controller;
        setup();

    }

    private void setup() throws FileNotFoundException {
        challengeData.loadFile();
        challengeData.loadResult();
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
        controller.getTreeView().setRoot(root);
        controller.getTreeView().setShowRoot(false);
        controller.getAddOption().setOnAction(e->{
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
            opt.textProperty().addListener(event-> {
                if (!cat.textProperty().get().equals("")&&!opt.textProperty().get().equals("")&&!val.textProperty().get().equals("")){
                    go.setDisable(false);
                }
            });
            val.textProperty().addListener(event-> {
                if (!cat.textProperty().get().equals("")&&!opt.textProperty().get().equals("")&&!val.textProperty().get().equals("")){
                    go.setDisable(false);
                }
            });
            go.setDisable(true);
            val.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        val.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });

            go.setOnAction(event -> {
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
        });
    }
}


