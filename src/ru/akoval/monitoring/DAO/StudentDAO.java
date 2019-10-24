package ru.akoval.monitoring.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Student;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StudentDAO extends BaseDAO{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Student getStudentByID(long id) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT * FROM student WHERE id=" + id);

            if (result.next()) return new Student(result.getInt("id"),
                    result.getString("surname"),
                    result.getString("name"),
                    result.getString("middlename"),
                    result.getString("dob"),
                    result.getInt("gender"),
                    ClassDAO.getClassByID(result.getInt("class")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Student> getStudentRecordsByGroupID(Integer groupID){
        if (groupID == null) throw new NullPointerException();
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Student> records = FXCollections.observableArrayList();
            ResultSet result;
            if (groupID == -1) {
                result = statement.executeQuery("SELECT * FROM student");
            } else {
                result = statement.executeQuery("SELECT * FROM student WHERE student.class="+groupID);
            }

            while (result.next()) {
                records.add(new Student(
                        result.getInt("id"),
                        result.getString("surname"),
                        result.getString("name"),
                        result.getString("middlename"),
                        result.getString("dob"),
                        result.getInt("gender"),
                        ClassDAO.getClassByID(result.getInt("class"))
                ));
            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Student> getStudentRecords() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ObservableList<Student> records = FXCollections.observableArrayList();
            ResultSet result = statement.executeQuery("SELECT * FROM student");

            while (result.next()) {
                records.add(new Student(
                        result.getInt("id"),
                        result.getString("surname"),
                        result.getString("name"),
                        result.getString("middlename"),
                        result.getString("dob"),
                        result.getInt("gender"),
                        ClassDAO.getClassByID(result.getInt("class"))
                ));
            }
            return records;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Student> getStudentData(int from, int rowsCnt) {
        try (
             Connection conn = SqliteConnection.Connector();
             Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            List<Student> students = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student LIMIT " + from + "," + rowsCnt);
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("middlename"),
                        resultSet.getString("dob"),
                        resultSet.getInt("gender"),
                        ClassDAO.getClassByID(resultSet.getInt("class"))));
            }
            return students;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Student setStudentData(Student student){
        String sql = "INSERT INTO student VALUES (NULL, ?,?,?,?,?,?)";
        try (
                Connection conn = SqliteConnection.Connector();
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(sql)
        ) {
            statement.setString(1, student.getSurname());
            statement.setString(2, student.getName());
            statement.setString(3, student.getMiddlename());
            statement.setString(4, student.getDob().format(formatter));
            statement.setInt(5, student.getGender());
            statement.setInt(6, student.getGroup().getId());
            statement.executeUpdate();
            //Get last inserted ID
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
                return student;
            } else {
                throw new SQLException("Creating student failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getStudentsTableVolume() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("SELECT count(*) FROM student");
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}