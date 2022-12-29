package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
        private Label HomePageNameLabel;
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
        @FXML
        public Button MyFolders;


        public HomePageControllers() {
                
                // use DatabaseConnection class to establish connection between homePageNAme and database
                
                Connection con = null;
                Statement st = null;
                ResultSet rs = null;
                con = DatabaseConnection.getConnection();   // connecting to database to access username which is being showed on home page.....
                try {
                        
                        //create query
                        String query = "select username from users where email ='"+login.loginEmailId+"'";

                        //open the statement
                        st = con.createStatement();
                        rs = st.executeQuery(query);

                        //display the results
                        while(rs.next()){
                                HomePageNameLabel.setText(rs.getString("username"));
                        }
                        st.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public void onClickNewFolder(ActionEvent event){
//                Loader loader = new Loader("../View/newFolder.fxml","New Folder");
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/newFolder.fxml"));
                        Parent root = (Parent) loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("New Folder");
                        stage.setScene(new Scene(root));
                        stage.show();
                }
                catch( IOException e){
                        e.printStackTrace();
                        System.out.println("Can't load this new window");
                }
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
