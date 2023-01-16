package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FileViewControllers {
    @FXML
    public static TextField FileNameArea;
    @FXML
    public static TextArea FileContentArea;

//    public FileViewControllers(TextField fileNameArea, TextArea fileContentArea) {
//        FileNameArea = fileNameArea;
//        FileContentArea = fileContentArea;
//    }

    public TextField getFileNameArea() {
        return FileNameArea;
    }

    public void setFileNameArea(TextField fileNameArea) {
        FileNameArea = fileNameArea;
    }

    public TextArea getFileContentArea() {
        return FileContentArea;
    }

    public void setFileContentArea(TextArea fileContentArea) {
        FileContentArea = fileContentArea;
    }
}
