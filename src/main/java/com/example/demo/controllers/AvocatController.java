package com.example.demo.controllers;

import com.example.demo.dao.impl.DB;
import com.example.demo.entities.Avocat;
import com.example.demo.service.AvocatService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AvocatController implements Initializable {
    Connection con = null;
    PreparedStatement st =null;
    ResultSet rs=null;
    AvocatService avocatService =new AvocatService();

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tAdress;

    @FXML
    private TextField tDescription;

    @FXML
    private TextField tNom;

    @FXML
    private TextField tPrenom;

    @FXML
    private TextField tRendez_vous;

    @FXML
    private TextField tSpecialite;

    @FXML
    private TextField tTelephone;

    @FXML
    private TableColumn<Avocat, String> colAdress;

    @FXML
    private TableColumn<Avocat, String> colDescription;

    @FXML
    private TableColumn<Avocat, Integer> colId;

    @FXML
    private TableColumn<Avocat, String> colNom;

    @FXML
    private TableColumn<Avocat, String> colPrenom;

    @FXML
    private TableColumn<Avocat, String> colRd;

    @FXML
    private TableColumn<Avocat, String> colSpecialite;

    @FXML
    private TableColumn<Avocat, String> colTelephone;

    @FXML
    private TableView<Avocat> table;
    int id=0;

    @FXML
    void clearField(ActionEvent event) {
        clear();

    }

    @FXML
    void creatAvocat(ActionEvent event) {
        String insert="INSERT INTO avocat (Nom, prenom,adress,description,rendez_vous,specialite,telephone) VALUES (?,?,?,?,?,?,?)";
        con= DB.getConnection();
        try {
            st=con.prepareStatement(insert);
            st.setString(1,tNom.getText());
            st.setString(2,tPrenom.getText());
            st.setString(3,tAdress.getText());
            st.setString(4,tDescription.getText());
            st.setString(5,tRendez_vous.getText());
            st.setString(6,tSpecialite.getText());
            st.setString(7,tTelephone.getText());
            st.executeUpdate();
            clear();
            showAvocats();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void getData(MouseEvent event) {
        Avocat avocat=table.getSelectionModel().getSelectedItem();
        id=avocat.getId();
        tNom.setText(avocat.getNom());
        tPrenom.setText(avocat.getPrenom());
        tAdress.setText(avocat.getAdress());
        tDescription.setText(avocat.getDescription());
        tRendez_vous.setText(avocat.getRendez_vous());
        tSpecialite.setText(avocat.getSpecialite());
        tTelephone.setText(avocat.getTelephone());
        btnSave.setDisable(true);

    }
    void clear(){
        tNom.setText(null);
        tPrenom.setText(null);
        tAdress.setText(null);
        tDescription.setText(null);
        tRendez_vous.setText(null);
        tSpecialite.setText(null);
        tTelephone.setText(null);
        btnSave.setDisable(false);

    }

    @FXML
    void deleteAvocat(ActionEvent event) {
        String delete ="DELETE FROM avocat WHERE id = ?";
        con= DB.getConnection();
        try {
            st=con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showAvocats();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void updateAvocat(ActionEvent event) {
        String update="UPDATE avocat SET Nom = ?,prenom = ?,adress = ?,description = ?,rendez_vous = ?,specialite = ?,telephone = ? WHERE Id = ?";
        con= DB.getConnection();
        try {
            st=con.prepareStatement(update);
            st.setString(1,tNom.getText());
            st.setString(2,tPrenom.getText());
            st.setString(3,tAdress.getText());
            st.setString(4,tDescription.getText());
            st.setString(5,tRendez_vous.getText());
            st.setString(6,tSpecialite.getText());
            st.setString(7,tTelephone.getText());
            st.setInt(8,id);
            st.executeUpdate();
            showAvocats();
            clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAvocats();

    }
    public ObservableList<Avocat> getAvocats(){
        ObservableList<Avocat> avocats= FXCollections.observableArrayList();
        String query="select* from avocat";
        con= DB.getConnection();
        try {
            st=con.prepareStatement(query);
            rs=st.executeQuery();
            while (rs.next()){
                Avocat avocat=new Avocat();
                avocat.setId(rs.getInt("id"));
                avocat.setNom(rs.getString("nom"));
                avocat.setPrenom(rs.getString("prenom"));
                avocat.setAdress(rs.getString("adress"));
                avocat.setDescription(rs.getString("description"));
                avocat.setRendez_vous(rs.getString("rendez_vous"));
                avocat.setSpecialite(rs.getString("specialite"));
                avocat.setTelephone(rs.getString("telephone"));
                avocats.add(avocat);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return avocats;
    }
    public void showAvocats(){
        ObservableList<Avocat> list=getAvocats();
        table.setItems(list);
        colId.setCellValueFactory(new PropertyValueFactory<Avocat,Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Avocat,String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Avocat,String>("prenom"));
        colAdress.setCellValueFactory(new PropertyValueFactory<Avocat,String>("adress"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Avocat,String>("description"));
        colRd.setCellValueFactory(new PropertyValueFactory<Avocat,String>("rendez_vous"));
        colSpecialite.setCellValueFactory(new PropertyValueFactory<Avocat,String>("specialite"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<Avocat,String>("telephone"));
    }
FileChooser fileChooser=new FileChooser();
    @FXML
    void importExcel(ActionEvent event) {
        File file=fileChooser.showOpenDialog(new Stage());
        avocatService.importerDepuisExcel(file.getAbsolutePath());
        showAvocats();

    }

    @FXML
    void ExportExcel(ActionEvent event) {
        avocatService.exporterVersExcel("src/main/resources/avocatsList.xlsx");

    }
}

