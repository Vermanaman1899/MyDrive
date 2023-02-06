package Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AllUploadedFileControllers extends FileViewControllers implements Initializable {

    @FXML
    private Button MoveToTrash;
    @FXML
    private Button Star;

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<FileList> data;








    @FXML
    private TableView<FileList> MyFilesTable;

    @FXML
    private TableColumn<FileList, Date> columnDate;

    @FXML
    private TableColumn<FileList, String> columnFileName;

    @FXML
    private TableColumn<FileList, Long> columnFileSize;

    @FXML
    private TableColumn<FileList, String> columnFileType;
    Integer index;
    @FXML
    void onClickOpen(){

        Loader loader = new Loader("../Views/fileView.fxml", "File");

        index = MyFilesTable.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        //set connection and retrieve filecontent, file name we can set from table column only
        String query = "select filecontent from fileslist where filename = ? and uploadedby = ? and trash = 0";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,columnFileName.getCellData(index).toString());
            pst.setString(2,login.loginEmailId);
            ResultSet rs = pst.executeQuery();

            static_FileNameLabel.setText(columnFileName.getCellData(index));
            while(rs.next()){
                System.out.println(rs.getString("filecontent"));
                static_FileContentTextArea.setText(rs.getString("filecontent"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void onClickDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Move To Trash");
        alert.setHeaderText("Conformation Needed !");
        alert.setContentText("Do you want this file to move to trash ?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            index = MyFilesTable.getSelectionModel().getSelectedIndex();

            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = null;
            ResultSet rs = null;

            String query = "update fileslist set trash = 1 where filename = ? and uploadedby = ?";

            try {
                pst = con.prepareStatement(query);
                pst.setString(1,columnFileName.getCellData(index).toString());
                pst.setString(2, login.loginEmailId);
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Loader loader = new Loader("../Views/allUploadedFiles.fxml", MoveToTrash, "My Files");
        }
    }

    @FXML
    void onClickStar(){

        index = MyFilesTable.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        String query = "update fileslist set favourite = 1 where filename = ? and uploadedby = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,columnFileName.getCellData(index).toString());
            pst.setString(2,login.loginEmailId);
            pst.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = DatabaseConnection.getConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
    }

    private void setCellTable(){
        columnFileName.setCellValueFactory(new PropertyValueFactory<FileList, String>("name"));
        columnDate.setCellValueFactory(new PropertyValueFactory<FileList, Date>("date"));
        columnFileType.setCellValueFactory(new PropertyValueFactory<FileList, String>("type"));
        columnFileSize.setCellValueFactory(new PropertyValueFactory<FileList, Long>("size"));
    }

    private void loadDataFromDatabase(){
        try {
            String query = "select filename, dateofupload, filetype, sizeoffile from fileslist where trash = 0";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                data.add(new FileList(rs.getString("filename") , rs.getDate("dateofupload"), rs.getString("filetype"), rs.getString("sizeoffile")));
            }
        }
        catch(SQLException e){
            System.out.println(pst);
            System.out.println(rs);


            e.printStackTrace();
        }
        MyFilesTable.setItems(data);
    }
}
