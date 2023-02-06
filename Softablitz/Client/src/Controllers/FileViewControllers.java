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
    private TextArea FileContentTextArea;

    public static Label static_FileNameLabel;
    public static TextArea static_FileContentTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_FileNameLabel = FileNameLabel;
        static_FileContentTextArea = FileContentTextArea;
    }
}
