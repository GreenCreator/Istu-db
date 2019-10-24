package ru.akoval.monitoring.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import ru.akoval.monitoring.DAO.DepartmentDAO;
import ru.akoval.monitoring.entities.Department;

public class DepartmentEditDialogController {

    @FXML
    private AnchorPane depatmentEditPanel;
    @FXML
    private TextField shortTitleField;
    @FXML
    private TextField titleField;


    private DepartmentTableController departmentTableController;
    private Department department;


    @FXML
    private void initialize() {
    }


    public void setDepartmentTableController(DepartmentTableController departmentTableController) {
        this.departmentTableController = departmentTableController;
    }

    public void setDepartmentEditNow(Department department) {
        this.department = department;
        shortTitleField.setText(department.getShortTitle());
        titleField.setText(department.getTitle());
    }

    @FXML
    private void handleOk(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (isInputValid(stage)) {
            Department department = new Department(
                    -1,//Поправить момент с изменением
                    shortTitleField.getText(),
                    titleField.getText()
            );
            department = DepartmentDAO.setDepartmentData(department);
            this.departmentTableController.getDepartmentTable().getItems().add(department);

            stage.close();
        }
        if (department != null) {
            department = DepartmentDAO.delete(department);
            this.departmentTableController.getDepartmentTable().getItems().remove(department);
            this.department = null;
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

        if (shortTitleField.toString() == null || shortTitleField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Шифр!\n";
        }
        if (titleField.toString() == null || titleField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Полное название!\n";
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



