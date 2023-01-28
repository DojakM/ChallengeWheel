package main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.model.ChallengeData;

import java.io.FileNotFoundException;

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
        if(challengeData.option_amount>controller.getOptionsGrid().getRowCount()){
            for(int i=0;challengeData.option_amount+1-controller.getOptionsGrid().getRowCount()>=i; i++){
                controller.getOptionsGrid().addRow(controller.getOptionsGrid().getRowCount()+i);
            }
        }
        for (int i = 0; challengeData.option_amount>i;i++) {
            String string = challengeData.getCategoriesArrayList().get(i);
            for (String opt:
                 challengeData.getCat_opt_map().get(string)) {
                add_triple_row(string, opt, challengeData.getOpt_val_map().get(opt));
            }
        }

    }
    private void add_triple_row(String cat, String opt, SimpleIntegerProperty val){
        ChoiceBox<String> catField = new ChoiceBox<>();
        catField.itemsProperty().setValue(challengeData.getCategoriesArrayList());
        ChoiceBox<String> optField = new ChoiceBox<>();
        catField.valueProperty().addListener(o ->
                optField.setItems(challengeData.getCat_opt_map().get(catField.getValue())));
        TextField value = new TextField();
        value.textProperty().bind(val.asString());
        optField.valueProperty().addListener(o ->
            val.set(Integer.parseInt(String.valueOf(challengeData.getOpt_val_map().get(optField.getValue()))))
        );
        catField.setValue(String.valueOf(cat));
        optField.setValue(opt);
        controller.getOptionsGrid().add(catField,0,
                challengeData.getIndexOf(opt)+1);
        controller.getOptionsGrid().add(optField,1,
                challengeData.getIndexOf(opt)+1);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button minus_this = new Button("-");
        minus_this.setOnAction(e->val.subtract(1));
        Button plus_this = new Button("+");
        plus_this.setOnAction(e -> val.add(1));
        hBox.getChildren().add(minus_this);
        hBox.getChildren().add(value);
        hBox.getChildren().add(plus_this);
        Button delButton = new Button("DELETE");
        delButton.setStyle("-fx-background-color:RED");
        delButton.setOnAction(event -> {
            removeRow(controller.getOptionsGrid(), challengeData.getIndexOf(opt)+1);
            challengeData.removeCat(cat, opt);
        });
        controller.getOptionsGrid().add(delButton, 3, challengeData.getIndexOf(opt)+1);
        controller.getOptionsGrid().add(hBox, 2,
                challengeData.getIndexOf(opt)+1);
    }
    public static int getRowIndexAsInteger(Node node) {
        final var a = GridPane.getRowIndex(node);
        if (a == null) {
            return 0;
        }
        return a;
    }
        public static void removeRow(GridPane grid, Integer targetRowIndexIntegerObject) {
            final int targetRowIndex = targetRowIndexIntegerObject == null ? 0 : targetRowIndexIntegerObject;

            // Remove children from row
            grid.getChildren().removeIf(node -> getRowIndexAsInteger(node) == targetRowIndex);

            // Update indexes for elements in further rows
            grid.getChildren().forEach(node -> {
                final int rowIndex = getRowIndexAsInteger(node);
                if (targetRowIndex < rowIndex) {
                    GridPane.setRowIndex(node, rowIndex - 1);
                }
            });

            // Remove row constraints
            grid.getRowConstraints().remove(targetRowIndex);
    }
}


