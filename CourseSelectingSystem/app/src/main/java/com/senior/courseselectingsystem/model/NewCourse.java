package com.senior.courseselectingsystem.model;

public class NewCourse {
    private String name;
    private String uplimit;
    private String description;
    private String teachernum;

    public NewCourse(String name, String uplimit, String description, String teachernum) {
        this.name = name;
        this.uplimit = uplimit;
        this.description = description;
        this.teachernum = teachernum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUplimit() {
        return uplimit;
    }

    public void setUplimit(String uplimit) {
        this.uplimit = uplimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeachernum() {
        return teachernum;
    }

    public void setTeachernum(String teachernum) {
        this.teachernum = teachernum;
    }

    @Override
    public String toString() {
        return "name=" + name + "&uplimit=" + uplimit + "&descrip=" + description + "&teachernum=" + teachernum;
    }
}
