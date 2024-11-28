package com.map.rpg_map.model.entities;

public class Item {
    private float width;
    private float height;
    private Position position;
    private String description;
    private Color color;

    public Item(float width, float height, Position position, String description, Color color) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.description = description;
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getArea() {
        return this.width * this.height;
    }

    public float getRight() {
        return this.position.getxPosition() + this.width;
    }

    public float getBottom() {
        return this.position.getyPosition() + this.height;
    }

    public boolean overlapsWith(Item other) {
        return !(this.getRight() <= other.getPosition().getxPosition() ||  // too far left
                this.getPosition().getxPosition() >= other.getRight() ||  // too far right
                this.getBottom() <= other.getPosition().getyPosition() || // too far up
                this.getPosition().getyPosition() >= other.getBottom());  // too far down
    }

    @Override
    public String toString() {
        return "Item{" +
                "width=" + width +
                ", height=" + height +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", color=" + color +
                '}';
    }
}
