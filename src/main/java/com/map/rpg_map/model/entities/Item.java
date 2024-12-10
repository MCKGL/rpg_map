package com.map.rpg_map.model.entities;

public class Item {
    private int cellsOnX;
    private int cellsOnY;
    private Position position;
    private String description;
    private String url;

    public Item(int cellsOnX, int cellsOnY, Position position, String description, String url) {
        this.cellsOnX = cellsOnX;
        this.cellsOnY = cellsOnY;
        this.position = position;
        this.description = description;
        this.url = url;
    }

    public Item(int cellsOnX, int cellsOnY, String description, String url) {
        this.cellsOnX = cellsOnX;
        this.cellsOnY = cellsOnY;
        this.description = description;
        this.url = url;
    }

    public int getCellsOnY() {
        return cellsOnY;
    }

    public void setCellsOnY(int cellsOnY) {
        this.cellsOnY = cellsOnY;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCellsOnX() {
        return cellsOnX;
    }

    public void setCellsOnX(int cellsOnX) {
        this.cellsOnX = cellsOnX;
    }


    public float getRight(Cell cell) {
        return this.position.getxPosition() + cellsOnX * cell.getWidth();
    }

    public float getBottom(Cell cell) {
        return this.position.getyPosition() + cellsOnY * cell.getHeight();
    }

    public boolean overlapsWith(Item other, Cell cell) {
        return !(this.getRight(cell) <= other.getPosition().getxPosition() ||  // too far left
                this.getPosition().getxPosition() >= other.getRight(cell) ||  // too far right
                this.getBottom(cell) <= other.getPosition().getyPosition() || // too far up
                this.getPosition().getyPosition() >= other.getBottom(cell));  // too far down
    }

    @Override
    public String toString() {
        return "Item{" +
                "cellsOnX=" + cellsOnX +
                ", cellsOnY=" + cellsOnY +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
