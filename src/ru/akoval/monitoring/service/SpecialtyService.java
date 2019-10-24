package ru.akoval.monitoring.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.controllers.SpecialityTableController;
import ru.akoval.monitoring.entities.Specialty;
import ru.akoval.monitoring.DAO.SpecialtyDAO;

import java.util.List;

public class SpecialtyService {
    private SpecialityTableController specTable;
    public SpecialtyService(SpecialityTableController specTable){
        this.specTable = specTable;
    }

    public ObservableList<Specialty> getSpecData(){
        return FXCollections.observableArrayList(SpecialtyDAO.getSpecialtyData());
    }

}
