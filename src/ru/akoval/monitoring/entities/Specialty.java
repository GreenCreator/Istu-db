package ru.akoval.monitoring.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Specialty extends BaseEntity{
    private StringProperty title;
    private Department department;


    // Конструктор
    public Specialty(int id, String title, Department department) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.department = department;
    }

    public String getTitle() {
        return title.get();
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return title.get();
    }
}