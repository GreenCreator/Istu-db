package ru.akoval.monitoring.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.akoval.monitoring.DAO.InstructorDAO;
import ru.akoval.monitoring.entities.Instructor;


public class InstructorEditDialogController {

    @FXML
    private AnchorPane instructorEditPanel;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField middlenameField;


    private InstructorTableController instructorTableController;
    private Instructor instructor;


    @FXML
    private void initialize() {
    }


    public void setInstructorTableController(InstructorTableController instructorTableController) {
        this.instructorTableController = instructorTableController;
    }

    public void setInstructorEditNow(Instructor instructor) {
        this.instructor = instructor;
        surnameField.setText(instructor.getSurname());
        nameField.setText(instructor.getName());
        middlenameField.setText(instructor.getMiddlename());
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (isInputValid(stage)) {
            Instructor instructor = new Instructor(
                    -1,//Поправить момент с изменением
                    surnameField.getText(),
                    nameField.getText(),
                    middlenameField.getText()
            );
            instructor = InstructorDAO.setInstructorData(instructor);
            this.instructorTableController.getInstructorTable().getItems().add(instructor);

            stage.close();
        }
        if (instructor != null) {
            instructor = InstructorDAO.delete(instructor);
            this.instructorTableController.getInstructorTable().getItems().remove(instructor);
            this.instructor = null;
        }
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

        if (surnameField.toString() == null || surnameField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Фамилия!\n";
        }
        if (nameField.toString() == null || nameField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Имя!\n";
        }
        if (middlenameField.toString() == null || middlenameField.toString().length() == 0) {
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
