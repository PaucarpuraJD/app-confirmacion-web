package org.jotad.app.confirmation.models;

import java.time.LocalDate;

public class Confirmant {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private LocalDate birthDate;
    private Family family;
    private Sacrament sacrament;

    public Confirmant() {
    }

    public Confirmant(Integer id, String name, String address, String phone, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {this.address = address;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Family getFamily() {return family;}

    public void setFamily(Family family) {this.family = family;}

    public Sacrament getSacrament() {return sacrament;}

    public void setSacrament(Sacrament sacrament) {this.sacrament = sacrament;}
}
