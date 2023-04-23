module com.example.pt2023_30222_laszlo_bogdan_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pt2023_30222_laszlo_bogdan_2 to javafx.fxml;
    exports com.example.pt2023_30222_laszlo_bogdan_2.Model;
    opens com.example.pt2023_30222_laszlo_bogdan_2.Model to javafx.fxml;
    exports com.example.pt2023_30222_laszlo_bogdan_2.Business_Logic;
    opens com.example.pt2023_30222_laszlo_bogdan_2.Business_Logic to javafx.fxml;
}