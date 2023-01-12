package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.io.*;
import java.sql.Date;

public class FileInfoControllers {
    private int Access = 100;
    private int Favourite;

    @FXML
    public TextField FileLocation;

    @FXML
    private RadioButton PrivateRButton;
    @FXML
    private RadioButton PublicRButton;
    @FXML
    private ToggleGroup access;

    @FXML
    private RadioButton FavouriteRButton;

    @FXML
    private Button Upload;

    @FXML
    private Button Cancel;
    @FXML
    private AnchorPane scenePane;


    public void onClickPrivateRB(){}
    public void onClickPublicRB(){}
    public void onClickFavouriteRB(){}
    public void onClickCancel(){
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
    public void onClickUpload(){

        if(PrivateRButton.isSelected()){
            Access = 0;
        }
        if(PublicRButton.isSelected()){
            Access = 1;
        }
        if(Access == 100){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You are missing something..");
            alert.setContentText("Please select either Private or Public option.");
            alert.show();
        }

        if(FavouriteRButton.isSelected()){
            Favourite = 1;
        }
        else{
            Favourite = 0;
        }

        try {
            // firstly connection must be present from database ::::
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst = con.prepareStatement("insert into fileslist (filename,filetype,uploadedby,dateofupload,favourite,access,sizeoffile,filecontent) values(?,?,?,?,?,?,?,?)");
            // prepared statement entries :
            // 1.filename , 2.filetype , 3.uploadedby , 4.dateofupload , 5.favourite , 6.access , 7.sizeoffile , 8.filecontent

            Path path = Paths.get(FileLocation.getText());

            File file = path.toFile();
            String fileName = file.getName();
            long fileLength = file.length();
            long fileLengthInKb = fileLength/1024;


            pst.setString(1, fileName);
            pst.setString(2, fileName.substring(fileName.lastIndexOf(".")+1));
            pst.setString(3, login.loginEmailId);
            Date date = new java.sql.Date(System.currentTimeMillis());
            pst.setDate(4,date);
            pst.setInt(5,Favourite); // in this we have to set response from favourite radio button
            pst.setInt(6,Access);  // we have to set response from public or private radio buttons

            pst.setLong(7,fileLengthInKb); // might be possible it returns zero size of selected file , might create some issue

            pst.setCharacterStream(8, new FileReader(file), fileLength);
            pst.executeUpdate();
            System.out.println("File stored in database");


        }
        catch(Exception e){
            e.printStackTrace();
        }
        // after successful upload close the window
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


}
