package ru.akoval.monitoring.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Instructor extends BaseEntity{
    private StringProperty surname;
    private StringProperty name;
    private StringProperty middlename;


    // Конструктор
    public Instructor(int id, String surname, String name, String middlename) {
        this.id = id;
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.middlename = new SimpleStringProperty(middlename);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameInstructor() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getMiddlename() {
        return middlename.get();
    }

    public StringProperty middlenameProperty() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename.set(middlename);
    }

    public String toString() {
        String fio = surname.get() +" "+name.get()+" "+middlename.get();
        return fio;
    }
}