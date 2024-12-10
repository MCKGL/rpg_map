package com.map.rpg_map.model.entities;

public enum DefaultItems {
    TREES(1,1, "trees", "trees.png"), FOREST(2,2, "forest", "forest.png"), MOUNTAIN(2,3, "mountain", "mountain.png"), STONE_ROAD(1,1, "stone road", "stone_road.png"), CHEST(1,1, "chest", "chest.png");

    private int cellsOnX;
    private int cellsOnY;
    private String description;
    private String url;

    DefaultItems(int cellsOnX, int cellsOnY, String description, String url) {
        this.cellsOnX = cellsOnX;
        this.cellsOnY = cellsOnY;
        this.description = description;
        this.url = url;
    }

    public int getCellsOnX() {
        return cellsOnX;
    }

    public int getCellsOnY() {
        return cellsOnY;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
