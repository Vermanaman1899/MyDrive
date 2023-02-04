package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class HomePageControllers implements Initializable {

        @FXML
        public Label HomePageNameLabel;
        @FXML
        public Button PersonalInfo;
        @FXML
        public Button CreateNewFolder;
        @FXML
        public Button TrashFiles;
        @FXML
        public Button Favorites;
        @FXML
        public Button About;
        @FXML
        private Button LogOut;
        @FXML
        private AnchorPane scenePane;
        @FXML
        public Button MyFolders;
        @FXML
        public Button UploadFile;
        @FXML
        public Button MyFiles;


        @FXML
        public void onClickNewFolder(ActionEvent event){
                Loader loader = new Loader("../Views/newFolder.fxml","New Folder");
//
        }

        public void onClickPersonalInfo(ActionEvent actionEvent){
               Loader loader = new Loader("../Views/personalinfo.fxml", PersonalInfo,"Profile Page");
        }

        public void onClickMyFolders(){}

        public void onClickTrashFiles(){
                try {
                        Loader loader = new Loader("../Views/trash.fxml", TrashFiles, "Trash");
                }
                catch( Exception e){
                        e.printStackTrace();
                        System.out.println("Sorry! CAn't Load page at this time");
                }
        }

        public void onClickFavourites() {
                try {
                        Loader loader = new Loader("../Views/favourites.fxml","Favorites Page");
                }
                catch( Exception e){
                        e.printStackTrace();
                        System.out.println("Sorry! CAn't Load page at this time");
                }
        }
        public void onClickAbout(){
                try {
                        Loader loader = new Loader("../Views/About Drive.fxml", "About");
                }
                catch( Exception e){
                        e.printStackTrace();
                        System.out.println("Sorry! CAn't Load page at this time");
                }
        }

        public void OnClickLogOut(){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("You're about to logout!");
                alert.setContentText("Do you want to logout ?");

                if(alert.showAndWait().get() == ButtonType.OK) {
                        Stage stage = (Stage) scenePane.getScene().getWindow();
                        stage.close();
                }
        }

        public void onClickMyFiles(){
                Loader loader = new Loader("../Views/allUploadedFiles.fxml", "My Files");
        }

        public void onClickNewFile(){
                //on a new window fileInfo view should be visible :::::::
                Loader loader = new Loader("../Views/fileInfo.fxml","New File");
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                Connection con = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                try {
                        // connecting to database to access username which is being showed on home page.....
                        con = DatabaseConnection.getConnection();
                        //create query
                        String query = "select username from users where email = ?;";

                        //open the prepared statement
                        pst = con.prepareStatement(query);
                        pst.setString(1, login.loginEmailId);
                        rs = pst.executeQuery();

                        System.out.println(pst);

                        //display the results
                        while(rs.next()){
                                System.out.println(rs.getString("username"));
                                HomePageNameLabel.setText(rs.getString("username"));
                        }
                        pst.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
}
