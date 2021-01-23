package com.project.daicuongbachkhoa.model;

public class Teacher {

    private String nameTeacher,idTeacher,codeTeacher,emailTeacher,passTeacher;

    public Teacher() {
    }

    public Teacher(String nameTeacher, String idTeacher, String emailTeacher, String passTeacher, String codeTeacher) {
        this.nameTeacher = nameTeacher;
        this.idTeacher = idTeacher;
        this.emailTeacher = emailTeacher;
        this.passTeacher = passTeacher;
        this.codeTeacher = codeTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }


    public String getEmailTeacher() {
        return emailTeacher;
    }

    public void setEmailTeacher(String emailTeacher) {
        this.emailTeacher = emailTeacher;
    }

    public String getPassTeacher() {
        return passTeacher;
    }

    public void setPassTeacher(String passTeacher) {
        this.passTeacher = passTeacher;
    }

    public String getCodeTeacher() {
        return codeTeacher;
    }

    public void setCodeTeacher(String codeTeacher) {
        this.codeTeacher = codeTeacher;
    }
}
