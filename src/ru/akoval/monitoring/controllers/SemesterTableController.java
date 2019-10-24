package ru.akoval.monitoring.controllers;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import ru.akoval.monitoring.DAO.*;
import ru.akoval.monitoring.util.DatePickerCell;
import ru.akoval.monitoring.util.PersistedTableColumn;
import ru.akoval.monitoring.entities.*;
import ru.akoval.monitoring.entities.Class;
import ru.akoval.monitoring.service.SemesterService;
import ru.akoval.monitoring.util.StudentCell;

import java.io.IOException;
import java.time.LocalDate;


public class SemesterTableController {


    @FXML
    Pagination semesterTablePagination;
    @FXML
    public TableView<Semester> semesterTable;
    @FXML
    private PersistedTableColumn<Semester, Instructor> instructorColumn;
    @FXML
    private PersistedTableColumn<Semester, Student> studentColumn;
    @FXML
    private PersistedTableColumn<Semester, Class> groupColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> semesterColumn;
    @FXML
    private PersistedTableColumn<Semester, LocalDate> dobColumn;
    @FXML
    private PersistedTableColumn<Semester, Disease> diseaseColumn;
    @FXML
    private PersistedTableColumn<Semester, LocalDate> dateOfSurveyColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> heightColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> weightColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> okrGrCellsColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> waistCircumferenceColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> lifelungCapacityColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> SADColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> DADColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> SHSSatRestColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> SHSSbeforeExerciseColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> SHSSafterExerciseColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> recoveryTimeColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> DINleftColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> DINrightColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> stangeTestColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> gencheTestColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> orthoprobeSHSSlyingColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> orthoprobeSHSSstandingColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> orthoprobeDiffColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> klinoprobaSHSSstandingColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> klinoprobaSHSSlyingColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> klinoprobaDiffColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> shuttleRunColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> running30mColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> running100mColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> running1000mColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> running2000mFColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> running3000mMColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> visOnBentHandsFColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> pullUpMColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> liftingTheTrunk30sColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> liftingTheTrunk1mColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> tiltForwardFromStandingColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> tiltForwardFromLyingColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> leapFromThePlaceColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> push_upColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> KEKColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> KVColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> IMTColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> PEIPRGKColumn;
    @FXML
    private PersistedTableColumn<Semester, Integer> PPIOFRColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> ADColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> UFSColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> VIColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> ZIColumn;
    @FXML
    private PersistedTableColumn<Semester, Double> DVMTColumn;
    private SemesterService semesterService;
    private SemesterTableFilterController filterPageController;


    private void refreshTableSemester(Semester semester, String updatedCol) {
        SemesterDAO.update(semester, updatedCol);
        semesterTable.refresh();
    }

