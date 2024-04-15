module ca.ucalgary.groupprojectgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;


    opens ca.ucalgary.groupprojectgui to javafx.fxml;
    exports ca.ucalgary.groupprojectgui;
}