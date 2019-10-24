package ru.akoval.monitoring.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DepartmentDAO extends BaseDAO {

    public static Department getDepartmentByID(long id) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM department WHERE id=" + id);
            if (result.next()) return new Department(
                    result.getInt("id"),
                    result.getString("short_title"),
                    result.getString("title")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ObservableList<Department> getDepartmentRecords() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Department> records = FXCollections.observableArrayList();
            ResultSet result = statement.executeQuery("SELECT * FROM department");
            while (result.next()) {
                records.add(new Department(
                        result.getInt("id"),
                        result.getString("short_title"),
                        result.getString("title"))
                );

            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Department> getDepartmentData() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            List<Department> departments = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM department");
            while (resultSet.next()) {
                departments.add(new Department(resultSet.getInt("id"),
                        resultSet.getString("short_title"),
                        resultSet.getString("title")));
            }
            return departments;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Department setDepartmentData(Department department) {
        String sql = "INSERT INTO department VALUES (NULL, ?,?)";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setString(1, department.getShortTitle());
            statement.setString(2, department.getTitle());
            statement.executeUpdate();
            //Get last inserted ID
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                department.setId(Math.toIntExact(generatedKeys.getLong(1)));
                return department;
            } else {
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}