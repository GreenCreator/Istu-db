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
import ru.akoval.monitoring.entities.Department;
import ru.akoval.monitoring.DAO.DepartmentDAO;
import ru.akoval.monitoring.service.DepartmentService;

import java.io.IOException;

public class DepartmentTableController {

    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableColumn<Department, String> shortTitleDepartmentColumn;
    @FXML
    private TableColumn<Department, String> titleDepartmentColumn;
    private DepartmentService departmentService;

    @FXML
    private void initialize() {

        departmentService = new DepartmentService(departmentTable);
        departmentService.getDepartmentData();
        shortTitleDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().shortTitleDepartment());
        titleDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().titleDepartment());

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Добавить");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Изменить");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Удалить");
        cm.getItems().add(mi3);

        mi1.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/departmentEditDialog.fxml"));
                AnchorPane parent = fxmlLoader.load();
                DepartmentEditDialogController departmentEditDialogController = fxmlLoader.getController();
                departmentEditDialogController.setDepartmentTableController(this);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Добавление факультета");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        mi2.setOnAction((ActionEvent event) -> {
            Department department = departmentTable.getSelectionModel().getSelectedItem();
            if (department != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/departmentEditDialog.fxml"));
                    AnchorPane parent = fxmlLoader.load();
                    DepartmentEditDialogController departmentEditDialogController = fxmlLoader.getController();
                    departmentEditDialogController.setDepartmentTableController(this);
                    departmentEditDialogController.setDepartmentEditNow(department);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Изменение факультета");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mi3.setOnAction((ActionEvent event) -> {
            Department department = departmentTable.getSelectionModel().getSelectedItem();
            if (department != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить факультет: " + department.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление факультета");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    department = DepartmentDAO.delete(department);
                    getDepartmentTable().getItems().remove(department);
                }

            }
        });
        getDepartmentTable().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(departmentTable, t.getScreenX(), t.getScreenY());
                }
            }
        });
    }

    public TableView<Department> getDepartmentTable() {
        return departmentTable;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }
}
