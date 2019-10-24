package ru.akoval.monitoring.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Specialty;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SpecialtyDAO extends BaseDAO {

    public static Specialty getSpecialtyByID(long id) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM specialty WHERE id=" + id);
            DepartmentDAO departmentDAO = new DepartmentDAO();
            return new Specialty(
                    result.getInt("id"),
                    result.getString("title"),
                    departmentDAO.getDepartmentByID(result.getInt("department"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Specialty setSpecialtyData(Specialty specialty) {
        String sql = "INSERT INTO specialty VALUES (NULL, ?,?)";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setString(1, String.valueOf(specialty.getTitle()));
            statement.setInt(2, specialty.getDepartment().getId());
            statement.executeUpdate();
            //Get last inserted ID
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                specialty.setId(Math.toIntExact(generatedKeys.getLong(1)));
                return specialty;
            } else {
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Specialty> getSpecialtyData() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            List<Specialty> specialty = new ArrayList<>();
            DepartmentDAO departmentDAO = new DepartmentDAO();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialty");
            while (resultSet.next()) {
                specialty.add(new Specialty(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        departmentDAO.getDepartmentByID(resultSet.getInt("department"))));
            }
            return specialty;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static ObservableList<Specialty> getSpecialtyRecords() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Specialty> records = FXCollections.observableArrayList();
            DepartmentDAO departmentDAO = new DepartmentDAO();
            ResultSet result = statement.executeQuery("SELECT * FROM specialty");
            while (result.next()) {
                records.add(new Specialty(
                        result.getInt("id"),
                        result.getString("title"),
                        departmentDAO.getDepartmentByID(result.getInt("department")))
                );

            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}