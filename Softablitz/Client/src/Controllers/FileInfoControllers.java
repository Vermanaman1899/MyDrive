package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.io.*;
import java.sql.Date;

public class FileInfoControllers {
    private String Access;
    private String Favourite;
    @FXML
    public TextField FileName;
    @FXML
    public TextField FileLocation;
    @FXML
    public TextField FileType;
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
            Access = "private";
        }
        if(PublicRButton.isSelected()){
            Access = "public";
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You are missing something..");
            alert.setContentText("Please select either Private or Public option.");
            alert.show();
        }

        if(FavouriteRButton.isSelected()){
            Favourite = "yes";
        }
        else{
            Favourite = "no";
        }

        try {
            // firstly connection must be present from database ::::
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement pst = con.prepareStatement("insert into fileslist values(?,?,?,?,?,?,?,?)");
            // prepared statement entries :
            // 1.filename , 2.filetype , 3.uploadedby , 4.dateofupload , 5.favourite , 6.access , 7.sizeoffile , 8.filecontent
            pst.setString(1, FileName.getText());
            pst.setString(2, FileType.getText());
//            pst.setString(3, email Id of account holder);
            Date date = new java.sql.Date(System.currentTimeMillis());
            pst.setDate(4,date);
            pst.setString(5,Favourite); // in this we have to set response from favourite radio button
            pst.setString(6,Access);  // we have to set response from public or private radio buttons

            File file = new File(FileLocation.getText());
            FileReader fr = new FileReader(file);
            pst.setLong(7,file.length()); // might be possible it returns zero size of selected file , might create some issue

            pst.setCharacterStream(8,fr,file.length());
            pst.executeUpdate();
            System.out.println("File stored in database");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


}