    @FXML
    private void initialize() {
        //Преподаватель
        instructorColumn.setParams("instructor",
                ComboBoxTableCell.forTableColumn(InstructorDAO.getInstructorRecords()),
                Semester::setInstructor, this::refreshTableSemester);
        //Семестер
        semesterColumn.setParams("semester",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setSemester, this::refreshTableSemester);
        //Студент
        studentColumn.setParams("student",
                ComboBoxTableCell.forTableColumn(StudentDAO.getStudentRecordsByGroupID(-1)),
                Semester::setStudent, this::refreshTableSemester);
        studentColumn.setCellFactory(
                param -> new StudentCell(this.semesterTable)
        );
        //Группа
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        ObservableList<Class> groupsOList = ClassDAO.getClassRecords();
        groupColumn.setCellFactory(ComboBoxTableCell.forTableColumn(groupsOList));


        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob_student"));
        //dobColumn.setCellFactory(param -> new DatePickerCell());

        diseaseColumn.setParams("disease",
                ComboBoxTableCell.forTableColumn(DiseaseDAO.getDiseaseRecords()),
                Semester::setDisease, this::refreshTableSemester);

        dateOfSurveyColumn.setParams("dateOfSurvey",
                param -> new DatePickerCell(),
                Semester::setDateOfSurvey, this::refreshTableSemester);

        //Рост студента
        heightColumn.setParams("height",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setHeight, this::refreshTableSemester);

        weightColumn.setParams("weight",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setWeight, this::refreshTableSemester);

        okrGrCellsColumn.setParams("okrGrCells",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setOkrGrCells, this::refreshTableSemester);

        waistCircumferenceColumn.setParams("waistCircumference",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setWaistCircumference, this::refreshTableSemester);

        lifelungCapacityColumn.setParams("lifelungCapacity",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setLifelungCapacity, this::refreshTableSemester);

        SADColumn.setParams("SAD",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setSAD, this::refreshTableSemester);

        DADColumn.setParams("DAD",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setDAD, this::refreshTableSemester);

        SHSSatRestColumn.setParams("SHSSatRest",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setSHSSatRest, this::refreshTableSemester);

        SHSSbeforeExerciseColumn.setParams("SHSSbeforeExercise",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setSHSSbeforeExercise, this::refreshTableSemester);

        SHSSafterExerciseColumn.setParams("SHSSafterExercise",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setSHSSafterExercise, this::refreshTableSemester);

        recoveryTimeColumn.setParams("recoveryTime",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setRecoveryTime, this::refreshTableSemester);

        DINleftColumn.setParams("DINleft",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setDINleft, this::refreshTableSemester);

        DINrightColumn.setParams("DINright",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setDINright, this::refreshTableSemester);
        stangeTestColumn.setParams("stangeTest",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setStangeTest, this::refreshTableSemester);

        gencheTestColumn.setParams("gencheTest",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setGencheTest, this::refreshTableSemester);

        orthoprobeSHSSlyingColumn.setParams("orthoprobeSHSSlying",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setOrthoprobeSHSSlying, this::refreshTableSemester);

        orthoprobeSHSSstandingColumn.setParams("orthoprobeSHSSstanding",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setOrthoprobeSHSSstanding, this::refreshTableSemester);

        orthoprobeDiffColumn.setParams("orthoprobeDiff",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setOrthoprobeDiff, this::refreshTableSemester);

        klinoprobaSHSSstandingColumn.setParams("klinoprobaSHSSstanding",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setKlinoprobaSHSSstanding, this::refreshTableSemester);

        klinoprobaSHSSlyingColumn.setParams("klinoprobaSHSSlying",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setKlinoprobaSHSSlying, this::refreshTableSemester);

        klinoprobaDiffColumn.setParams("klinoprobaDiff",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setKlinoprobaDiff, this::refreshTableSemester);

        shuttleRunColumn.setParams("shuttleRun",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setShuttleRun, this::refreshTableSemester);

        running30mColumn.setParams("running30m",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setRunning30m, this::refreshTableSemester);

        running100mColumn.setParams("running100m",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setRunning100m, this::refreshTableSemester);

        running1000mColumn.setParams("running1000m",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setRunning1000m, this::refreshTableSemester);

        running2000mFColumn.setParams("running2000mF",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setRunning2000mF, this::refreshTableSemester);

        running3000mMColumn.setParams("running3000mM",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setRunning3000mM, this::refreshTableSemester);

        visOnBentHandsFColumn.setParams("visOnBentHandsF",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setVisOnBentHandsF, this::refreshTableSemester);

        pullUpMColumn.setParams("pullUpM",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setPullUpM, this::refreshTableSemester);

        liftingTheTrunk30sColumn.setParams("liftingTheTrunk30s",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setLiftingTheTrunk30s, this::refreshTableSemester);

        liftingTheTrunk1mColumn.setParams("liftingTheTrunk1m",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setLiftingTheTrunk1m, this::refreshTableSemester);

        tiltForwardFromStandingColumn.setParams("tiltForwardFromStanding",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setTiltForwardFromStanding, this::refreshTableSemester);

        tiltForwardFromLyingColumn.setParams("tiltForwardFromLying",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setTiltForwardFromLying, this::refreshTableSemester);

        leapFromThePlaceColumn.setParams("leapFromThePlace",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setLeapFromThePlace, this::refreshTableSemester);

        push_upColumn.setParams("push_up",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setPush_up, this::refreshTableSemester);

        KEKColumn.setParams("KEK",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setKEK, this::refreshTableSemester);

        KVColumn.setParams("KV",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setKV, this::refreshTableSemester);

        IMTColumn.setParams("IMT",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setIMT, this::refreshTableSemester);

        PEIPRGKColumn.setParams("PEIPRGK",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setPEIPRGK, this::refreshTableSemester);

        PPIOFRColumn.setParams("PPIOFR",
                TextFieldTableCell.forTableColumn(new IntegerStringConverter()),
                Semester::setPPIOFR, this::refreshTableSemester);

        ADColumn.setParams("AD",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setAD, this::refreshTableSemester);

        UFSColumn.setParams("UFS",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setUFS, this::refreshTableSemester);

        VIColumn.setParams("VI",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setVI, this::refreshTableSemester);

        ZIColumn.setParams("ZI",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setZI, this::refreshTableSemester);

        DVMTColumn.setParams("DVMT",
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()),
                Semester::setDVMT, this::refreshTableSemester);

