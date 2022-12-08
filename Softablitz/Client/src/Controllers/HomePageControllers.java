package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.*;

import java.io.IOException;
import java.sql.*;


public class HomePageControllers {

        @FXML
        public Label HomePageName;
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
        public Button LogOut;


        public HomePageControllers() {
//
//                //
                // use DatabaseConnection class to establish connection between homePageNAme and database
                
                Connection con = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                con = DatabaseConnection.getConnection();   // connecting to database to access username which is being showed on home page.....
                try {
                        
                        //create query
                        String query = "select username from users";

                        //open the statement
                        pst = con.prepareStatement(query);
                        rs = pst.executeQuery();

                        //display the results
                        while(rs.next()){
//                                HomePageName = rs.getString("username");
//                                System.out.format("Hello-> %s",username);
                        }
                        pst.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public void onClickNewFolder(){
                Loader loader = new Loader("../View/newFolder.fxml","New Folder");
        }

        public void onClickPersonalInfo(ActionEvent actionEvent){
                try {
                        Loader loader = new Loader("../Views/personalinfo.fxml", PersonalInfo, "Profile Page");
                }
                catch( Exception e){
                        e.printStackTrace();
                        System.out.println("Sorry! CAn't Load page at this time");
                }
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
                        Loader loader = new Loader("../Views/favourites.fxml", Favorites, "Favorites Page");
                }
                catch( Exception e){
                        e.printStackTrace();
                        System.out.println("Sorry! CAn't Load page at this time");
                }
        }
        public void onClickAbout(){
                try {
                        Loader loader = new Loader("../Views/About Drive.fxml", About, "About");
                }
                catch( Exception e){
                        e.printStackTrace();
                        System.out.println("Sorry! CAn't Load page at this time");
                }
        }

        public void OnClickLogOut(){}


}
