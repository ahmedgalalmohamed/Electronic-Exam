package com.example.databasetryv2;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TryClass implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void RunForms(Button btn_close, String title, String fxml, String lab_email) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            if (fxml.contains("Admin_Slidbar")) {
                Admin_SlidbarController admin_slidbarController = fxmlLoader.getController();
                admin_slidbarController.setLab_email(lab_email);
            }
            else if(fxml.contains("Doctor_Slidbar")){
                Doctor_SlidbarController doctor_slidbarController = fxmlLoader.getController();
                doctor_slidbarController.setLab_email(lab_email);
            }
            else if(fxml.contains("Student_Slidbar")){
                Student_SlidbarController student_slidbarController = fxmlLoader.getController();
                student_slidbarController.setLab_email(lab_email);
            }
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage stage1 = (Stage) btn_close.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }


    public static boolean checkemail(String email) {
        if (email.contains("@yahoo.com") || email.contains("@gmail.com")) {
            return true;
        }
        return false;
    }

}
