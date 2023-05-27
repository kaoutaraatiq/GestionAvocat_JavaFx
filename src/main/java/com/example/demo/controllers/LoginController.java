package com.example.demo.controllers;

import com.example.demo.dao.impl.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField tLogin;
    public PasswordField tPw;
    public Button btnCon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    btnCon.setOnAction(ActionEvent->login());
    }
    public void Home(){
        try{
            Parent parent= FXMLLoader.load(getClass().getResource("/Fxml/Avocats.fxml"));
            Stage stage=new Stage();
           stage.setMaximized(true);
           stage.setScene(new Scene(parent));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void login(){

        PreparedStatement st=null;
        ResultSet rs=null;
        Connection con= DB.getConnection();
        try {
            st=con.prepareStatement("SELECT * FROM users where login=? AND password=?");
        st.setString(1,tLogin.getText());
        st.setString(2,tPw.getText());
            rs=st.executeQuery();
        if (rs.next()){
            Home();
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING,"Login failed", ButtonType.OK);
            alert.show();
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
