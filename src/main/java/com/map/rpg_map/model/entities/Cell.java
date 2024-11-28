package com.map.rpg_map.model.entities;

public class Cell {
    private float width;
    private float height;

    public Cell(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getxSize() {
        return width;
    }

    public void setxSize(float xSize) {
        this.width = xSize;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "xSize=" + width +
                ", ySize=" + height +
                '}';
    }
}
