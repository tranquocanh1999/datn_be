package com.main.Models;

import com.main.Entities.SubjectEntity;

public class ExamForm {
    //region Property
    private String title;

    private String code;
    private ClassModel2 classroom;

    private SubjectEntity subject;
    private Integer time;
    private Integer numberOfExam;

    private Boolean hasQuestionLv1;
    private Integer numberOfQuestionLv1;
    private Boolean hasQuestionLv2;
    private Integer numberOfQuestionLv2;
    private Boolean hasQuestionLv3;
    private Integer numberOfQuestionLv3;
    private Boolean hasQuestionLv4;
    private Integer numberOfQuestionLv4;
    //endregion

    //region Constructor
    //endregion

    //region Getter & Setter


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ClassModel2 getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassModel2 classroom) {
        this.classroom = classroom;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getNumberOfExam() {
        return numberOfExam;
    }

    public void setNumberOfExam(Integer numberOfExam) {
        this.numberOfExam = numberOfExam;
    }

    public Integer getNumberOfQuestionLv1() {
        return numberOfQuestionLv1;
    }

    public void setNumberOfQuestionLv1(Integer numberOfQuestionLv1) {
        this.numberOfQuestionLv1 = numberOfQuestionLv1;
    }

    public Integer getNumberOfQuestionLv2() {
        return numberOfQuestionLv2;
    }

    public void setNumberOfQuestionLv2(Integer numberOfQuestionLv2) {
        this.numberOfQuestionLv2 = numberOfQuestionLv2;
    }

    public Integer getNumberOfQuestionLv3() {
        return numberOfQuestionLv3;
    }

    public void setNumberOfQuestionLv3(Integer numberOfQuestionLv3) {
        this.numberOfQuestionLv3 = numberOfQuestionLv3;
    }

    public Integer getNumberOfQuestionLv4() {
        return numberOfQuestionLv4;
    }

    public void setNumberOfQuestionLv4(Integer numberOfQuestionLv4) {
        this.numberOfQuestionLv4 = numberOfQuestionLv4;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public Boolean getHasQuestionLv1() {
        return hasQuestionLv1;
    }

    public void setHasQuestionLv1(Boolean hasQuestionLv1) {
        this.hasQuestionLv1 = hasQuestionLv1;
    }

    public Boolean getHasQuestionLv2() {
        return hasQuestionLv2;
    }

    public void setHasQuestionLv2(Boolean hasQuestionLv2) {
        this.hasQuestionLv2 = hasQuestionLv2;
    }

    public Boolean getHasQuestionLv3() {
        return hasQuestionLv3;
    }

    public void setHasQuestionLv3(Boolean hasQuestionLv3) {
        this.hasQuestionLv3 = hasQuestionLv3;
    }

    public Boolean getHasQuestionLv4() {
        return hasQuestionLv4;
    }

    public void setHasQuestionLv4(Boolean hasQuestionLv4) {
        this.hasQuestionLv4 = hasQuestionLv4;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //endregion
}
