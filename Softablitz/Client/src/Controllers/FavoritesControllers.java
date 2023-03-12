package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FavoritesControllers extends FileViewControllers implements Initializable {
    @FXML
    public Button Unstar;
    @FXML
    public Button BacktoHS;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<FileList> data;


    @FXML
    private TableView<FileList> FavoriteFilesTable;

    @FXML
    private TableColumn<FileList, Date> columnDate;

    @FXML
    private TableColumn<FileList, String> columnFileName;

    @FXML
    private TableColumn<FileList, Long> columnSize;

    @FXML
    private TableColumn<FileList, String> columnType;
    Integer index;


    @FXML
    void onClickUnstar(){

        index = FavoriteFilesTable.getSelectionModel().getSelectedIndex();

        if(index <= -1){
            return;
        }

        String query = "update fileslist set favourite = 0 where filename = ? and uploadedby = ?";
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

        Loader loader = new Loader("../Views/favorites.fxml",Unstar , "Favorites Page");

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
        columnType.setCellValueFactory(new PropertyValueFactory<FileList, String>("type"));
        columnSize.setCellValueFactory(new PropertyValueFactory<FileList, Long>("size"));
    }

    private void loadDataFromDatabase(){
        try {
            String query = "select filename, dateofupload, filetype, sizeoffile from fileslist where favourite = 1";
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
        FavoriteFilesTable.setItems(data);
    }

    public void onClickBack(ActionEvent actionEvent) {
        Loader loader = new Loader("../Views/home page.fxml", BacktoHS, "Home Page");
    }
}


