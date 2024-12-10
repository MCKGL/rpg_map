package com.map.rpg_map.controller;

import com.map.rpg_map.model.entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MapController {
    @FXML
    private Label labelEditCell;
    @FXML
    private MenuItem menuNewMap, menuNewItem, menuDelete, menuAbout;
    @FXML
    private Pane paneCreation, paneMap;
    @FXML
    private ScrollPane paneInventory;
    @FXML
    private TextField textWCell, textHCell, textWMap, textHMap;

    private Cell cell = new Cell(25, 25);
    private Map map;
    private Inventory inventory = new Inventory();
    private VBox inventoryContent;
    private ImageView currentlyHighlightedImageView = null;

    @FXML
    public void initialize() {
        this.hidePane();
        for (DefaultItems defaultItem : DefaultItems.values()) {
            Item item = new Item(
                    defaultItem.getCellsOnX(),
                    defaultItem.getCellsOnY(),
                    defaultItem.getDescription(),
                    defaultItem.getUrl()
            );
            inventory.addItem(item);
        }
        setupInventory();
    }

    private void setupInventory() {
        inventoryContent = new VBox();
        inventoryContent.setSpacing(10);
        paneInventory.setContent(inventoryContent);

        for (Item item : inventory.getItems()) {
            VBox itemBox = createItemBox(item);
            inventoryContent.getChildren().add(itemBox);
        }
    }

    private VBox createItemBox(Item item) {
        VBox vbox = new VBox();
        ImageView imageView = createImageView(item);
        Label label = new Label(item.getDescription());
        setupItemClickListener(imageView);
        vbox.getChildren().addAll(imageView, label);
        return vbox;
    }

    private void setupItemClickListener(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            if (currentlyHighlightedImageView != null && currentlyHighlightedImageView != imageView) {
                currentlyHighlightedImageView.setStyle("");
            }
            if (currentlyHighlightedImageView == imageView) {
                imageView.setStyle("");
                currentlyHighlightedImageView = null;
            } else {
                imageView.setStyle("-fx-effect: innershadow(gaussian, orangered, 10, 0.5, 0, 0);");
                currentlyHighlightedImageView = imageView;
            }
        });
    }

    private ImageView createImageView(Item item) {
        ImageView imageView;
        try {
            imageView = new ImageView(getClass().getResource("/images/"+item.getUrl()).toExternalForm());
            imageView.setFitWidth(item.getCellsOnX() * cell.getWidth());
            imageView.setFitHeight(item.getCellsOnY() * cell.getHeight());
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'image : " + item.getUrl());
            imageView = new ImageView(); // Image vide par défaut
        }

        return imageView;
    }

    private void hidePane() {
        paneCreation.setVisible(false);
    }

    public void showPane(ActionEvent event) {
        this.hidePane();
        if (event.getSource() == menuNewMap) {
            textWCell.setText(String.valueOf(cell.getWidth()));
            textHCell.setText(String.valueOf(cell.getHeight()));
            paneCreation.setVisible(true);
        }
        if (event.getSource() == menuNewItem) {
            // TODO : show item importation pane
        }
        if (event.getSource() == menuDelete) {
            // TODO : show delete map pane
        }
        if (event.getSource() == menuAbout) {
            // TODO : show about pane
        }

    }

    public void createMap() {
            try {
                int mapWidth = Integer.parseInt(textWMap.getText());
                int mapHeight = Integer.parseInt(textHMap.getText());

                this.map = new Map(cell, mapWidth, mapHeight);

                drawMap();

                System.out.println("New map : " + map);
            } catch (NumberFormatException e) {
                System.out.println("Error : invalid map size");
            }

        paneCreation.setVisible(false);
    }

    private void drawMap() {
        // Clear the map pane
        paneMap.getChildren().clear();

        if (map != null) {
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
        } else {
            System.out.println("Error : no map to draw");
        }

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
