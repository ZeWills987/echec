module fr.chess {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.chess to javafx.graphics, javafx.fxml;
    exports fr.chess;
}