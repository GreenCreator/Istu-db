package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import ru.akoval.monitoring.controllers.ClassTableController;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.DAO.ClassDAO;

import java.util.List;

public class ClassService {
    public final int ROWS_PER_PAGE = 200;
    private ClassTableController controller;

    public ClassService(ClassTableController controller) {
        this.controller = controller;
    }


    public Node createPage(int pageIndex) {
        List<Class> data;
        int from = pageIndex * ROWS_PER_PAGE;
        data = ClassDAO.getClassPagedData(from, ROWS_PER_PAGE);
        this.controller.getClassTable().setItems(FXCollections.observableArrayList(data));
        return this.controller.getClassTable();
    }
}
