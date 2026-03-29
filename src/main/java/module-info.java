module fr.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;

    opens fr.chess to javafx.graphics, javafx.fxml;
    exports fr.chess;
}