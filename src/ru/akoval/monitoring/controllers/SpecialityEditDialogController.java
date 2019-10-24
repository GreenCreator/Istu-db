package ru.akoval.monitoring.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.akoval.monitoring.DAO.DepartmentDAO;
import ru.akoval.monitoring.DAO.SpecialtyDAO;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.entities.Department;
import ru.akoval.monitoring.entities.Specialty;

public class SpecialityEditDialogController {

    @FXML
    private AnchorPane specEditPanel;
    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<Department> departmentField;


    private SpecialityTableController specialityTableController;
    private Specialty specialty;



    @FXML
    private void initialize() {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        departmentField.setItems(departmentDAO.getDepartmentRecords());
        departmentField.getSelectionModel().selectFirst();

    }


    public void setSpecialtyTableController(SpecialityTableController specialtyTableController){
        this.specialityTableController = specialtyTableController;
    }
    public void setSpecialtyEditNow(Specialty specialty){
        this.specialty = specialty;
        nameField.setText(String.valueOf(specialty.getTitle()));
        departmentField.getSelectionModel().select(specialty.getDepartment().getId()-1);
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (isInputValid(stage)) {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Specialty specialty = new Specialty(
                    -1,//Поправить момент с изменением
                    nameField.getText(),
                    departmentDAO.getDepartmentByID(departmentField.getSelectionModel().getSelectedItem().getId())
            );
            specialty = SpecialtyDAO.setSpecialtyData(specialty);
            this.specialityTableController.getSpecTable().getItems().add(specialty);

            stage.close();
        }
        if (specialty!=null){
            specialty = SpecialtyDAO.delete(specialty);
            this.specialityTableController.getSpecTable().getItems().remove(specialty);
            this.specialty = null;
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

        if (nameField.toString() == null || nameField.toString().length() == 0) {
            errorMessage += "Не заполнена строка Имя!\n";
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
