module com.example {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires itextpdf;

    opens com.example to javafx.fxml;
    exports com.example;
}
