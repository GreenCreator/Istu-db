package ru.akoval.monitoring.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Class extends BaseEntity {

    private StringProperty title;
    private Specialty speciality;

    // Конструктор
    public Class(int id, String title, Specialty speciality) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.speciality = speciality;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public Specialty getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Specialty specialty) {
        this.speciality = specialty;
    }

    @Override
    public String toString() {
        return title.getValue();
    }
}