package ru.akoval.monitoring.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.akoval.monitoring.DAO.DiseaseDAO;
import ru.akoval.monitoring.entities.Disease;


public class DiseaseEditDialogController {

    @FXML
    private AnchorPane diseaseEditPanel;
    @FXML
    private TextField codeField;
    @FXML
    private TextField titleField;


    private DiseaseTableController diseaseTableController;
    private Disease disease;


    @FXML
    private void initialize() {
    }


    public void setDiseaseTableController(DiseaseTableController diseaseTableController) {
        this.diseaseTableController = diseaseTableController;
    }

    public void setDiseaseEditNow(Disease disease) {
        this.disease = disease;
        codeField.setText(String.valueOf(disease.getCode()));
        titleField.setText(disease.getTitle());
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (isInputValid(stage)) {
            Disease disease = new Disease(
                    -1,//Поправить момент с изменением
                    codeField.getText().hashCode(),
                    titleField.getText()
            );
            disease = DiseaseDAO.setDiseaseData(disease);
            this.diseaseTableController.getDiseaseTable().getItems().add(disease);

            stage.close();
        }
        if (disease != null) {
            disease = DiseaseDAO.delete(disease);
            this.diseaseTableController.getDiseaseTable().getItems().remove(disease);
            this.disease = null;
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

        if (codeField.toString() == null || codeField.toString().length() == 0) {
            errorMessage += "Не заполнена строка шифр!\n";
        }
        if (titleField.toString() == null || titleField.toString().length() == 0) {
            errorMessage += "Не заполнена строка наименование!\n";
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


