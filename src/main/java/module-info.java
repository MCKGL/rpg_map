module com.map.rpg_map {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.map.rpg_map to javafx.fxml;
    opens com.map.rpg_map.controller to javafx.fxml;

    exports com.map.rpg_map;
    exports com.map.rpg_map.controller;
}
