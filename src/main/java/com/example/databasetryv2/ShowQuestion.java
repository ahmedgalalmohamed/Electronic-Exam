package com.example.databasetryv2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowQuestion implements Initializable {
    @FXML
    private ComboBox<String> combox_item;
    @FXML
    private TextArea question_input, answer_input1, answer_input2, answer_input3, answer_input4, correct_answer_input;
    @FXML
    private TextField txt_quest;

    ObservableList<String> list = FXCollections.observableArrayList();
    ArrayList<Integer> listexamid = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void funaddquestion() throws ClassNotFoundException, SQLException {
        if (combox_item.getValue() != null) {
            if (question_input.getText() != "" || txt_quest.getText() != "" || answer_input1.getText() != "" || answer_input2.getText() != "" || answer_input3.getText() != "" || answer_input4.getText() != "" || correct_answer_input.getText() != "") {
                int numquest = Integer.parseInt(txt_quest.getText());
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
                int idexam = listexamid.get(list.indexOf(combox_item.getValue()));
                String Query = "INSERT INTO `question` (`ID`, `id_exam`, `Question_Content`, `Choose1`, `Choose2`, `Choose3`, `Choose4`, `ans_correct`) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement1 = con.prepareStatement(Query);
                preparedStatement1.setInt(1, numquest);
                preparedStatement1.setInt(2, idexam);
                preparedStatement1.setString(3, question_input.getText());
                preparedStatement1.setString(4, answer_input1.getText());
                preparedStatement1.setString(5, answer_input2.getText());
                preparedStatement1.setString(6, answer_input3.getText());
                preparedStatement1.setString(7, answer_input4.getText());
                preparedStatement1.setString(8, correct_answer_input.getText());
                preparedStatement1.addBatch();
                try {
                    int[] rowadd = preparedStatement1.executeBatch();
                    preparedStatement1.close();
                    if (rowadd.length >= 1) {
                        fundelete();
                    }
                } catch (BatchUpdateException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All TextFills");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("First Create Exam Or Choose Exam");
            alert.show();
        }
    }

    public void GetExam(String emaildoct) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
        list.clear();
        int idcourse = 0;
        String Query1 = "select ID from course where Email_doctor=?";
        PreparedStatement preparedStatement1 = con.prepareStatement(Query1);
        preparedStatement1.setString(1, emaildoct);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()) {
            idcourse = Integer.parseInt(resultSet1.getString("ID"));
        }
        String Query2 = "SELECT * FROM exam where Email_doctor=? And ID_Course=?";
        PreparedStatement preparedStatement = con.prepareStatement(Query2);
        preparedStatement.setString(1, emaildoct);
        preparedStatement.setInt(2, idcourse);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            list.add(res.getString("Name"));
            listexamid.add(res.getInt("ID"));
        }
        combox_item.setItems(list);
    }

    private void fundelete() {
        question_input.setText("");
        answer_input1.setText("");
        answer_input2.setText("");
        answer_input3.setText("");
        answer_input4.setText("");
        correct_answer_input.setText("");
        txt_quest.setText("");
    }
}
