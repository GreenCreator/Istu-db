package ru.akoval.monitoring.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.akoval.monitoring.DAO.ClassDAO;
import ru.akoval.monitoring.DAO.SpecialtyDAO;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.entities.Specialty;

public class ClassEditDialogController {

    @FXML
    private AnchorPane classEditPanel;
    @FXML
    private TextField titleField;
    @FXML
    private ChoiceBox<Specialty> specialityField;

    private ClassTableController classTableController;
    private Class group;


    @FXML
    private void initialize() {
        specialityField.setItems(SpecialtyDAO.getSpecialtyRecords());
        specialityField.getSelectionModel().selectFirst();

    }


    public void setClassTableController(ClassTableController classTableController) {
        this.classTableController = classTableController;
    }

    public void setClassEditNow(Class group) {
        this.group = group;
        titleField.setText(group.getTitle());
        specialityField.getSelectionModel().select(group.getSpeciality().getId() - 1);
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (isInputValid(stage)) {
              Class clas = new Class(
                    -1,//Поправить момент с изменением
                    titleField.getText(),
                    SpecialtyDAO.getSpecialtyByID(specialityField.getSelectionModel().getSelectedItem().getId())
            );
            clas = ClassDAO.setClassData(clas);
            this.classTableController.getClassTable().getItems().add(clas);

            stage.close();
        }
        if (group != null) {
            group = ClassDAO.delete(group);
            this.classTableController.getClassTable().getItems().remove(group);
            this.group = null;
        }

    }

    @FXML
    private void handleCancel(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    private boolean isInputValid(Stage stage) {
        String errorMessage = "";

        if (titleField.toString() == null || titleField.toString().length() == 0) {
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


