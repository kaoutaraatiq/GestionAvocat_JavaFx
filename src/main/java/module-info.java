module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;





    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.entities;
    opens com.example.demo.entities to javafx.fxml;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;


}