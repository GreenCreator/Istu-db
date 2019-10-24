package ru.akoval.monitoring.service;

import ru.akoval.monitoring.util.SqliteConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class dbModel {
    private Connection conection;

    public dbModel() {
        conection = SqliteConnection.Connector();
        if (conection == null) {

            System.out.println("connection not successful");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !conection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}