        ContextMenu cm = new ContextMenu();
        MenuItem addMenuItem = new MenuItem("Добавить");
        cm.getItems().add(addMenuItem);
        MenuItem delMenuItem = new MenuItem("Удалить");
        cm.getItems().add(delMenuItem);
        MenuItem filterMenuItem = new MenuItem("Фильтр");
        cm.getItems().add(filterMenuItem);
        MenuItem selParMenuItem = new MenuItem("Параметры выборки");
        cm.getItems().add(selParMenuItem);
        MenuItem hypoMenuItem = new MenuItem("Проверка гипотез");
        cm.getItems().add(hypoMenuItem);


        addMenuItem.setOnAction((ActionEvent event) -> {
            Semester semester = new Semester();

            semesterTable.getItems().add(semester);
        });

        delMenuItem.setOnAction((ActionEvent event) ->
        {
            Semester semester = semesterTable.getSelectionModel().getSelectedItem();
            if (semester != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Вы  действительно хотите удалить данные о студенте " + semester.getStudent().getName() + " " + semester.getStudent().getSurname() + " " + semester.getStudent().getMiddlename() + " ?", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Внимание!");
                alert.setHeaderText("Удаление преподавателя");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    semester = SemesterDAO.delete(semester);
                    getSemesterTable().getItems().remove(semester);
                }

            }
        });
        filterMenuItem.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader filterLoader = new FXMLLoader(getClass().getResource("/semesterFilter.fxml"));
                AnchorPane parent = filterLoader.load();
                this.filterPageController = filterLoader.getController();
                filterPageController.setSemesterTableController(semesterTable);
                filterPageController.setSemesterService(semesterService);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Фильтр");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        selParMenuItem.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/semesterParam.fxml"));
                AnchorPane parent = fxmlLoader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Параметры выборки");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        hypoMenuItem.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/semesterHypothesis.fxml"));
                AnchorPane parent = fxmlLoader.load();
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Проверка гипотез");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        semesterTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                Semester semester = semesterTable.getSelectionModel().getSelectedItem();
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(semesterTable, t.getScreenX(), t.getScreenY());
                }
            }
        });
        semesterService = new SemesterService(this);

        int pageCount = (SemesterDAO.getSemesterTableVolume() / semesterService.ROWS_PER_PAGE) + 1;
        semesterTablePagination.setPageCount(pageCount);
        semesterTablePagination.setPageFactory(semesterService::createPage);
        semesterTable.resize(semesterTable.getWidth(), semesterTable.getHeight());


    }

    public TableView<Semester> getSemesterTable() {
        return semesterTable;
    }

    private SemesterService getSemesterService() {
        return semesterService;
    }


}