package com.map.rpg_map;

import com.map.rpg_map.model.entities.Cell;
import com.map.rpg_map.model.entities.Item;
import com.map.rpg_map.model.entities.Map;
import com.map.rpg_map.model.entities.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();
        Cell cell = new Cell(1.0f, 1.0f);
        Map map = new Map(cell, 10, 10);

        Item item1 = new Item(2, 2, new Position(1, 1), "First item", null);
        Item item2 = new Item(3, 3, new Position(3, 3), "Second item", null);
        Item item3 = new Item(2, 2, new Position(2, 2), "Overlapping item", null);

        System.out.println(map.addItem(item1)); // true
        System.out.println(map.addItem(item2)); // true
        System.out.println(map.addItem(item3)); // false
    }
}