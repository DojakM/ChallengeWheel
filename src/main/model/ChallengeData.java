package main.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonContainer {
    JsonContainer() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("src/main/data.csv"));
        while (sc.hasNextLine()){
            String current_line = sc.nextLine();
            category.add(current_line.split(",")[0]);
            option.add(current_line.split(",")[1]);
            value.add(Integer.valueOf(current_line.split(",")[2]));
        }
    }
}

