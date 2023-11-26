module com.teupa.finaldmu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.apache.poi.poi;
    
    exports com.teupa.modelo;
    exports com.teupa.controlador;
    
    opens com.teupa.controlador to javafx.fxml;

}