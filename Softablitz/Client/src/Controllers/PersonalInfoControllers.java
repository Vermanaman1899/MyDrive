package Controllers;

import Request.SignupRequest;
import Response.SignupResponse;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;

import javax.security.auth.callback.PasswordCallback;
import java.lang.*;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;



public class PersonalInfoControllers implements Initializable{

        @FXML
        private TextField UsernameEdit;

        @FXML
        private PasswordField PasswordEdit;

        @FXML
        private Button BackToHS;

        @FXML
        private Button EditProfile;

        @FXML
        private TextField MobileEdit;

        @FXML
        private TextField NameEdit;

        @FXML
        private Label ProfileEmailLabel;


        @FXML
        private Label ProfilePasswordLabel1;

        @FXML
        private Label ProfileEmailLabel1;

        @FXML
        private Label ProfileMobileLabel;

        Alert alert;

        @FXML
        private Label ProfileMobileLabel1;

        @FXML
        private Label ProfileNameLabel;

        @FXML
        private Label ProfileNameLabel1;

        @FXML
        private Label ProfileNameLabel2;

        @FXML
        private Label ProfileUsernameLabel;

        @FXML
        private Label ProfilePasswordLabel;

        @FXML
        private Label ProfileUsernameLabel1;

        @FXML
        private Button SaveProfile;

        String passwordcurr;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                // connecting to database to access the profile details to be showed on Personal Information page.....
                con = DatabaseConnection.getConnection();
                //create query
                String query = "select name, email, username, mobile, passwd from users where email = ?;";

                //open the prepared statement
                pst = con.prepareStatement(query);
                pst.setString(1, login.loginEmailId);
                rs = pst.executeQuery();

                System.out.println(pst);
                //display the results
                while(rs.next()){
                    ProfileNameLabel.setText(rs.getString("name"));
                    ProfileUsernameLabel.setText(rs.getString("username"));
                    ProfileEmailLabel.setText(rs.getString("email"));
                    ProfileMobileLabel.setText(rs.getString("mobile"));
                    ProfilePasswordLabel.setText(rs.getString("passwd"));
                }
                pst.close();


                passwordcurr=ProfilePasswordLabel.getText();
                NameEdit.setText(ProfileNameLabel.getText());
                MobileEdit.setText(ProfileMobileLabel.getText());
                UsernameEdit.setText(ProfileUsernameLabel.getText());
                PasswordEdit.setText(ProfilePasswordLabel.getText());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void OnClickEdit(ActionEvent event) {
            NameEdit.setVisible(true);
            MobileEdit.setVisible(true);
            UsernameEdit.setVisible(true);
            PasswordEdit.setVisible(true);
        }


        @FXML
        void OnClickSave(ActionEvent event){

            String oldpassword=passwordcurr;
            TextInputDialog textInputDialog=new TextInputDialog();
            textInputDialog.setTitle("Confirmation!");
            textInputDialog.setHeaderText("Enter your current password to confirm!");
            textInputDialog.setContentText("Password:");
            Optional<String> result=textInputDialog.showAndWait();
            String s1 = String.valueOf(result.get());
            System.out.println(s1);
            if (oldpassword.equals(s1)){
                //all good, continue
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR, "Incorrect information. Please try again.");
                alert.showAndWait();
                return;
            }
            String NewName= NameEdit.getText();
            String NewMobile= MobileEdit.getText();
            String NewUsername= UsernameEdit.getText();
            String NewPassword= PasswordEdit.getText();

            Connection con = null;
            PreparedStatement pst = null;

            try {
                // connecting to database to update the details
                con = DatabaseConnection.getConnection();
                //create query
                String query = "update users " +
                        "set name = ? , username = ? , passwd = ? ,mobile = ? WHERE email = ?;";

                //open the prepared statement
                pst = con.prepareStatement(query);
                pst.setString(1, NewName);
                pst.setString(2, NewUsername);
                pst.setString(3, NewPassword);
                pst.setString(4, NewMobile);
                pst.setString(5, login.loginEmailId);

                System.out.println(pst);

                int rowAffected = pst.executeUpdate();
                System.out.println(String.format("Row affected %d", rowAffected));

                pst.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            //display the results
            ProfileNameLabel.setText(NewName);
            ProfileUsernameLabel.setText(NewUsername);
            ProfileEmailLabel.setText(login.loginEmailId);
            ProfileMobileLabel.setText(NewMobile);
            ProfilePasswordLabel.setText(NewPassword);

            NameEdit.setVisible(false);
            MobileEdit.setVisible(false);
            UsernameEdit.setVisible(false);
            PasswordEdit.setVisible(false);
        }
        @FXML
        void OnClickBack(ActionEvent event) {
            Loader loader = new Loader("../Views/home page.fxml", BackToHS, "Home Page");
        }

}


