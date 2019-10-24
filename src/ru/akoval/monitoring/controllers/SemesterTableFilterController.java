package ru.akoval.monitoring.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.akoval.monitoring.service.SemesterService;

public class SemesterTableFilterController {
    private SemesterService semesterService;
    @FXML
    private TableView semesterTable;
    @FXML
    private AnchorPane filterPanel;
    @FXML
    private ComboBox filterComboBox;

    @FXML
    private void handleOk(ActionEvent e) {

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

    public void setSemesterTableController(TableView semesterTable) {
        this.semesterTable = semesterTable;
        semesterTable.getColumns().forEach(item -> filterComboBox.getItems().add(item));
        filterComboBox.getSelectionModel().selectFirst();
    }

    public void setSemesterService(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    private double index = 0.0;

    @FXML
    public void addOk(ActionEvent actionEvent) {
        TableColumn col = (TableColumn) filterComboBox.getSelectionModel().getSelectedItem();
        String colName = col.toString();
        int colIndex = filterComboBox.getSelectionModel().getSelectedIndex();
        filterComboBox.getItems().remove(colIndex);
        if (colIndex == 0) {
            filterComboBox.getSelectionModel().selectFirst();
        }
        Label label = new Label(colName + "   от");
        Label label2 = new Label("   до");
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        Button delete = new Button("удалить");
        delete.setOnAction(event -> {
            filterComboBox.getItems().add(col);
        });
        //Label
        AnchorPane.setTopAnchor(label, 45.0 + index);
        AnchorPane.setLeftAnchor(label, 10.0);
        AnchorPane.setTopAnchor(label2, 45.0 + index);
        AnchorPane.setLeftAnchor(label2, 240.0);
        //TextField
        textField.setPrefWidth(40);
        AnchorPane.setTopAnchor(textField, 45.0 + index);
        AnchorPane.setLeftAnchor(textField, 200.0);
        textField2.setPrefWidth(40);
        AnchorPane.setTopAnchor(textField2, 45.0 + index);
        AnchorPane.setLeftAnchor(textField2, 270.0);
        //Button
        AnchorPane.setTopAnchor(delete, 45.0 + index);
        AnchorPane.setLeftAnchor(delete, 350.0);

        filterPanel.getChildren().add(label);
        filterPanel.getChildren().add(label2);
        filterPanel.getChildren().add(textField);
        filterPanel.getChildren().add(textField2);
        filterPanel.getChildren().add(delete);

        index += 30.0;
    }
}
