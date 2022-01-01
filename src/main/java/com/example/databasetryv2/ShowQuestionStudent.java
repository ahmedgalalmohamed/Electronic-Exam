package com.example.databasetryv2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowQuestionStudent implements Initializable {
    @FXML
    private Label question11;
    @FXML
    RadioButton answer11, answer21, answer31, answer41;
    @FXML
    private Label question12;
    @FXML
    RadioButton answer12, answer22, answer32, answer42;
    @FXML
    private Label question13;
    @FXML
    RadioButton answer13, answer23, answer33, answer43;
    @FXML
    private Label question14;
    @FXML
    RadioButton answer14, answer24, answer34, answer44;
    @FXML
    private Label question15;
    @FXML
    RadioButton answer15, answer25, answer35, answer45;
    @FXML
    private ToggleGroup marked_answer1, marked_answer2, marked_answer3, marked_answer4, marked_answer5;
    @FXML
    private Button Submit;
    static Pane panenow = new Pane();
    int gradquet = 0;
    int gradexam = 0;
    int idexam = 0;
    String emailstud = "";
    ArrayList<String> questioncontentfromdb = new ArrayList<>();
    ArrayList<String> Answercolumn1fromdb = new ArrayList<>();
    ArrayList<String> Answercolumn2fromdb = new ArrayList<>();
    ArrayList<String> Answercolumn3fromdb = new ArrayList<>();
    ArrayList<String> Answercolumn4fromdb = new ArrayList<>();
    ArrayList<String> correctanswercolumnfromdb = new ArrayList<>();
    ArrayList<Label> questcontent = new ArrayList<>();
    ArrayList<RadioButton> Answercolquest1 = new ArrayList<>();
    ArrayList<RadioButton> Answercolquest2 = new ArrayList<>();
    ArrayList<RadioButton> Answercolquest3 = new ArrayList<>();
    ArrayList<RadioButton> Answercolquest4 = new ArrayList<>();
    ArrayList<String> answerstudent = new ArrayList<>();
    ArrayList<String> correctAnswerrandom = new ArrayList<>();

    @FXML
    private void submitquest() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");
        String Query1 = "SELECT * FROM exam_resulte Where Email_stud=? AND id_exam=?";
        PreparedStatement preparedStatement1 = con.prepareStatement(Query1);
        preparedStatement1.setString(1, emailstud);
        preparedStatement1.setInt(2, idexam);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        if (resultSet1.isBeforeFirst()) {
            fundeletequst();
            return;
        } else {
            answerstudent.clear();
            int countcorrectanswer = 0;
            String answertogle1 = marked_answer1.selectedToggleProperty().getValue().toString();
            answerstudent.add(answertogle1.substring(answertogle1.indexOf("'")));
            String answertogle2 = marked_answer2.selectedToggleProperty().getValue().toString();
            answerstudent.add(answertogle2.substring(answertogle1.indexOf("'")));
            String answertogle3 = marked_answer3.selectedToggleProperty().getValue().toString();
            answerstudent.add(answertogle3.substring(answertogle1.indexOf("'")));
            String answertogle4 = marked_answer4.selectedToggleProperty().getValue().toString();
            answerstudent.add(answertogle4.substring(answertogle1.indexOf("'")));
            String answertogle5 = marked_answer5.selectedToggleProperty().getValue().toString();
            answerstudent.add(answertogle5.substring(answertogle1.indexOf("'")));
            for (int i = 0; i < answerstudent.size(); i++) {
                if (answerstudent.get(i).contains(correctAnswerrandom.get(i))) {
                    countcorrectanswer++;
                }
            }

            String Query2 = "INSERT INTO exam_resulte (id_exam, grade, Email_stud) VALUES (?,?,?)";
            PreparedStatement preparedStatement2 = con.prepareStatement(Query2);
            preparedStatement2.setInt(1, idexam);
            preparedStatement2.setInt(2, countcorrectanswer * 2);
            preparedStatement2.setString(3, emailstud);
            preparedStatement2.addBatch();
            try {
                int[] rowadd = preparedStatement2.executeBatch();
                if (rowadd.length >= 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Your Grade Is " + (countcorrectanswer * 2) + " From " + gradexam);
                    alert.show();
                    panenow.getChildren().clear();
                    fundeletequst();
                    return;
                }
            } catch (BatchUpdateException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(exception.getMessage());
                alert.show();
            }
        }
        con.close();
        preparedStatement1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questcontent.add(question11);
        questcontent.add(question12);
        questcontent.add(question13);
        questcontent.add(question14);
        questcontent.add(question15);
        //==============================================================//
        Answercolquest1.add(answer11);
        Answercolquest1.add(answer12);
        Answercolquest1.add(answer13);
        Answercolquest1.add(answer14);
        Answercolquest1.add(answer15);
        //==============================================================//
        Answercolquest2.add(answer21);
        Answercolquest2.add(answer22);
        Answercolquest2.add(answer23);
        Answercolquest2.add(answer24);
        Answercolquest2.add(answer25);
        //==============================================================//
        Answercolquest3.add(answer31);
        Answercolquest3.add(answer32);
        Answercolquest3.add(answer33);
        Answercolquest3.add(answer34);
        Answercolquest3.add(answer35);
        //==============================================================//
        Answercolquest4.add(answer41);
        Answercolquest4.add(answer42);
        Answercolquest4.add(answer43);
        Answercolquest4.add(answer44);
        Answercolquest4.add(answer45);
    }

    public void funGetQuestion(String emailstudent, String courname, Button button, Pane pane) throws ClassNotFoundException, SQLException {
        questioncontentfromdb.clear();
        Answercolumn1fromdb.clear();
        Answercolumn2fromdb.clear();
        Answercolumn3fromdb.clear();
        Answercolumn4fromdb.clear();
        correctanswercolumnfromdb.clear();
        correctAnswerrandom.clear();
        emailstud = emailstudent;
        panenow = pane;
        for (int i = 0; i < questcontent.size(); i++) {
            questcontent.get(i).setText("");
        }

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/electronic_exam", "root", "");

        int idcourse = 0;
        String Query1 = "select ID from course where Name=?";
        PreparedStatement preparedStatement1 = con.prepareStatement(Query1);
        preparedStatement1.setString(1, courname);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while (resultSet1.next()) {
            idcourse = resultSet1.getInt("ID");
        }


        String Query2 = "SELECT * FROM exam where ID_Course=?";
        PreparedStatement preparedStatement2 = con.prepareStatement(Query2);
        preparedStatement2.setInt(1, idcourse);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while (resultSet2.next()) {
            idexam = resultSet2.getInt("ID");
            gradquet = resultSet2.getInt("ques_grade");
            gradexam = resultSet2.getInt("grade_exam");
        }

        if (idexam == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No Exam");
            alert.show();
            pane.getChildren().clear();
            Submit.setVisible(false);
            return;
        }
        Submit.setVisible(true);
        String Query3 = "SELECT * FROM question where id_exam=?";
        PreparedStatement preparedStatement3 = con.prepareStatement(Query3);
        preparedStatement3.setInt(1, idexam);
        ResultSet resultSet3 = preparedStatement3.executeQuery();

        while (resultSet3.next()) {
            questioncontentfromdb.add(resultSet3.getString("Question_Content"));
            Answercolumn1fromdb.add(resultSet3.getString("Choose1"));
            Answercolumn2fromdb.add(resultSet3.getString("Choose2"));
            Answercolumn3fromdb.add(resultSet3.getString("Choose3"));
            Answercolumn4fromdb.add(resultSet3.getString("Choose4"));
            correctanswercolumnfromdb.add(resultSet3.getString("ans_correct"));
        }

        String Query4 = "SELECT * FROM exam_resulte Where Email_stud=? AND id_exam=?";
        PreparedStatement preparedStatement4 = con.prepareStatement(Query4);
        preparedStatement4.setString(1, emailstud);
        preparedStatement4.setInt(2, idexam);
        ResultSet resultSet4 = preparedStatement4.executeQuery();
        if (resultSet4.isBeforeFirst()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("this exam you perform before");
            alert.show();
            pane.getChildren().clear();
            fundeletequst();
            return;
        }
        int random = 0;

        if (questioncontentfromdb.size() >= 5) {
            for (int i = 0; i < questcontent.size(); i++) {
                random = randomWithRange(0, questioncontentfromdb.size() - 1);


                for (int j = 0; j < questcontent.size(); j++) {
                    if (questcontent.get(j).getText() == questioncontentfromdb.get(random)) {
                        random = randomWithRange(0, questioncontentfromdb.size() - 1);
                    }
                }

                questcontent.get(i).setText(questioncontentfromdb.get(random));

                Answercolquest1.get(i).setText(Answercolumn1fromdb.get(random));

                Answercolquest2.get(i).setText(Answercolumn2fromdb.get(random));

                Answercolquest3.get(i).setText(Answercolumn3fromdb.get(random));

                Answercolquest4.get(i).setText(Answercolumn4fromdb.get(random));

                correctAnswerrandom.add(correctanswercolumnfromdb.get(random));
            }
        } else {
            pane.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No Exam");
            alert.show();
            pane.getChildren().clear();
            fundeletequst();
        }
        con.close();
        preparedStatement1.close();
        preparedStatement2.close();
        preparedStatement3.close();
    }

    private void fundeletequst() {
        for (int i = 0; i < questcontent.size(); i++) {
            questcontent.get(i).setText("");

            Answercolquest1.get(i).setText("");

            Answercolquest2.get(i).setText("");

            Answercolquest3.get(i).setText("");

            Answercolquest4.get(i).setText("");
        }
        Submit.setVisible(false);
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}
