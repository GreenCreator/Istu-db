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
import ru.akoval.monitoring.entities.Instructor;
import ru.akoval.monitoring.DAO.InstructorDAO;
import ru.akoval.monitoring.service.InstructorService;

import java.io.IOException;

public class InstructorTableController {

    @FXML
    private TableView<Instructor> instructorTable;
    @FXML
    private TableColumn<Instructor, String> surnameColumn;
    @FXML
    private TableColumn<Instructor, String> nameColumn;
    @FXML
    private TableColumn<Instructor, String> middlenameColumn;

    private InstructorService instructorService;

    @FXML
    private void initialize() {

        instructorService = new InstructorService(instructorTable);
        instructorService.getInstructorData();
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameInstructor());
        middlenameColumn.setCellValueFactory(cellData -> cellData.getValue().middlenameProperty());

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Добавить");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Изменить");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Удалить");
        cm.getItems().add(mi3);

        mi1.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InstructorEditDialog.fxml"));
                AnchorPane parent = fxmlLoader.load();
                InstructorEditDialogController instructorEditDialogController = fxmlLoader.getController();
                instructorEditDialogController.setInstructorTableController(this);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Добавление преподавателя");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        mi2.setOnAction((ActionEvent event) -> {
            Instructor instructor = instructorTable.getSelectionModel().getSelectedItem();
            if (instructor != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/InstructorEditDialog.fxml"));
                    AnchorPane parent = fxmlLoader.load();
                    InstructorEditDialogController instructorEditDialogController = fxmlLoader.getController();
                    instructorEditDialogController.setInstructorTableController(this);
                    instructorEditDialogController.setInstructorEditNow(instructor);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Изменение преподавателя");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mi3.setOnAction((ActionEvent event) -> {
            Instructor instructor = instructorTable.getSelectionModel().getSelectedItem();
            if (instructor != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить преподавателя " + instructor.getSurname() + " " + instructor.getName() + " " + instructor.getMiddlename() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление преподавателя");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    instructor = InstructorDAO.delete(instructor);
                    getInstructorTable().getItems().remove(instructor);
                }

            }
        });
        instructorTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Instructor instructor = instructorTable.getSelectionModel().getSelectedItem();
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(instructorTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

    }

    public TableView<Instructor> getInstructorTable() {
        return instructorTable;
    }

    public InstructorService getInstructorService() {
        return instructorService;
    }
}
