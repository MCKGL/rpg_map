package com.map.rpg_map.model.entities;

import java.util.ArrayList;

public class Map {
    private Cell cell;
    private int cellsOnX;
    private int cellsOnY;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Map(Cell cell, int cellsOnX, int cellsOnY) {
        this.cell = cell;
        this.cellsOnX = cellsOnX;
        this.cellsOnY = cellsOnY;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getCellsOnX() {
        return cellsOnX;
    }

    public void setCellsOnX(int cellsOnX) {
        this.cellsOnX = cellsOnX;
    }

    public int getCellsOnY() {
        return cellsOnY;
    }

    public void setCellsOnY(int cellsOnY) {
        this.cellsOnY = cellsOnY;
    }

    public Size getSize() {
        float totalWidth = this.cellsOnX * this.cell.getxSize();
        float totalHeight = this.cellsOnY * this.cell.getHeight();
        return new Size(totalWidth, totalHeight);
    }

    public float getWidth() {
        return this.getSize().getWidth();
    }

    public float getHeight() {
        return this.getSize().getHeight();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

//    public void addItem(Item item) {
//        if (!this.isPlaceFree(item)) {
//            throw new IllegalArgumentException("Item can't be placed here");
//        } else {
//            this.items.add(item);
//        }
//    }

    public boolean addItem(Item newItem) {
        if (isPlaceFree(newItem)) {
            items.add(newItem);
            return true;
        }
        return false;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void clearItems() {
        this.items.clear();
    }

    private boolean isPlaceFree(Item newItem) {
        // check if the item is within the map boundaries
        if (newItem.getPosition().getxPosition() < 0 || newItem.getPosition().getyPosition() < 0 ||
                newItem.getRight() > this.getWidth() || newItem.getBottom() > this.getHeight()) {
            return false; // En dehors des limites
        }

        // check if the item overlaps with any existing item
        for (Item existingItem : this.items) {
            if (newItem.overlapsWith(existingItem)) {
                return false; // Collision détectée
            }
        }

        return true;
    }

}
