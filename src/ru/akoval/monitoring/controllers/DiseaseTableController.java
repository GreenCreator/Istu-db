package ru.akoval.monitoring.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.akoval.monitoring.entities.Disease;
import ru.akoval.monitoring.DAO.DiseaseDAO;
import ru.akoval.monitoring.service.DiseaseService;

import java.io.IOException;

public class DiseaseTableController {

    @FXML
    private TableView<Disease> diseaseTable;
    @FXML
    private TableColumn<Disease, Integer> codeDiseaseColumn;
    @FXML
    private TableColumn<Disease, String> titleDiseaseColumn;

    private DiseaseService diseaseService;

    @FXML
    private void initialize() {
        diseaseService = new DiseaseService(diseaseTable);
        diseaseService.getDiseaseData();
        codeDiseaseColumn.setCellValueFactory(cellData -> cellData.getValue().codeDisease().asObject());
        titleDiseaseColumn.setCellValueFactory(cellData -> cellData.getValue().titleDisease());

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Добавить");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Изменить");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Удалить");
        cm.getItems().add(mi3);

        mi1.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/diseaseEditDialog.fxml"));
                AnchorPane parent = fxmlLoader.load();
                DiseaseEditDialogController diseaseEditDialogController = fxmlLoader.getController();
                diseaseEditDialogController.setDiseaseTableController(this);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Добавление заболевания");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        mi2.setOnAction((ActionEvent event) -> {
            Disease disease = diseaseTable.getSelectionModel().getSelectedItem();
            if (disease != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/diseaseEditDialog.fxml"));
                    AnchorPane parent = fxmlLoader.load();
                    DiseaseEditDialogController diseaseEditDialogController = fxmlLoader.getController();
                    diseaseEditDialogController.setDiseaseTableController(this);
                    diseaseEditDialogController.setDiseaseEditNow(disease);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Изменение заболевания");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mi3.setOnAction((ActionEvent event) -> {
            Disease disease = diseaseTable.getSelectionModel().getSelectedItem();
            if (disease != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить заболевание: " + disease.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление заболевания");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    disease = DiseaseDAO.delete(disease);
                    getDiseaseTable().getItems().remove(disease);
                }

            }
        });
        getDiseaseTable().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Disease disease = diseaseTable.getSelectionModel().getSelectedItem();
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(diseaseTable, t.getScreenX(), t.getScreenY());
                }
            }
        });
    }

    public TableView<Disease> getDiseaseTable() {
        return diseaseTable;
    }

    public DiseaseService getDiseaseService() {
        return diseaseService;
    }
}
