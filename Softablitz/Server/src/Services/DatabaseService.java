package Services;

import Request.LoginRequest;
import Request.SignupRequest;
import Response.LoginResponse;
import Response.SignupResponse;
import main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {
    public static SignupResponse createUser(SignupRequest signupRequest){
        Connection connection = Main.getConnection();
        String query = "INSERT INTO users (username, passwd, name, email, mobile) VALUES (?, ?, ?, ?, ?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, signupRequest.getUsername());
            preparedStatement.setString(2, signupRequest.getPassword());
            preparedStatement.setString(3, signupRequest.getName());
            preparedStatement.setString(4, signupRequest.getEmail());
            preparedStatement.setString(5, signupRequest.getPassword());
            int res = preparedStatement.executeUpdate();
            if (res != 1) {
                return new SignupResponse("FAILED!");
            } else {
                return new SignupResponse("SUCCESS!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//    return new SignupResponse("FAILED!");
    }

    public static LoginResponse loginUser(LoginRequest loginRequest) {
        Connection connection = Main.getConnection();
        String query = "SELECT username, name, email, mobile, passwd FROM users WHERE email = ? AND passwd = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, loginRequest.getEmail());
            preparedStatement.setString(2, loginRequest.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString(1);
                String email = resultSet.getString(2);
                String username = resultSet.getString(3);
                String passwd = resultSet.getString(4);
                String mobile = resultSet.getString(5);
                return new LoginResponse(email,username,mobile,name,passwd);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
