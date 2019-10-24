package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import ru.akoval.monitoring.entities.Instructor;
import ru.akoval.monitoring.DAO.InstructorDAO;

import java.util.List;

public class InstructorService {
    private TableView<Instructor> instructorTable;

    public InstructorService(TableView<Instructor> instructorTable) {
        this.instructorTable = instructorTable;
    }

    public TableView<Instructor> getInstructorData() {
        List<Instructor> data = InstructorDAO.getInstructorData();
        instructorTable.setItems(FXCollections.observableArrayList(data));
        return instructorTable;
    }
}
