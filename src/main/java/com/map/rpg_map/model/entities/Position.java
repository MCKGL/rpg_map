package com.map.rpg_map.model.entities;

public class Position {
    private final float xPosition;
    private final float yPosition;

    public Position(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    @Override
    public String toString() {
        return "Position{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }
}
