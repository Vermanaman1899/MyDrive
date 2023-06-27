package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;

public class Loader {

    // this constructor will open scene in current present window itself
    // stage is same but scene is updated
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


    // this constructor will open scene in new window
    // stage is different with different scene
    // we can use this constructor to open short term stages like--> create new folder scene, create new file scene , aboutDrive view etc...
    public Loader(String resourceLocation, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceLocation));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch( IOException e){
            e.printStackTrace();
            System.out.println("Can't load this new window");
        }
    }
}
