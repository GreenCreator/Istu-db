package ru.akoval.monitoring.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student extends BaseEntity{
    private Class group;
    private StringProperty surname;
    private StringProperty name;
    private StringProperty middlename;
    private LocalDate dob;
    private int gender;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Конструктор
    public Student(int id, String surname, String name, String middlename, String dob, int gender, Class group) {
        this.id = id;
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.middlename = new SimpleStringProperty(middlename);
        this.dob = LocalDate.parse(dob, formatter);
        this.gender = gender;
        this.group = group;

    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty name() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setGroup(Class group) {
        this.group = group;
    }

    public Class getGroup() {
        return group;
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

    @Override
    public String toString() {
        String fio = surname.get() +" "+name.get()+" "+middlename.get();
        return fio;
    }

}