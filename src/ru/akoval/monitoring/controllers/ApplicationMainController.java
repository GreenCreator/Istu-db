package ru.akoval.monitoring.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.akoval.monitoring.service.dbModel;

public class ApplicationMainController {

    private dbModel loginModel = new dbModel();

    @FXML
    private Label isConnected;

    @FXML
    private AnchorPane studentTab;
    @FXML
    private AnchorPane departmentTab;
    @FXML
    private AnchorPane specialityTab;
    @FXML
    private AnchorPane instructorTab;

    public ApplicationMainController() {}

    @FXML
    private void initialize() {}
}