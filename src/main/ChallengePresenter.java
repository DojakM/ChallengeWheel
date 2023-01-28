package main;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import main.model.ChallengeData;

import java.io.FileNotFoundException;
import java.io.IOException;

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
                addOptionRow(string, opt, challengeData.getOpt_val_map().get(opt));
            }
        }
        controller.getSaveMenuItem().setOnAction(e->{
            try {
                challengeData.writeFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        controller.getResetMenuItem().setOnAction(e-> {
            try {
                setup();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
    private void addOptionRow(String cat, String opt, SimpleStringProperty val){
        ChoiceBox<String> catField = new ChoiceBox<>();
        catField.itemsProperty().setValue(challengeData.getCategoriesArrayList());
        ChoiceBox<String> optField = new ChoiceBox<>();
        TextField value = new TextField();

        catField.valueProperty().addListener(o ->
                optField.setItems(challengeData.getCat_opt_map().get(catField.getValue())));

        optField.valueProperty().addListener((obs, ol, nw)-> {
            if (ol != null){
                Bindings.unbindBidirectional(value.textProperty(), challengeData.getOpt_val_map().get(ol));
            }
            if (nw == null){
                value.setText("");
            } else {
                Bindings.bindBidirectional(value.textProperty(), challengeData.getOpt_val_map().get(nw));
            }
        });
        catField.setValue(cat);
        optField.setValue(opt);
        controller.getOptionsGrid().add(catField,0,
                challengeData.getIndexOf(opt)+1);
        controller.getOptionsGrid().add(optField,1,
                challengeData.getIndexOf(opt)+1);
        value.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                value.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        Button delButton = new Button("DELETE");
        delButton.setStyle("-fx-background-color:RED");
        delButton.setOnAction(event -> {
            removeRow(controller.getOptionsGrid(), challengeData.getIndexOf(opt)+1);
            challengeData.removeCat(cat, opt);
        });
        controller.getOptionsGrid().add(delButton, 3, challengeData.getIndexOf(opt)+1);
        controller.getOptionsGrid().add(value, 2,
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


