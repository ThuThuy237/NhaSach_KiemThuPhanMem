module com.mynhasach.nhasach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mynhasach.nhasach to javafx.fxml;
    exports com.mynhasach.nhasach;
}
