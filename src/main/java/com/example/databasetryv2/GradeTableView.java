package com.example.databasetryv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class GradeTableView {
    @FXML
    private TableView<ShowGrade> table;
    @FXML
    private TableColumn<ShowGrade, String> email_col;
    @FXML
    private TableColumn<ShowGrade, String> grade_col;

    private ObservableList<ShowGrade> showGrade = FXCollections.observableArrayList();

    public void funShowGrade(String emaildoct) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
        showGrade.clear();

        int courid = 0;
        String Query1 = "SELECT * FROM course where Email_Doctor=?";
        PreparedStatement preparedStatement1 = con.prepareStatement(Query1);
        preparedStatement1.setString(1, emaildoct);
        ResultSet res1 = preparedStatement1.executeQuery();
        while (res1.next()) {
            courid = res1.getInt("ID");
        }

        int examid = 0;
        String Query2 = "select * from exam where Email_doctor=? AND ID_Course=?";
        PreparedStatement preparedStatement2 = con.prepareStatement(Query2);
        preparedStatement2.setString(1, emaildoct);
        preparedStatement2.setInt(2, courid);
        ResultSet res2 = preparedStatement2.executeQuery();
        while (res2.next()) {
            examid = res2.getInt("ID");
        }

        String Query3 = "SELECT * FROM exam_resulte where id_exam=?";
        PreparedStatement preparedStatement3 = con.prepareStatement(Query3);
        preparedStatement3.setInt(1, examid);
        ResultSet res3 = preparedStatement3.executeQuery();
        while (res3.next()) {
            showGrade.add(new ShowGrade(res3.getString("Email_stud"), res3.getInt("grade") + ""));
        }
        table.setItems(showGrade);
        email_col.setCellValueFactory(new PropertyValueFactory<>("emailstuent"));
        grade_col.setCellValueFactory(new PropertyValueFactory<>("studgrade"));
    }
}
