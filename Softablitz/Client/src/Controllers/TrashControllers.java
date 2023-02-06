package Controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TrashControllers implements Initializable {

    @FXML
    private Button PermanentDelete ;
    @FXML
    private Button RestoreFile ;
    private int selectedIndex = -1;



    @FXML
    void onClickDelete(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Permanent Delete");
        alert.setContentText("Do you still want to delete this file?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            TrashFilesList.setOnMouseClicked(event -> {
                selectedIndex = TrashFilesList.getSelectionModel().getSelectedIndex();
            });

            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = null;
            ResultSet rs = null;

            String query = "delete from fileslist where filename = ? and uploadedby = ? and trash = 1";

            try {
                pst = con.prepareStatement(query);
                pst.setString(1, TrashFilesList.getSelectionModel().getSelectedItem().toString());
                pst.setString(2, login.loginEmailId);
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Loader loader = new Loader("../Views/trash.fxml", PermanentDelete, "Trash");
        }
    }

    @FXML
    void onClickRestore(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Restore Conformation ");
        alert.setHeaderText("!Message!");
        alert.setContentText("Do you want to restore this file ?");

        if(alert.showAndWait().get() == ButtonType.OK) {

            TrashFilesList.setOnMouseClicked(event -> {
                selectedIndex = TrashFilesList.getSelectionModel().getSelectedIndex();
            });

            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = null;
            ResultSet rs = null;

            String query = "update fileslist set trash = 0 where filename = ? and uploadedby = ?";

            try {
                pst = con.prepareStatement(query);
                pst.setString(1, TrashFilesList.getSelectionModel().getSelectedItem().toString());
                pst.setString(2, login.loginEmailId);
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Loader loader = new Loader("../Views/trash.fxml", RestoreFile, "Trash");
        }
    }




    @FXML
    private ListView<String> TrashFilesList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String query = "select filename from fileslist where trash = 1 and uploadedby = ?";

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, login.loginEmailId);
            rs = pst.executeQuery();


            while(rs.next()){
                String listOut = rs.getString("filename");
                TrashFilesList.getItems().add(listOut);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
