package ru.akoval.monitoring.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.akoval.monitoring.DAO.ClassDAO;
import ru.akoval.monitoring.DAO.StudentDAO;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.entities.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Окно для изменения информации об адресате.
 */
public class StudentEditDialogController {

    @FXML
    private AnchorPane studEditPanel;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private DatePicker dobField;
    @FXML
    private ChoiceBox<String> genderField;
    @FXML
    private ChoiceBox<Class> groupField;


    private StudentTableController studentTableController;
    private Student student;


    @FXML
    private void initialize() {

        ClassDAO classDAO = new ClassDAO();

        //Genders in dropdown ChoiceBox
        genderField.setItems(FXCollections.observableArrayList("Мужской", "Женский"));
        genderField.getSelectionModel().selectFirst();
        dobField.setPromptText("dd-MM-yyyy");
        dobField.setValue(LocalDate.now());
        //Groups in dropdown ChoiceBox
        groupField.setItems(classDAO.getClassRecords());
        groupField.getSelectionModel().selectFirst();

    }


    public void setStudentTableController(StudentTableController studentTableController) {
        this.studentTableController = studentTableController;
    }

    public void setStudentEditNow(Student student) {
        this.student = student;
        surnameField.setText(student.getSurname());
        nameField.setText(student.getName());
        middleNameField.setText(student.getMiddlename());
        dobField.setValue(student.getDob());
        genderField.getSelectionModel().select(student.getGender());
        groupField.getSelectionModel().select(student.getGroup().getId() - 1);

    }


    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (isInputValid(stage)) {
            ClassDAO classDAO = new ClassDAO();
            Student student = new Student(
                    -1,//Поправить момент с изменением
                    surnameField.getText(),
                    nameField.getText(),
                    middleNameField.getText(),
                    dobField.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    genderField.getSelectionModel().getSelectedIndex(),
                    classDAO.getClassByID(groupField.getSelectionModel().getSelectedItem().getId())
            );
            student = StudentDAO.setStudentData(student);
            this.studentTableController.getStudentTable().getItems().add(student);

            stage.close();
        }
        if (student != null) {
            student = StudentDAO.delete(student);
            this.studentTableController.getStudentTable().getItems().remove(student);
            this.student = null;
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid(Stage stage) {
        String errorMessage = "";

        if (surnameField.getText() == null || surnameField.getText().length() == 0) {
            errorMessage += "Не заполнена строка Фамилия!\n";
        }
        if (nameField.toString() == null || nameField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Имя!\n";
        }
        if (middleNameField.toString() == null || middleNameField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Отчество!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}
