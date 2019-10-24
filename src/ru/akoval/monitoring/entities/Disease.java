package ru.akoval.monitoring.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Disease extends BaseEntity{
    private IntegerProperty code;
    private StringProperty title;



    // Конструктор
    public Disease(int id, int code, String title) {
        this.id = id;
        this.code = new SimpleIntegerProperty(code);
        this.title = new SimpleStringProperty(title);
    }

    public int getCode() {return code.get();}

    public void setCode(int code) {
        this.code.set(code);
    }

    public IntegerProperty codeDisease() {
        return code;
    }

    public String getTitle() {return title.get();}

    public void setTitle(String name) {
        this.title.set(name);
    }

    public StringProperty titleDisease() {
        return title;
    }

    public String toString() {
        return title.get();
    }
}