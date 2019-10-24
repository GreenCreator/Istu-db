package ru.akoval.monitoring.DAO;

import ru.akoval.monitoring.entities.BaseEntity;
import ru.akoval.monitoring.entities.Student;
import ru.akoval.monitoring.util.SqliteConnection;
import ru.akoval.monitoring.entities.Semester;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SemesterDAO extends BaseDAO{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static List<Semester> getSemestrData(int from, int rowsCnt) {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            List<Semester> semesters = new ArrayList<>();
            StudentDAO studentDAO = new StudentDAO();
            DiseaseDAO diseaseDAO = new DiseaseDAO();
            InstructorDAO instructorDAO = new InstructorDAO();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM semester limit " + from + "," + rowsCnt);
            while (resultSet.next()) {
                Semester semester = new Semester();
                semester.setId(resultSet.getInt("id"));
                semester.setInstructor(instructorDAO.getInstructorByID(resultSet.getInt("instructor")));
                semester.setSemester(resultSet.getInt("semester"));
                Student student = studentDAO.getStudentByID(resultSet.getInt("student"));
                semester.setStudent(student);
                semester.setGroup(student.getGroup());
                semester.setDisease(diseaseDAO.getDiseaseByID(resultSet.getInt("disease")));
                String dateOfSurvey = resultSet.getString("dateOfSurvey");
                if (dateOfSurvey != null){
                    semester.setDateOfSurvey(LocalDate.parse(dateOfSurvey, Semester.formatter));
                }
                semester.setHeight(resultSet.getInt("height"));
                semester.setWeight(resultSet.getInt("weight"));
                semester.setOkrGrCells(resultSet.getInt("okrGrCells"));
                semester.setWaistCircumference(resultSet.getInt("waistCircumference"));
                semester.setLifelungCapacity(resultSet.getInt("lifelungCapacity"));
                semester.setSAD(resultSet.getInt("SAD"));
                semester.setDAD(resultSet.getInt("DAD"));
                semester.setSHSSatRest(resultSet.getInt("SHSSatRest"));
                semester.setSHSSbeforeExercise(resultSet.getInt("SHSSbeforeExercise"));
                semester.setSHSSafterExercise(resultSet.getInt("SHSSafterExercise"));
                semester.setRecoveryTime(resultSet.getInt("recoveryTime"));
                semester.setDINleft(resultSet.getInt("DINleft"));
                semester.setDINright(resultSet.getInt("DINright"));
                semester.setStangeTest(resultSet.getDouble("stangeTest"));
                semester.setGencheTest(resultSet.getDouble("gencheTest"));
                semester.setOrthoprobeSHSSlying(resultSet.getInt("orthoprobeSHSSlying"));
                semester.setOrthoprobeSHSSstanding(resultSet.getInt("orthoprobeSHSSstanding"));
                semester.setOrthoprobeDiff(resultSet.getInt("orthoprobeDiff"));
                semester.setKlinoprobaSHSSstanding(resultSet.getInt("klinoprobaSHSSstanding"));
                semester.setKlinoprobaSHSSlying(resultSet.getInt("klinoprobaSHSSlying"));
                semester.setKlinoprobaDiff(resultSet.getInt("klinoprobaDiff"));
                semester.setShuttleRun(resultSet.getInt("shuttleRun"));
                semester.setRunning30m(resultSet.getDouble("running30m"));
                semester.setRunning100m(resultSet.getDouble("running100m"));
                semester.setRunning1000m(resultSet.getDouble("running1000m"));
                semester.setRunning2000mF(resultSet.getDouble("running2000mF"));
                semester.setRunning3000mM(resultSet.getDouble("running3000mM"));
                semester.setVisOnBentHandsF(resultSet.getDouble("visOnBentHandsF"));
                semester.setPullUpM(resultSet.getInt("pullUpM"));
                semester.setLiftingTheTrunk30s(resultSet.getInt("liftingTheTrunk30s"));
                semester.setLiftingTheTrunk1m(resultSet.getInt("liftingTheTrunk1m"));
                semester.setTiltForwardFromStanding(resultSet.getInt("tiltForwardFromStanding"));
                semester.setTiltForwardFromLying(resultSet.getInt("tiltForwardFromLying"));
                semester.setLeapFromThePlace(resultSet.getInt("leapFromThePlace"));
                semester.setPush_up(resultSet.getInt("push-up"));
                semester.setKEK(resultSet.getInt("KEK"));
                semester.setKV(resultSet.getDouble("KV"));
                semester.setIMT(resultSet.getDouble("IMT"));
                semester.setPEIPRGK(resultSet.getDouble("PEIPRGK"));
                semester.setAD(resultSet.getDouble("AD"));
                semester.setUFS(resultSet.getDouble("UFS"));
                semester.setVI(resultSet.getDouble("VI"));
                semester.setZI(resultSet.getDouble("ZI"));
                semester.setDVMT(resultSet.getDouble("DVMT"));
                semesters.add(semester);
            }
            return semesters;
        } catch (SQLException e) {
            e.printStackTrace();

            return Collections.emptyList();
        }
    }


    public static void update(Semester semester, String updatedCol) {
        updatedCol = updatedCol.replace("Column", "");
        String query = null;
        Connection conn = SqliteConnection.Connector();
        if (updatedCol.equals("Student") && semester.getId() == null){
            query = "INSERT INTO semester (student) VALUES (?);";
            try {
                PreparedStatement statement = Objects.requireNonNull(conn).prepareStatement(query);
                statement.setInt(1, semester.getStudent().getId());
                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    semester.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        String methodName = "get" + updatedCol.substring(0, 1).toUpperCase() + updatedCol.substring(1);
        try {
            if (semester.getClass().getMethod(updatedCol+"Property").getReturnType().getSimpleName().equals("ObjectProperty")){
                query = "UPDATE semester SET " +
                        updatedCol + "=" + ((BaseEntity)semester.getClass().getMethod(methodName).invoke(semester)).getId() +
                        " WHERE id = "+semester.getId()+";";
            } else {
                query = "UPDATE semester SET " +
                        updatedCol + "=" + semester.getClass().getMethod(methodName).invoke(semester) +
                        " WHERE id = "+semester.getId()+";";
            }
            Objects.requireNonNull(conn).prepareStatement(query).executeUpdate();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getSemesterTableVolume() {
        try (
                Connection conn = SqliteConnection.Connector();
                Statement statement = Objects.requireNonNull(conn).createStatement()
        ) {
            ResultSet result = statement.executeQuery("select count(*) from semester");
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}