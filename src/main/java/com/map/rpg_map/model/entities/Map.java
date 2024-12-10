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
        float totalWidth = this.cellsOnX * this.cell.getWidth();
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

    public boolean addItem(Item item, Position clickPosition) {
        boolean isAdd = false;

        float alignedX = (float) Math.floor(clickPosition.getxPosition() / cell.getWidth()) * cell.getWidth();
        float alignedY = (float) Math.floor(clickPosition.getyPosition() / cell.getHeight()) * cell.getHeight();

        item.setPosition(new Position(alignedX, alignedY));

        if (this.isPlaceFree(item)) {
            this.items.add(item);
            isAdd = true;
        }
        return isAdd;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void clearItems() {
        this.items.clear();
    }

    private boolean isPlaceFree(Item newItem) {
        boolean isFree = true;
        // check if the item is within the map boundaries
        if (newItem.getPosition().getxPosition() < 0 || newItem.getPosition().getyPosition() < 0 ||
                newItem.getRight(cell) > this.getWidth() || newItem.getBottom(cell) > this.getHeight()) {
            isFree = false;
        }

        // check if the item overlaps with any existing item
        for (Item existingItem : this.items) {
            if (newItem.overlapsWith(existingItem, cell)) {
                isFree = false;
            }
        }

        return isFree;
    }

    @Override
    public String toString() {
        return "Map{" +
                "cell=" + cell +
                ", cellsOnX=" + cellsOnX +
                ", cellsOnY=" + cellsOnY +
                ", items=" + items +
                '}';
    }
}
