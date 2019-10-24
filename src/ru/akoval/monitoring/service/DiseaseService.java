package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import ru.akoval.monitoring.entities.Disease;
import ru.akoval.monitoring.DAO.DiseaseDAO;

import java.util.List;

public class DiseaseService {
    private TableView<Disease> diseaseTable;

    public DiseaseService(TableView<Disease> diseaseTable) {
        this.diseaseTable = diseaseTable;
    }

    public TableView<Disease> getDiseaseData() {
        List<Disease> data = DiseaseDAO.getDiseaseData();
        diseaseTable.setItems(FXCollections.observableArrayList(data));
        return diseaseTable;
    }
}
