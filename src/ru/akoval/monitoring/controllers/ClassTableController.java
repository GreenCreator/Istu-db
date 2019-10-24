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
import ru.akoval.monitoring.DAO.ClassDAO;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.service.ClassService;

import java.io.IOException;

public class ClassTableController {

    @FXML
    private TableView<Class> classTable;
    @FXML
    private TableColumn<Class, String> titleColumn;
    @FXML
    private TableColumn<Class, String> departmentColumn;
    @FXML
    private Pagination classTablePagination;
    private ClassService classService;

    @FXML
    private void initialize() {


        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));

        classService = new ClassService(this);
        int pageCount = (ClassDAO.getClassTableVolume() / classService.ROWS_PER_PAGE) + 1;
        classTablePagination.setPageCount(pageCount);
        classTablePagination.setPageFactory(classService::createPage);
        classTable.resize(classTable.getWidth(), classTable.getHeight());

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Добавить");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Изменить");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Удалить");
        cm.getItems().add(mi3);

        mi1.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/classEditDialog.fxml"));
                AnchorPane parent = fxmlLoader.load();
                ClassEditDialogController classEditDialogController = fxmlLoader.getController();
                classEditDialogController.setClassTableController(this);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Добавление группы");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        mi2.setOnAction((ActionEvent event) -> {
            Class aClass = classTable.getSelectionModel().getSelectedItem();
            if (aClass != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/classEditDialog.fxml"));
                    AnchorPane parent = fxmlLoader.load();
                    ClassEditDialogController classEditDialogController = fxmlLoader.getController();
                    classEditDialogController.setClassTableController(this);
                    classEditDialogController.setClassEditNow(aClass);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Изменение информации о группе");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mi3.setOnAction((ActionEvent event) -> {
            Class group = classTable.getSelectionModel().getSelectedItem();
            if (group != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить группу: " + group.getTitle() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление группы");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                    ClassDAO classDAO = new ClassDAO();
                    classDAO.delete(group);
                    group = ClassDAO.delete(group);
                    getClassTable().getItems().remove(group);
                }

            }
        });
        getClassTable().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(getClassTable(), t.getScreenX(), t.getScreenY());
                }
            }
        });
    }

    public TableView<Class> getClassTable() {
        return classTable;
    }

    public ClassService getClassService() {
        return classService;
    }
}
