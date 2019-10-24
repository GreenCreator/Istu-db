package ru.akoval.monitoring.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ClassDAO extends BaseDAO {

    public static Class getClassByID(long id) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM class WHERE id=" + id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();

            if (result.next()) return new Class(
                    result.getInt("id"),
                    result.getString("title"),
                    specialtyDAO.getSpecialtyByID(result.getInt("speciality"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Class> getClassRecords() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Class> records = FXCollections.observableArrayList();
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            ResultSet result = statement.executeQuery("SELECT * FROM class");
            while (result.next()) {
                records.add(new Class(
                        result.getInt("id"),
                        result.getString("title"),
                        specialtyDAO.getSpecialtyByID(result.getInt("speciality"))
                ));

            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Class> getClassPagedData(int from, int rowsCnt) {
        try (Connection conn = SqliteConnection.Connector(); Statement statement = Objects.requireNonNull(conn).createStatement()) {
            List<Class> classes = new ArrayList<>();
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM class LIMIT " + from + "," + rowsCnt);
            while (resultSet.next()) {
                classes.add(new Class(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        specialtyDAO.getSpecialtyByID(resultSet.getInt("speciality"))));
            }
            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Integer getClassTableVolume() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("select count(*) from class");
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class setClassData(Class clas) {
        String sql = "INSERT INTO class VALUES (NULL, ?,?)";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setString(1, clas.getTitle());
            statement.setInt(2, clas.getSpeciality().getId());
            statement.executeUpdate();
            //Get last inserted ID
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                clas.setId(Math.toIntExact(generatedKeys.getLong(1)));
                return clas;
            } else {
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}