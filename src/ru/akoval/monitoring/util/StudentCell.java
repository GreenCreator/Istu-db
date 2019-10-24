package ru.akoval.monitoring.util;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import ru.akoval.monitoring.DAO.StudentDAO;
import ru.akoval.monitoring.entities.Semester;
import ru.akoval.monitoring.entities.Student;

public class StudentCell extends ComboBoxTableCell<Semester, Student> {

    private final TableView<Semester> semesterTable;

    public StudentCell(TableView<Semester> semesterTable) {
        this.semesterTable = semesterTable;
    }

    @Override
    public void updateItem(Student item, boolean empty) {
        super.updateItem(item, empty);
            int index = semesterTable.getSelectionModel().getFocusedIndex();
            super.getItems().setAll(StudentDAO.getStudentRecordsByGroupID(semesterTable.getItems().get(index).getGroup().getId()));
    }
}
