/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.databasetryv2;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * @author Ibrahim M Zeid
 */
public class Doctor_SlidbarController implements Initializable {
    @FXML
    private Label lab_email;
    @FXML
    private VBox main_pane;
    @FXML
    private Button log_out;
    @FXML
    private TextField txt_name_exam, txt_time_exam;

    static String emaildoctor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }

    TryClass tryClass = new TryClass();

    @FXML
    void question_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_Questions_Page.fxml"));
            root = fxmlLoader.load();
            ShowQuestion showQuestion = fxmlLoader.getController();
            showQuestion.GetExam(emaildoctor);
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Doctor_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exam_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            root = FXMLLoader.load(getClass().getResource("Add_Exam.fxml"));
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Doctor_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void funaddexam(ActionEvent event) {
        if (txt_time_exam.getText() != "" || txt_name_exam.getText() != "") {
            try {
                int gradeexam = 10;
                int gradequest = 2;
                int timeexam = Integer.parseInt(txt_time_exam.getText());
                String nameexam = txt_name_exam.getText();
                int idcour = 0;
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
                String Query = "select ID from course where Email_doctor=?";
                PreparedStatement preparedStatement1 = con.prepareStatement(Query);
                preparedStatement1.setString(1, emaildoctor);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next()) {
                    idcour = resultSet1.getInt("ID");
                }
                Query = "INSERT INTO `exam` (`Name`, `Email_doctor`, `grade_exam`, `long_exam`, `ID_Course`, `ques_grade`) VALUES (?,?, ?, ?, ?, ?)";
                preparedStatement1 = con.prepareStatement(Query);
                preparedStatement1.setString(1, nameexam);
                preparedStatement1.setString(2, emaildoctor);
                preparedStatement1.setInt(3, gradeexam);
                preparedStatement1.setInt(4, timeexam);
                preparedStatement1.setInt(5, idcour);
                preparedStatement1.setInt(6, gradequest);
                preparedStatement1.addBatch();
                try {
                    int[] rowadd = preparedStatement1.executeBatch();
                    preparedStatement1.close();
                    if (rowadd.length >= 1) {
                        fundelete();
                    }
                } catch (BatchUpdateException exception) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(exception.getMessage());
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill All TextFills");
            alert.show();
        }
    }

    @FXML
    private void delet_exam() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
        String Querydel = "DELETE FROM exam WHERE Email_doctor=?";
        PreparedStatement preparedStatement1 = con.prepareStatement(Querydel);
        preparedStatement1.setString(1,emaildoctor);
        int checkdel =  preparedStatement1.executeUpdate();
        if(checkdel>=1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Exam Delete");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Exam Found");
            alert.show();
        }
    }

    @FXML
    void grade_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Show_grades.fxml"));
            root = fxmlLoader.load();
            GradeTableView showGrade = fxmlLoader.getController();
            showGrade.funShowGrade(emaildoctor);
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Doctor_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void return_to_signin_page(ActionEvent event) {
        tryClass.RunForms(log_out, "Sign In", "signin", "");
    }

    public void setLab_email(String lab_email) {
        this.lab_email.setText(lab_email);
        emaildoctor = lab_email;
    }

    private void fundelete() {
        txt_name_exam.setText("");
        txt_time_exam.setText("");
    }
}
