package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class FileViewControllers implements Initializable {
    @FXML
    private Label FileNameLabel;
    @FXML
    private Label FileContentLabel;

    public static Label static_FileNameLabel;
    public static Label static_FileContentLabel;

//    public TextField getFileNameArea() {
//        return FileNameArea;
//    }

//    public void setFileNameArea(TextField fileNameArea) {
//        FileNameArea = fileNameArea;
//    }

//    public TextArea getFileContentArea() {
//        return FileContentArea;
//    }

//    public void setFileContentArea(TextArea fileContentArea) {
//        FileContentArea = fileContentArea;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_FileNameLabel = FileNameLabel;
        static_FileContentLabel = FileContentLabel;
    }
}
