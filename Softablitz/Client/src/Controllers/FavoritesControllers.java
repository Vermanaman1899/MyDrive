package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FavoritesControllers {
    @FXML
    public Button HomePage;

    public void onClickHomePage() {
        Loader loader = new Loader("../Views/home page.fxml", HomePage, "Home Page");

    }
}
