package org.jotad.app.confirmation.models;

public class Family {
    private Integer id;
    private String fatherName;
    private String fatherPhone;
    private String motherName;
    private String motherPhone;

    public Family() {
    }

    public Family(Integer id, String fatherName, String fatherPhone, String motherName, String motherPhone) {
        this.id = id;
        this.fatherName = fatherName;
        this.fatherPhone = fatherPhone;
        this.motherName = motherName;
        this.motherPhone = motherPhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherPhone() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone = fatherPhone;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherPhone() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone = motherPhone;
    }
}
