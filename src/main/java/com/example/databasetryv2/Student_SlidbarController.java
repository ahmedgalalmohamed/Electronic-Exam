/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.databasetryv2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author
 */
public class Student_SlidbarController implements Initializable {


    @FXML
    private Button log_out;
    @FXML
    private VBox main_pane;
    @FXML
    private Label lab_email;
    @FXML
    private Button cloud_exam, ai_exam, soft_exam, programming_exam, os_exam, database_exam;
    static String emailstudent;

    TryClass tryClass = new TryClass();

    @FXML
    void ai_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentFrame.fxml"));
            root = fxmlLoader.load();
            ShowQuestionStudent showQuestionStudent = fxmlLoader.getController();
            main_pane.getChildren().add(root);
            showQuestionStudent.funGetQuestion(emailstudent, "Artificial Intelligence", ai_exam, main_pane);
        } catch (IOException ex) {
            Logger.getLogger(Student_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cloud_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentFrame.fxml"));
            root = fxmlLoader.load();
            ShowQuestionStudent showQuestionStudent = fxmlLoader.getController();
            main_pane.getChildren().add(root);
            showQuestionStudent.funGetQuestion(emailstudent, "Cloud Computing", cloud_exam, main_pane);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Student_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void database_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentFrame.fxml"));
            root = fxmlLoader.load();
            ShowQuestionStudent showQuestionStudent = fxmlLoader.getController();
            main_pane.getChildren().add(root);
            showQuestionStudent.funGetQuestion(emailstudent, "DataBase", database_exam, main_pane);
        } catch (IOException ex) {
            Logger.getLogger(Student_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void os_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentFrame.fxml"));
            root = fxmlLoader.load();
            ShowQuestionStudent showQuestionStudent = fxmlLoader.getController();
            main_pane.getChildren().add(root);
            showQuestionStudent.funGetQuestion(emailstudent, "Operating System", os_exam, main_pane);
        } catch (IOException ex) {
            Logger.getLogger(Student_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void programming_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentFrame.fxml"));
            root = fxmlLoader.load();
            ShowQuestionStudent showQuestionStudent = fxmlLoader.getController();
            main_pane.getChildren().add(root);
            showQuestionStudent.funGetQuestion(emailstudent, "Java Programming", programming_exam, main_pane);
        } catch (IOException ex) {
            Logger.getLogger(Student_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void soft_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentFrame.fxml"));
            root = fxmlLoader.load();
            ShowQuestionStudent showQuestionStudent = fxmlLoader.getController();
            main_pane.getChildren().add(root);
            showQuestionStudent.funGetQuestion(emailstudent, "Software Engineering", soft_exam, main_pane);
        } catch (IOException ex) {
            Logger.getLogger(Student_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setLab_email(String lab_email) {
        this.lab_email.setText(lab_email);
        emailstudent = lab_email;
    }
}
