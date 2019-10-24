package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import ru.akoval.monitoring.controllers.StudentTableController;
import ru.akoval.monitoring.entities.Student;
import ru.akoval.monitoring.DAO.StudentDAO;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public final int ROWS_PER_PAGE = 500;

    private StudentTableController controller;
    private List<Student> studentList = new ArrayList<>();

    public StudentService(StudentTableController controller) {
        this.controller = controller;
    }

    public Node createPage(int pageIndex) {
        int from = pageIndex * ROWS_PER_PAGE;
        studentList = StudentDAO.getStudentData(from, ROWS_PER_PAGE);
        this.controller.getStudentTable().setItems(FXCollections.observableArrayList(studentList));
        return this.controller.getStudentTable();
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
