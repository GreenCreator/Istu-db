package ru.akoval.monitoring.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Instructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class InstructorDAO extends BaseDAO {

    public InstructorDAO() {
    }

    public static Instructor getInstructorByID(long id) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM instructor WHERE id=" + id);

            if (result.next()) return new Instructor(result.getInt("id"),
                    result.getString("surname"),
                    result.getString("name"),
                    result.getString("middlename"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Instructor> getInstructorRecords() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Instructor> records = FXCollections.observableArrayList();
            ResultSet result = statement.executeQuery("SELECT * FROM instructor");

            while (result.next()) {
                records.add(new Instructor(
                        result.getInt("id"),
                        result.getString("surname"),
                        result.getString("name"),
                        result.getString("middlename")));

            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Instructor> getInstructorData() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            List<Instructor> instructor = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM instructor");
            while (resultSet.next()) {
                instructor.add(new Instructor(resultSet.getInt("id"),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("middlename")));
            }
            return instructor;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Instructor setInstructorData(Instructor instructor) {
        String sql = "INSERT INTO instructor VALUES (NULL, ?,?,?)";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setString(1, instructor.getSurname());
            statement.setString(2, instructor.getName());
            statement.setString(3, instructor.getMiddlename());
            statement.executeUpdate();
            //Get last inserted ID
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                instructor.setId(Math.toIntExact(generatedKeys.getLong(1)));
                return instructor;
            } else {
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}