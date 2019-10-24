package ru.akoval.monitoring.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Disease;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiseaseDAO extends BaseDAO {
    public static Disease getDiseaseByID(long id) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM disease WHERE id=" + id);

            if (result.next()) {
                return new Disease(
                        result.getInt("id"),
                        result.getInt("code"),
                        result.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Disease> getDiseaseData() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            List<Disease> diseases = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM disease");
            while (resultSet.next()) {
                diseases.add(new Disease(resultSet.getInt("id"),
                        resultSet.getInt("code"),
                        resultSet.getString("title")));
            }
            return diseases;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Disease setDiseaseData(Disease disease) {
        String sql = "INSERT INTO disease VALUES (NULL, ?,?)";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setInt(1, disease.getCode());
            statement.setString(2, disease.getTitle());
            statement.executeUpdate();
            //Get last inserted ID
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                disease.setId(Math.toIntExact(generatedKeys.getLong(1)));
                return disease;
            } else {
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ObservableList<Disease> getDiseaseRecords() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Disease> records = FXCollections.observableArrayList();
            ResultSet result = statement.executeQuery("SELECT * FROM disease");
            while (result.next()) {
                records.add(new Disease(result.getInt("id"),
                        result.getInt("code"),
                        result.getString("title")));

            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}