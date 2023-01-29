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
}


