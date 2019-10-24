package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import ru.akoval.monitoring.controllers.SemesterTableController;
import ru.akoval.monitoring.entities.Semester;
import ru.akoval.monitoring.DAO.SemesterDAO;

import java.util.List;

public class SemesterService {

    public final int ROWS_PER_PAGE = 500;

    private SemesterTableController controller;

    public SemesterService(SemesterTableController controller) {
        this.controller = controller;
    }
    //TODO здесь должен быть метод с фильтрацией
    public ObservableList<Semester> getSemesterData(){
       return FXCollections.observableArrayList(SemesterDAO.getSemestrData(0, ROWS_PER_PAGE));
    }

    public Node createPage(int pageIndex) {
        List<Semester> data;
        int from = pageIndex * ROWS_PER_PAGE;
        data = SemesterDAO.getSemestrData(from, ROWS_PER_PAGE);
        this.controller.getSemesterTable().setItems(FXCollections.observableArrayList(data));
        return this.controller.getSemesterTable();
    }
}
