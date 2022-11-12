module com.example.calculator_real_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.calculator_real_ to javafx.fxml;
    exports com.example.calculator_real_;
}