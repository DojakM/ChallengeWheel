package main.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChallengeData {
    public int option_amount = 0;

    ObservableList<String> resultsArrayList = FXCollections.observableArrayList();
    HashMap<String[], SimpleBooleanProperty> doneMap = new HashMap<>();
    ObservableList<String> categoriesArrayList = FXCollections.observableArrayList();
    ArrayList<String> optlist = new ArrayList<>();
    ObservableList<SimpleIntegerProperty> valuesArrayList = FXCollections.observableArrayList();
    HashMap<String, ObservableList<String>> cat_opt_map = new HashMap<>();
    HashMap<String, SimpleIntegerProperty> opt_val_map = new HashMap<String, SimpleIntegerProperty>();

    public void loadFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/data.csv"));
        while (sc.hasNextLine()){
            String current_line = sc.nextLine();
            String[] lines = current_line.split(",");
            this.option_amount++;
            String category = lines[0];
            String option = lines[1];
            SimpleIntegerProperty value = new SimpleIntegerProperty(Integer.parseInt(lines[2]));
            if (!categoriesArrayList.contains(category)){
                categoriesArrayList.add(category);
                cat_opt_map.put(category, FXCollections.observableArrayList());
                cat_opt_map.get(category).add(option);
                optlist.add(option);
                opt_val_map.put(option, value);
            } else {
                cat_opt_map.get(category).add(option);
                opt_val_map.put(option, value);
                optlist.add(option);
            }

        }
        sc.close();
    }

    public ObservableList<String> getResultsArrayList() {
        return resultsArrayList;
    }

    public ObservableList<String> getCategoriesArrayList() {
        return categoriesArrayList;
    }

    public HashMap<String[], SimpleBooleanProperty> getDoneMap() {
        return doneMap;
    }

    public ObservableList<SimpleIntegerProperty> getValuesArrayList() {
        return valuesArrayList;
    }

    public HashMap<String, ObservableList<String>> getCat_opt_map() {
        return cat_opt_map;
    }

    public HashMap<String, SimpleIntegerProperty> getOpt_val_map() {
        return opt_val_map;
    }
    public int getIndexOf(String opt){
        return this.optlist.indexOf(opt);
    }

    public void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/data.csv", false);
        for (String category:
             categoriesArrayList) {
            for (Object option:
                 cat_opt_map.get(category)) {
                String text = category + ",";
                text += option.toString() + ",";
                text += opt_val_map.get(option) + "\n";
                fileWriter.write(text);
            }
        }
        fileWriter.close();
    }

    public void loadResult() throws FileNotFoundException {
        resultsArrayList.clear();
        Scanner sc = new Scanner(new File("src/main/result.csv"));
        while (sc.hasNextLine()){
            String current_line = sc.nextLine();
            String[] frags = current_line.split(",");

        }
        sc.close();
    }
    
    public void addCat(String cat, String opt, SimpleIntegerProperty sio){
        this.option_amount++;
        if (!categoriesArrayList.contains(cat)){
            categoriesArrayList.add(cat);
            cat_opt_map.put(cat, FXCollections.observableArrayList());
            cat_opt_map.get(cat).add(opt);
            opt_val_map.put(opt, sio);
            optlist.add(opt);
        } else {
            cat_opt_map.get(cat).add(opt);
            opt_val_map.put(opt, sio);
            optlist.add(opt);
        }
    }
    public void removeCat(String cat, String opt){
        this.option_amount--;
        optlist.remove(opt);
        cat_opt_map.get(cat).remove(opt);
        if (cat_opt_map.get(cat).isEmpty()){
            categoriesArrayList.remove(cat);
            cat_opt_map.remove(cat);

        }
        opt_val_map.remove(opt);
    }

    public void writeResults(String date, String option, Boolean isDone) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/result.csv", false);
        String text = date + "," + option + "," + isDone + "\n";
        fileWriter.write(text);
        fileWriter.close();
    }
}

