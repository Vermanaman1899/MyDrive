package Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;

public class AllUploadedFileControllers {

    Connection con = DatabaseConnection.getConnection();
    String query = "select * from fileslist where uploadedby = ?";



    @FXML
    private TableColumn<?, ?> Date;

    @FXML
    private TableColumn<?, ?> FileName;

    @FXML
    private TableColumn<?, ?> FileSize;

    @FXML
    private TableColumn<?, ?> FileType;

    @FXML
    private TableView<?> MyFilesTable;

}
