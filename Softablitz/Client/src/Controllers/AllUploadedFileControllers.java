package Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import Controllers.FileViewControllers;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AllUploadedFileControllers extends FileViewControllers implements Initializable {

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
    void getFile(MouseEvent event){

        Loader loader = new Loader("../Views/fileView.fxml", "File");

        index = MyFilesTable.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        //set connection and retrieve filecontent, file name we can set from table column only
        String query = "select filecontent from fileslist where filename = ? and uploadedby = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,columnFileName.getCellData(index).toString());
            pst.setString(2,login.loginEmailId);
            ResultSet rs = pst.executeQuery();

//            FileViewControllers fvc = new FileViewControllers();
            System.out.println(columnFileName.getCellData(index));

            static_FileNameLabel.setText(columnFileName.getCellData(index));
            while(rs.next()){
                System.out.println(rs.getString("filecontent"));
                static_FileContentLabel.setText(rs.getString("filecontent"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

//        Loader loader = new Loader("../Views/fileView.fxml", "File");

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
            String query = "select filename, dateofupload, filetype, sizeoffile from fileslist";
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
