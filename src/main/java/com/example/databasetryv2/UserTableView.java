package com.example.databasetryv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UserTableView implements Initializable {
    @FXML
    private TableView<ShowUser> table;
    @FXML
    private TableColumn<ShowUser, String> email_col;
    @FXML
    private TableColumn<ShowUser, String> name_col;
    @FXML
    private TableColumn<ShowUser, String> address_col;
    private ObservableList<ShowUser> showUsers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void StudentInfo(String fxml) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
        showUsers.clear();
        String Query = "";
        if (fxml.contains("admin")) {
            Query = "SELECT * FROM admin";
        } else if (fxml.contains("doctor")) {
            Query = "SELECT * FROM doctor";
        } else {
            Query = "SELECT * FROM student";
        }
        PreparedStatement preparedStatement = con.prepareStatement(Query);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            showUsers.add(new ShowUser(res.getString("Email"), res.getString("Name"), res.getString("Address")));
        }
        table.setItems(showUsers);
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
