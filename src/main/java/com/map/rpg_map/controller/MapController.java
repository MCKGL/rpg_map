package com.map.rpg_map.controller;

import com.map.rpg_map.model.entities.Cell;
import com.map.rpg_map.model.entities.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapController {
    @FXML
    private Label labelCreate, labelEditCell, labelSizeType;
    @FXML
    private MenuItem menuNewMap, menuNewItem;
    @FXML
    private Pane paneCreation, paneCreateCell, paneMap;
    @FXML
    private TextField textWCell, textHCell, textWMap, textHMap;

    private boolean isMapCreation;
    private Cell cell = new Cell(25, 25);
    private Map map;

    @FXML
    public void initialize() {
        paneCreation.setVisible(false);
    }

    public void showPaneCreation(ActionEvent event) {
        textWCell.setText(String.valueOf(cell.getWidth()));
        textHCell.setText(String.valueOf(cell.getHeight()));
        labelEditCell.setVisible(false);
        if (event.getSource() == menuNewMap) {
            this.isMapCreation = true;
            paneCreateCell.setVisible(true);
            labelCreate.setText("Create a new map");
            labelSizeType.setText("Number of cells");
        }
        if (event.getSource() == menuNewItem) {
            this.isMapCreation = false;
            paneCreateCell.setVisible(false);
            labelCreate.setText("Create a new item");
            labelSizeType.setText("px");
        }
        paneCreation.setVisible(true);
    }

    public void createElement() {
        if (this.isMapCreation) {
            try {
                int mapWidth = Integer.parseInt(textWMap.getText());
                int mapHeight = Integer.parseInt(textHMap.getText());

                this.map = new Map(cell, mapWidth, mapHeight);

                drawMap();

                System.out.println("New map : " + map);
            } catch (NumberFormatException e) {
                System.out.println("Error : invalid map size");
            }
        } else {
            System.out.println("Create a new item");
            // TODO
        }
        paneCreation.setVisible(false);
    }

    private void drawMap() {
        // Clear the map pane
        paneMap.getChildren().clear();

        if (map == null) {
            // TODO error message and null gestion
            return;
        }

        int cellsOnX = map.getCellsOnX();
        int cellsOnY = map.getCellsOnY();
        float cellWidth = map.getCell().getWidth();
        float cellHeight = map.getCell().getHeight();

        // Draw the cells on the map
        for (int row = 0; row < cellsOnY; row++) {
            for (int col = 0; col < cellsOnX; col++) {
                Rectangle cellRect = new Rectangle();
                cellRect.setX(col * cellWidth);  // Position X
                cellRect.setY(row * cellHeight); // Position Y
                cellRect.setWidth(cellWidth);    // width of the cell
                cellRect.setHeight(cellHeight);  // height of the cell
                cellRect.setFill(Color.TRANSPARENT); // Transparent background
                cellRect.setStroke(Color.BLACK);     // Black border

                // Add the cell to the map pane
                paneMap.getChildren().add(cellRect);
            }
        }

        System.out.println("Map drawn");
    }

    public void editCell() {
        try {
            float newWidth = Float.parseFloat(textWCell.getText());
            float newHeight = Float.parseFloat(textHCell.getText());
            cell.setWidth(newWidth);
            cell.setHeight(newHeight);
            labelEditCell.setVisible(true);
            labelEditCell.setText("Cell edited");
        } catch (NumberFormatException e) {
            labelEditCell.setVisible(true);
            labelEditCell.setText("Invalid cell size");
        }
    }

    public void cancelCreation() {
        paneCreation.setVisible(false);
    }

}
