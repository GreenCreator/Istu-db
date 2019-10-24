package ru.akoval.monitoring.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.akoval.monitoring.entities.Specialty;
import ru.akoval.monitoring.DAO.SpecialtyDAO;
import ru.akoval.monitoring.service.SpecialtyService;

import java.io.IOException;


public class SpecialityTableController {
    @FXML
    private AnchorPane answerWindow;
    @FXML
    private TableView<Specialty> specTable;
    @FXML
    private TableColumn<Specialty, String> titleColumn;
    @FXML
    private TableColumn<Specialty, String> departmentColumn;
    private SpecialtyService specialtyService;

    @FXML
    private void initialize() {
        specialtyService = new SpecialtyService(this);
        specTable.setItems(specialtyService.getSpecData());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Добавить");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Изменить");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Удалить");
        cm.getItems().add(mi3);

        mi1.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/specialityEditDialog.fxml"));
                AnchorPane parent = fxmlLoader.load();
                SpecialityEditDialogController specialityEditDialogController = fxmlLoader.getController();
                specialityEditDialogController.setSpecialtyTableController(this);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Добавление специальности");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        mi2.setOnAction((ActionEvent event) -> {
            Specialty specialty = specTable.getSelectionModel().getSelectedItem();
            if (specialty != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/specialityEditDialog.fxml"));
                    AnchorPane parent = fxmlLoader.load();
                    SpecialityEditDialogController specialityEditDialogController = fxmlLoader.getController();
                    specialityEditDialogController.setSpecialtyTableController(this);
                    specialityEditDialogController.setSpecialtyEditNow(specialty);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Изменение специальности");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mi3.setOnAction((ActionEvent event) -> {
            Specialty specialty = specTable.getSelectionModel().getSelectedItem();
            if (specialty != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить специальность " + specialty.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление специальности");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    specialty = SpecialtyDAO.delete(specialty);
                    getSpecTable().getItems().remove(specialty);
                }

            }
        });
        specTable.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            Specialty specialty = specTable.getSelectionModel().getSelectedItem();
            if (specialty != null) {
                System.out.println(specialty.getTitle());

            }
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(specTable, t.getScreenX(), t.getScreenY());
            }
        });
    }

    public TableView<Specialty> getSpecTable() {
        return specTable;
    }
}
