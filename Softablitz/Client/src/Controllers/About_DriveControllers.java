package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class About_DriveControllers {
    @FXML
    public Button SwitchToHomePage;
    public void onClickHomePage(){
        Loader loader = new Loader("../Views/home page.fxml", SwitchToHomePage, "Home Page");
    }
}
