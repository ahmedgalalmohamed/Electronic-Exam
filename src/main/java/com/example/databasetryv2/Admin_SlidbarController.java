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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ibrahim M Zeid
 */
public class Admin_SlidbarController implements Initializable {

    @FXML
    private TextField txt_username, txt_email, txt_address;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button adduser, log_out;
    @FXML
    private Label lab_email;
    @FXML
    private RadioButton radstudent, raddoctor, radadmin;
    @FXML
    private VBox main_pane;


    TryClass tryClass = new TryClass();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    @FXML
    private void add_member_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            root = FXMLLoader.load(getClass().getResource("Add_Member.fxml"));
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Admin_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show_admin_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Show_Member_Data.fxml"));
            root = fxmlLoader.load();
            UserTableView userTableView = fxmlLoader.getController();
            userTableView.StudentInfo("admin");
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Admin_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void show_doctor_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Show_Member_Data.fxml"));
            root = fxmlLoader.load();
            UserTableView userTableView = fxmlLoader.getController();
            userTableView.StudentInfo("doctor");
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Admin_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void show_student_page(ActionEvent event) {
        Parent root = null;
        if (main_pane.getChildren() != null) {
            main_pane.getChildren().clear();
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Show_Member_Data.fxml"));
            root = fxmlLoader.load();
            UserTableView userTableView = fxmlLoader.getController();
            userTableView.StudentInfo("student");
            main_pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Admin_SlidbarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //Start Sign Up
    @FXML
    private void funadduser() {
        if (txt_password.getText() != "" && txt_email.getText() != "") {
            if (TryClass.checkemail(txt_email.getText())) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
                    if (radstudent.isSelected()) {
                        String stattoensuresingupsearchdoctor = String.format("SELECT * FROM doctor WHERE Email=?");
                        String stattoensuresingupsearchadmin = String.format("SELECT * FROM admin WHERE Email=?");
                        PreparedStatement preparedStatementsearchdoctor = con.prepareStatement(stattoensuresingupsearchdoctor);
                        preparedStatementsearchdoctor.setString(1, txt_email.getText());
                        PreparedStatement preparedStatementsearchadmin = con.prepareStatement(stattoensuresingupsearchadmin);
                        preparedStatementsearchadmin.setString(1, txt_email.getText());
                        ResultSet resadmin = preparedStatementsearchadmin.executeQuery();
                        ResultSet resdoctor = preparedStatementsearchdoctor.executeQuery();
                        if (resadmin.isBeforeFirst() || resdoctor.isBeforeFirst()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("This Email Is Found");
                            alert.show();
                        } else {
                            String stattoensureinsert = String.format("Insert Into student (Email,Name,Address,Password) values(?,?,?,?)");
                            PreparedStatement preparedStatementinsert = con.prepareStatement(stattoensureinsert);
                            preparedStatementinsert.setString(1, txt_email.getText());
                            preparedStatementinsert.setString(2, txt_username.getText());
                            preparedStatementinsert.setString(3, txt_address.getText());
                            preparedStatementinsert.setString(4, txt_password.getText());
                            preparedStatementinsert.addBatch();
                            try {
                                int[] rowadd = preparedStatementinsert.executeBatch();
                                preparedStatementinsert.close();
                                if (rowadd.length >= 1) {
                                    DeleteTextField();
                                }
                            } catch (BatchUpdateException exception) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText(exception.getMessage());
                                alert.show();
                            }
                            preparedStatementsearchdoctor.close();
                            resdoctor.close();
                            resadmin.close();
                        }
                    } else {
                        String stattoensuresingupsearchdoctor = String.format("SELECT * FROM student WHERE Email=?");
                        PreparedStatement preparedStatementsearchdoctor = con.prepareStatement(stattoensuresingupsearchdoctor);
                        preparedStatementsearchdoctor.setString(1, txt_email.getText());
                        ResultSet resdoctor = preparedStatementsearchdoctor.executeQuery();
                        if (resdoctor.isBeforeFirst()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("This Email Is Found");
                            alert.show();
                        } else {
                            String stattoensureinsert = "";
                            if (radadmin.isSelected()) {
                                stattoensureinsert = String.format("Insert Into admin (Email,Name,Password,Address) values(?,?,?,?)");
                            } else {
                                stattoensureinsert = String.format("Insert Into doctor (Email,Password,Name,Address) values(?,?,?,?)");
                            }
                            PreparedStatement preparedStatementinsert = con.prepareStatement(stattoensureinsert);
                            preparedStatementinsert.setString(1, txt_email.getText());
                            preparedStatementinsert.setString(2, txt_username.getText());
                            preparedStatementinsert.setString(4, txt_address.getText());
                            preparedStatementinsert.setString(3, txt_password.getText());
                            preparedStatementinsert.addBatch();
                            try {
                                int[] rowadd = preparedStatementinsert.executeBatch();
                                preparedStatementinsert.close();
                                if (rowadd.length >= 1) {
                                    DeleteTextField();
                                }
                            } catch (BatchUpdateException exception) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText(exception.getMessage());
                                alert.show();
                            }
                            preparedStatementsearchdoctor.close();
                            resdoctor.close();
                        }
                    }
                    con.close();
                } catch (SQLException | ClassNotFoundException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email Is Not Valid");
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill All TextFills");
            alert.show();
        }
    }
    //End Sign Up

    @FXML
    private void return_to_signin_page(ActionEvent event) {
        tryClass.RunForms(log_out, "Sign In", "signin", "");
    }

    public void DeleteTextField() {
        txt_address.setText("");
        txt_password.setText("");
        txt_username.setText("");
        txt_email.setText("");
    }

    public void setLab_email(String lab_email) {
        this.lab_email.setText(lab_email);
    }
}
