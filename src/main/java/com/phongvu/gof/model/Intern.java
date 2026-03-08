package com.phongvu.gof.model;

public class Intern extends Candidate{
    private String major;
    private String semester;
    private String university;

    //Parameter Constructor
    public Intern(String id, String firstName, String lastName, int birthDate, String address,
                  String phone, String email, int typeCandidate, String major, String semester, String university) {
        super(id, firstName, lastName, birthDate, address, phone, email, typeCandidate);
        this.major = major;
        this.semester = semester;
        this.university = university;
    }

    //=======Getter & Setter=======
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
