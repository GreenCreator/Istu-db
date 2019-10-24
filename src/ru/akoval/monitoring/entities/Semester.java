package ru.akoval.monitoring.entities;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Semester extends BaseEntity{

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ObjectProperty<Instructor> instructor = new SimpleObjectProperty<>();
    private IntegerProperty semester = new SimpleIntegerProperty();
    private ObjectProperty<Student> student  = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dob_student  = new SimpleObjectProperty<>();
    private ObjectProperty<Class> group = new SimpleObjectProperty<>();
    private ObjectProperty<Disease> disease = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dateOfSurvey = new SimpleObjectProperty<>();
    private IntegerProperty height = new SimpleIntegerProperty();
    private IntegerProperty weight = new SimpleIntegerProperty();
    private IntegerProperty okrGrCells = new SimpleIntegerProperty();
    private IntegerProperty waistCircumference = new SimpleIntegerProperty();
    private IntegerProperty lifelungCapacity = new SimpleIntegerProperty();
    private IntegerProperty SAD = new SimpleIntegerProperty();
    private IntegerProperty DAD = new SimpleIntegerProperty();
    private IntegerProperty SHSSatRest = new SimpleIntegerProperty();
    private IntegerProperty SHSSbeforeExercise = new SimpleIntegerProperty();
    private IntegerProperty SHSSafterExercise = new SimpleIntegerProperty();
    private IntegerProperty recoveryTime = new SimpleIntegerProperty();
    private IntegerProperty DINleft = new SimpleIntegerProperty();
    private IntegerProperty DINright = new SimpleIntegerProperty();
    private DoubleProperty stangeTest = new SimpleDoubleProperty();
    private DoubleProperty gencheTest = new SimpleDoubleProperty();
    private IntegerProperty orthoprobeSHSSlying = new SimpleIntegerProperty();
    private IntegerProperty orthoprobeSHSSstanding = new SimpleIntegerProperty();
    private IntegerProperty orthoprobeDiff = new SimpleIntegerProperty();
    private IntegerProperty klinoprobaSHSSstanding = new SimpleIntegerProperty();
    private IntegerProperty klinoprobaSHSSlying = new SimpleIntegerProperty();
    private IntegerProperty klinoprobaDiff = new SimpleIntegerProperty();
    private IntegerProperty shuttleRun = new SimpleIntegerProperty();
    private DoubleProperty running30m = new SimpleDoubleProperty();
    private DoubleProperty running100m = new SimpleDoubleProperty();
    private DoubleProperty running1000m = new SimpleDoubleProperty();
    private DoubleProperty running2000mF = new SimpleDoubleProperty();
    private DoubleProperty running3000mM = new SimpleDoubleProperty();
    private DoubleProperty visOnBentHandsF = new SimpleDoubleProperty();
    private IntegerProperty pullUpM = new SimpleIntegerProperty();
    private IntegerProperty liftingTheTrunk30s = new SimpleIntegerProperty();
    private IntegerProperty liftingTheTrunk1m = new SimpleIntegerProperty();
    private IntegerProperty tiltForwardFromStanding = new SimpleIntegerProperty();
    private IntegerProperty tiltForwardFromLying = new SimpleIntegerProperty();
    private IntegerProperty leapFromThePlace = new SimpleIntegerProperty();
    private IntegerProperty push_up = new SimpleIntegerProperty();
    private IntegerProperty KEK = new SimpleIntegerProperty();
    private DoubleProperty KV = new SimpleDoubleProperty();
    private DoubleProperty IMT = new SimpleDoubleProperty();
    private DoubleProperty PEIPRGK = new SimpleDoubleProperty();
    private IntegerProperty PPIOFR = new SimpleIntegerProperty();
    private DoubleProperty AD = new SimpleDoubleProperty();
    private DoubleProperty UFS = new SimpleDoubleProperty();
    private DoubleProperty VI = new SimpleDoubleProperty();
    private DoubleProperty ZI = new SimpleDoubleProperty();
    private DoubleProperty DVMT = new SimpleDoubleProperty();


    public int getSemester() {
        return semester.get();
    }

    public IntegerProperty semesterProperty() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester.set(semester);
    }


    public LocalDate getDob_student() {
        return dob_student.get();
    }

    public final ObjectProperty<LocalDate> dobStudentProperty(){
        return dob_student;
    }

    public void setDob_student(LocalDate dob_student) {
        this.dob_student.set(dob_student);
    }

    public Class getGroup() {
        return group.get();
    }

    public ObjectProperty groupProperty() {
        return group;
    }

    public void setGroup(Class group) { this.group.set(group); }

    public LocalDate getDateOfSurvey() {
        return dateOfSurvey.get();
    }

    public final ObjectProperty<LocalDate> dateOfSurveyProperty(){
        return dateOfSurvey;
    }

    public void setDateOfSurvey(LocalDate dateOfSurvey) {
        this.dateOfSurvey.set(dateOfSurvey);
    }

    public int getHeight() {
        return height.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public int getOkrGrCells() {
        return okrGrCells.get();
    }

    public IntegerProperty okrGrCellsProperty() {
        return okrGrCells;
    }

    public void setOkrGrCells(int okrGrCells) {
        this.okrGrCells.set(okrGrCells);
    }

    public int getWaistCircumference() {
        return waistCircumference.get();
    }

    public IntegerProperty waistCircumferenceProperty() {
        return waistCircumference;
    }

    public void setWaistCircumference(int waistCircumference) {
        this.waistCircumference.set(waistCircumference);
    }

    public int getLifelungCapacity() {
        return lifelungCapacity.get();
    }

    public IntegerProperty lifelungCapacityProperty() {
        return lifelungCapacity;
    }

    public void setLifelungCapacity(int lifelungCapacity) {
        this.lifelungCapacity.set(lifelungCapacity);
    }

    public int getSAD() {
        return SAD.get();
    }

    public IntegerProperty SADProperty() {
        return SAD;
    }

    public void setSAD(int SAD) {
        this.SAD.set(SAD);
    }

    public int getDAD() {
        return DAD.get();
    }

    public IntegerProperty DADProperty() {
        return DAD;
    }

    public void setDAD(int DAD) {
        this.DAD.set(DAD);
    }

    public int getSHSSatRest() {
        return SHSSatRest.get();
    }

    public IntegerProperty SHSSatRestProperty() {
        return SHSSatRest;
    }

    public void setSHSSatRest(int SHSSatRest) {
        this.SHSSatRest.set(SHSSatRest);
    }

    public int getSHSSbeforeExercise() {
        return SHSSbeforeExercise.get();
    }

    public IntegerProperty SHSSbeforeExerciseProperty() {
        return SHSSbeforeExercise;
    }

    public void setSHSSbeforeExercise(int SHSSbeforeExercise) {
        this.SHSSbeforeExercise.set(SHSSbeforeExercise);
    }

    public int getSHSSafterExercise() {
        return SHSSafterExercise.get();
    }

    public IntegerProperty SHSSafterExerciseProperty() {
        return SHSSafterExercise;
    }

    public void setSHSSafterExercise(int SHSSafterExercise) {
        this.SHSSafterExercise.set(SHSSafterExercise);
    }

    public int getRecoveryTime() {
        return recoveryTime.get();
    }

    public IntegerProperty recoveryTimeProperty() {
        return recoveryTime;
    }

    public void setRecoveryTime(int recoveryTime) {
        this.recoveryTime.set(recoveryTime);
    }

    public int getDINleft() {
        return DINleft.get();
    }

    public IntegerProperty DINleftProperty() {
        return DINleft;
    }

    public void setDINleft(int DINleft) {
        this.DINleft.set(DINleft);
    }

    public int getDINright() {
        return DINright.get();
    }

    public IntegerProperty DINrightProperty() {
        return DINright;
    }

    public void setDINright(int DINright) {
        this.DINright.set(DINright);
    }

    public double getStangeTest() {
        return stangeTest.get();
    }

    public DoubleProperty stangeTestProperty() {
        return stangeTest;
    }

    public void setStangeTest(double stangeTest) {
        this.stangeTest.set(stangeTest);
    }

    public double getGencheTest() {
        return gencheTest.get();
    }

    public DoubleProperty gencheTestProperty() {
        return gencheTest;
    }

    public void setGencheTest(double gencheTest) {
        this.gencheTest.set(gencheTest);
    }

    public int getOrthoprobeSHSSlying() {
        return orthoprobeSHSSlying.get();
    }

    public IntegerProperty orthoprobeSHSSlyingProperty() {
        return orthoprobeSHSSlying;
    }

    public void setOrthoprobeSHSSlying(int orthoprobeSHSSlying) {
        this.orthoprobeSHSSlying.set(orthoprobeSHSSlying);
    }

    public int getOrthoprobeSHSSstanding() {
        return orthoprobeSHSSstanding.get();
    }

    public IntegerProperty orthoprobeSHSSstandingProperty() {
        return orthoprobeSHSSstanding;
    }

    public void setOrthoprobeSHSSstanding(int orthoprobeSHSSstanding) {
        this.orthoprobeSHSSstanding.set(orthoprobeSHSSstanding);
    }

    public int getOrthoprobeDiff() {
        return orthoprobeDiff.get();
    }

    public IntegerProperty orthoprobeDiffProperty() {
        return orthoprobeDiff;
    }

    public void setOrthoprobeDiff(int orthoprobeDiff) {
        this.orthoprobeDiff.set(orthoprobeDiff);
    }

    public int getKlinoprobaSHSSstanding() {
        return klinoprobaSHSSstanding.get();
    }

    public IntegerProperty klinoprobaSHSSstandingProperty() {
        return klinoprobaSHSSstanding;
    }

    public void setKlinoprobaSHSSstanding(int klinoprobaSHSSstanding) {
        this.klinoprobaSHSSstanding.set(klinoprobaSHSSstanding);
    }

    public int getKlinoprobaSHSSlying() {
        return klinoprobaSHSSlying.get();
    }

    public IntegerProperty klinoprobaSHSSlyingProperty() {
        return klinoprobaSHSSlying;
    }

    public void setKlinoprobaSHSSlying(int klinoprobaSHSSlying) {
        this.klinoprobaSHSSlying.set(klinoprobaSHSSlying);
    }

    public int getKlinoprobaDiff() {
        return klinoprobaDiff.get();
    }

    public IntegerProperty klinoprobaDiffProperty() {
        return klinoprobaDiff;
    }

    public void setKlinoprobaDiff(int klinoprobaDiff) {
        this.klinoprobaDiff.set(klinoprobaDiff);
    }

    public int getShuttleRun() {
        return shuttleRun.get();
    }

    public IntegerProperty shuttleRunProperty() {
        return shuttleRun;
    }

    public void setShuttleRun(int shuttleRun) {
        this.shuttleRun.set(shuttleRun);
    }

    public double getRunning30m() {
        return running30m.get();
    }

    public DoubleProperty running30mProperty() {
        return running30m;
    }

    public void setRunning30m(double running30m) {
        this.running30m.set(running30m);
    }

    public double getRunning100m() {
        return running100m.get();
    }

    public DoubleProperty running100mProperty() {
        return running100m;
    }

    public void setRunning100m(double running100m) {
        this.running100m.set(running100m);
    }

    public double getRunning1000m() {
        return running1000m.get();
    }

    public DoubleProperty running1000mProperty() {
        return running1000m;
    }

    public void setRunning1000m(double running1000m) {
        this.running1000m.set(running1000m);
    }

    public double getRunning2000mF() {
        return running2000mF.get();
    }

    public DoubleProperty running2000mFProperty() {
        return running2000mF;
    }

    public void setRunning2000mF(double running2000mF) {
        this.running2000mF.set(running2000mF);
    }

    public double getRunning3000mM() {
        return running3000mM.get();
    }

    public DoubleProperty running3000mMProperty() {
        return running3000mM;
    }

    public void setRunning3000mM(double running3000mM) {
        this.running3000mM.set(running3000mM);
    }

    public double getVisOnBentHandsF() {
        return visOnBentHandsF.get();
    }

    public DoubleProperty visOnBentHandsFProperty() {
        return visOnBentHandsF;
    }

    public void setVisOnBentHandsF(double visOnBentHandsF) {
        this.visOnBentHandsF.set(visOnBentHandsF);
    }

    public int getPullUpM() {
        return pullUpM.get();
    }

    public IntegerProperty pullUpMProperty() {
        return pullUpM;
    }

    public void setPullUpM(int pullUpM) {
        this.pullUpM.set(pullUpM);
    }

    public int getLiftingTheTrunk30s() {
        return liftingTheTrunk30s.get();
    }

    public IntegerProperty liftingTheTrunk30sProperty() {
        return liftingTheTrunk30s;
    }

    public void setLiftingTheTrunk30s(int liftingTheTrunk30s) {
        this.liftingTheTrunk30s.set(liftingTheTrunk30s);
    }

    public int getLiftingTheTrunk1m() {
        return liftingTheTrunk1m.get();
    }

    public IntegerProperty liftingTheTrunk1mProperty() {
        return liftingTheTrunk1m;
    }

    public void setLiftingTheTrunk1m(int liftingTheTrunk1m) {
        this.liftingTheTrunk1m.set(liftingTheTrunk1m);
    }

    public int getTiltForwardFromStanding() {
        return tiltForwardFromStanding.get();
    }

    public IntegerProperty tiltForwardFromStandingProperty() {
        return tiltForwardFromStanding;
    }

    public void setTiltForwardFromStanding(int tiltForwardFromStanding) {
        this.tiltForwardFromStanding.set(tiltForwardFromStanding);
    }

    public int getTiltForwardFromLying() {
        return tiltForwardFromLying.get();
    }

    public IntegerProperty tiltForwardFromLyingProperty() {
        return tiltForwardFromLying;
    }

    public void setTiltForwardFromLying(int tiltForwardFromLying) {
        this.tiltForwardFromLying.set(tiltForwardFromLying);
    }

    public int getLeapFromThePlace() {
        return leapFromThePlace.get();
    }

    public IntegerProperty leapFromThePlaceProperty() {
        return leapFromThePlace;
    }

    public void setLeapFromThePlace(int leapFromThePlace) {
        this.leapFromThePlace.set(leapFromThePlace);
    }

    public int getPush_up() {
        return push_up.get();
    }

    public IntegerProperty push_upProperty() {
        return push_up;
    }

    public void setPush_up(int push_up) {
        this.push_up.set(push_up);
    }

    public int getKEK() {
        return KEK.get();
    }

    public IntegerProperty KEKProperty() {
        return KEK;
    }

    public void setKEK(int KEK) {
        this.KEK.set(KEK);
    }

    public double getKV() {
        return KV.get();
    }

    public DoubleProperty KVProperty() {
        return KV;
    }

    public void setKV(double KV) {
        this.KV.set(KV);
    }

    public double getIMT() {
        return IMT.get();
    }

    public DoubleProperty IMTProperty() {
        return IMT;
    }

    public void setIMT(double IMT) {
        this.IMT.set(IMT);
    }

    public double getPEIPRGK() {
        return PEIPRGK.get();
    }

    public DoubleProperty PEIPRGKProperty() {
        return PEIPRGK;
    }

    public void setPEIPRGK(double PEIPRGK) {
        this.PEIPRGK.set(PEIPRGK);
    }

    public int getPPIOFR() {
        return PPIOFR.get();
    }

    public IntegerProperty PPIOFRProperty() {
        return PPIOFR;
    }

    public void setPPIOFR(int PPIOFR) {
        this.PPIOFR.set(PPIOFR);
    }

    public double getAD() {
        return AD.get();
    }

    public DoubleProperty ADProperty() {
        return AD;
    }

    public void setAD(double AD) {
        this.AD.set(AD);
    }

    public double getUFS() {
        return UFS.get();
    }

    public DoubleProperty UFSProperty() {
        return UFS;
    }

    public void setUFS(double UFS) {
        this.UFS.set(UFS);
    }

    public double getVI() {
        return VI.get();
    }

    public DoubleProperty VIProperty() {
        return VI;
    }

    public void setVI(double VI) {
        this.VI.set(VI);
    }

    public double getZI() {
        return ZI.get();
    }

    public DoubleProperty ZIProperty() {
        return ZI;
    }

    public void setZI(double ZI) {
        this.ZI.set(ZI);
    }

    public double getDVMT() {
        return DVMT.get();
    }

    public DoubleProperty DVMTProperty() {
        return DVMT;
    }

    public void setDVMT(double DVMT) {
        this.DVMT.set(DVMT);
    }

    public Instructor getInstructor() {
        return instructor.get();
    }

    public final ObjectProperty<Instructor> instructorProperty(){
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        if (instructor == null) {
            return;
        }
        this.instructor.set(instructor);
    }

    public Student getStudent() {
        return student.get();
    }

    public final ObjectProperty<Student> studentProperty(){
        return student;
    }

    public void setStudent(Student student) {
        this.student.set(student);
        this.dob_student.set(student.getDob());
        this.group.set(student.getGroup());
    }

    public Disease getDisease() {
        System.out.println(disease.get());
        return disease.get();
    }

    public final ObjectProperty<Disease> diseaseProperty(){
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease.set(disease);
    }
}