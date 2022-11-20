package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;

public class Loader {

    public Loader(String resourceLocation, Button button, String title){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceLocation));
        Scene scene = null;
        Stage stage = (Stage) button.getScene().getWindow();
        try{
            scene = new Scene(loader.load());
        }catch(IOException e){
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle(title);
    }
}
