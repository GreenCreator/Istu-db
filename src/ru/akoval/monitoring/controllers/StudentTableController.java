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
import ru.akoval.monitoring.DAO.StudentDAO;
import ru.akoval.monitoring.entities.Student;
import ru.akoval.monitoring.service.StudentService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class StudentTableController {

    @FXML
    Pagination studentTablePagination;
    @FXML
    public TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> surnameColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> middlenameColumn;
    @FXML
    private TableColumn<Student, String> dobColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TableColumn<Student, Integer> groupColumn;

    private StudentService studentService;

    @FXML
    private void initialize() {


        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<>("middlename"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("dob"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));


        studentService = new StudentService(this);
        int pageCount = (StudentDAO.getStudentsTableVolume() / studentService.ROWS_PER_PAGE) + 1;
        studentTablePagination.setPageCount(pageCount);
        studentTablePagination.setPageFactory(studentService::createPage);
        studentTable.resize(studentTable.getWidth(), studentTable.getHeight());

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Добавить");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Изменить");
        cm.getItems().add(mi2);
        MenuItem mi3 = new MenuItem("Удалить");
        cm.getItems().add(mi3);
        //answerWindow.setVisible(true);
        mi1.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/StudentEditDialog.fxml"));
                AnchorPane parent = fxmlLoader.load();
                StudentEditDialogController studentEditDialogController = fxmlLoader.getController();
                studentEditDialogController.setStudentTableController(this);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Добавление студента");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        mi2.setOnAction((ActionEvent event) -> {
            Student student = studentTable.getSelectionModel().getSelectedItem();
            if (student != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/StudentEditDialog.fxml"));
                    AnchorPane parent = fxmlLoader.load();
                    StudentEditDialogController studentEditDialogController = fxmlLoader.getController();
                    studentEditDialogController.setStudentTableController(this);
                    studentEditDialogController.setStudentEditNow(student);
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Изменение студента");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mi3.setOnAction((ActionEvent event) -> {
            Student student = studentTable.getSelectionModel().getSelectedItem();
            if (student != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить студента " + student.getSurname() + " " + student.getName() + " " + student.getMiddlename() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление студента");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    student = StudentDAO.delete(student);
                    getStudentTable().getItems().remove(student);
                }

            }
        });
        studentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Student student = studentTable.getSelectionModel().getSelectedItem();
                if (student != null) {
                    System.out.println(student.getDob());

                }
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(studentTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

    }

    public TableView<Student> getStudentTable() {
        return studentTable;
    }

    public StudentService getStudentService() {
        return studentService;
    }
}
