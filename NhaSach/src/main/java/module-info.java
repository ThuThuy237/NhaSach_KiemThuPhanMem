module com.mynhasach.nhasach {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mynhasach.nhasach to javafx.fxml;
    opens com.mynhasach.pojo to javafx.fxml, javafx.base;
    exports com.mynhasach.nhasach;
}
