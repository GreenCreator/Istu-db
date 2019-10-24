package ru.akoval.monitoring.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite::resource:FizraBD.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            // TODO: handle exception
        }
    }


}