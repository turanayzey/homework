module com.example.car_rental {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.car_rental to javafx.fxml;
    exports com.example.car_rental;
}