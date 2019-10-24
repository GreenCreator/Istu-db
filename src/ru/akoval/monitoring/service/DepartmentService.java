package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import ru.akoval.monitoring.entities.Department;
import ru.akoval.monitoring.DAO.DepartmentDAO;

import java.util.List;

public class DepartmentService {
    private TableView<Department> departmentTable;

    public DepartmentService(TableView<Department> departmentTable) {
        this.departmentTable = departmentTable;
    }

    public TableView<Department> getDepartmentData() {
        List<Department> data = DepartmentDAO.getDepartmentData();
        departmentTable.setItems(FXCollections.observableArrayList(data));
        return departmentTable;
    }
}
