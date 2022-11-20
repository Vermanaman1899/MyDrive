package Controllers;

import Request.SignupRequest;
import Response.SignupResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import Services.*;

public class signup{
    @FXML
    public Button Signup;
    @FXML
    public Button BackToLoginPage;

    @FXML
    public TextField SignupName;

    @FXML
    public TextField SignupEmailID;

    @FXML
    public TextField SignupUsername;

    @FXML
    public PasswordField SignupPassword;

    @FXML
    public PasswordField ConfirmSignupPassword;
    @FXML
    public TextField SignupPhone;

    Connection connection ;
    PreparedStatement pst;


    public void OnClickCreateAccount(ActionEvent actionEvent) {



//        int access=0;
        String name = SignupName.getText();
        String email = SignupEmailID.getText();
        String userName = SignupUsername.getText();
        String password = SignupPassword.getText();
        String phoneNumber = SignupPhone.getText();
        String confirmPassword = ConfirmSignupPassword.getText();

        if (password.equals(confirmPassword)) {
            SignupRequest registerRequest = new SignupRequest(name, email, userName, password,phoneNumber);
            Main.sendRequest(registerRequest);
            System.out.println("Register request sent");
            SignupResponse response = (SignupResponse) Main.getResponse();
            assert response != null;
            if (response.getMessage().length() == 0) {
                System.out.println("Please Try Again");
            } else {
                System.out.println("Signup success!");

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/softablitz", "root", "#NitinMySqlPassword0500");
                    pst = connection.prepareStatement("insert into users(username, passwd, name, email, mobile)values(?,?,?,?,?)");
                    pst.setString(1,userName);
                    pst.setString(2,password);
                    pst.setString(3,name);
                    pst.setString(4,email);
                    pst.setString(5,phoneNumber);

                    int status = pst.executeUpdate();

                    if(status ==1){
                        JOptionPane.showMessageDialog(null,"Sign up data Stored Succssfuly");
                        Loader loader = new Loader("../Views/home page.fxml", Signup, "Home Page");
                        SignupName.setText("");
                        SignupEmailID.setText("");
                        SignupUsername.setText("");
                        SignupPassword.setText("");
                        SignupPhone.setText("");
                    }

                }catch (ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Please enter correct info");
        }
    }

    public void onClickBackToLoginPage(){
        Loader loader = new Loader("../Views/login.fxml", BackToLoginPage, "Home Page");

    }

}
