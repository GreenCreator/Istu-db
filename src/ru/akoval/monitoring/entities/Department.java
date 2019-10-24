package ru.akoval.monitoring.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Department extends BaseEntity{
    private StringProperty shortTitle;
    private StringProperty title;


    // Конструктор
    public Department(int id, String short_title, String title) {
        this.id = id;
        this.shortTitle = new SimpleStringProperty(short_title);
        this.title = new SimpleStringProperty(title);
    }

    public String getShortTitle() {
        return shortTitle.get();
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle.set(shortTitle);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty shortTitleDepartment() {
        return shortTitle;
    }

    public StringProperty titleDepartment() {
        return title;
    }

    @Override
    public String toString() {
        return title.getValue();
    }
}