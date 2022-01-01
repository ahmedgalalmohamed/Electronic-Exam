package com.example.databasetryv2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private Button btn_signin;
    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_password;
    @FXML
    private RadioButton radstudent, raddoctor;
    TryClass tryClass = new TryClass();

    @FXML
    private void funsignin() throws SQLException, ClassNotFoundException {
        if (txt_password.getText() != "" && txt_email.getText() != "") {
            if (TryClass.checkemail(txt_email.getText())) {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
                if (radstudent.isSelected()) {
                    String stattoensurelogin = String.format("SELECT * FROM student WHERE Email=? AND Password=?");
                    PreparedStatement preparedStatement = con.prepareStatement(stattoensurelogin);
                    preparedStatement.setString(1, txt_email.getText());
                    preparedStatement.setString(2, txt_password.getText());
                    ResultSet res = preparedStatement.executeQuery();

                    if (!res.isBeforeFirst()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("This Student Not Found");
                        alert.show();
                    } else {
                        tryClass.RunForms(btn_signin,"Student Page","Student_Slidbar",txt_email.getText());
                    }
                    preparedStatement.close();
                    res.close();
                } else if (raddoctor.isSelected()) {
                    String stattoensurelogin = String.format("SELECT * FROM doctor WHERE Email=? AND Password=?");
                    String stattogetnamecour = String.format("SELECT * FROM course WHERE Email_Doctor=?");
                    PreparedStatement preparedStatement = con.prepareStatement(stattoensurelogin);

                    preparedStatement.setString(1, txt_email.getText());
                    preparedStatement.setString(2, txt_password.getText());

                    PreparedStatement preparedStatementcourname = con.prepareStatement(stattogetnamecour);
                    preparedStatementcourname.setString(1, txt_email.getText());

                    ResultSet res = preparedStatement.executeQuery();
                    ResultSet rescourname = preparedStatementcourname.executeQuery();

                    if (!res.isBeforeFirst()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("This Doctor Not Found");
                        alert.show();
                    } else {
                        tryClass.RunForms(btn_signin, "Doctor Page", "Doctor_Slidbar", txt_email.getText());
                    }
                    preparedStatement.close();
                    preparedStatementcourname.close();
                    res.close();
                } else {
                    String stattoensureloginasadmin = String.format("SELECT * FROM admin WHERE Email=? AND Password=?");
                    PreparedStatement preparedStatement = con.prepareStatement(stattoensureloginasadmin);
                    preparedStatement.setString(1, txt_email.getText());
                    preparedStatement.setString(2, txt_password.getText());
                    ResultSet res = preparedStatement.executeQuery();

                    if (!res.isBeforeFirst()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("This Admin Not Found");
                        alert.show();
                    } else {
                        tryClass.RunForms(btn_signin, "Admin Page", "Admin_Slidbar", txt_email.getText());
                    }
                    preparedStatement.close();
                    res.close();
                }
                con.close();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
