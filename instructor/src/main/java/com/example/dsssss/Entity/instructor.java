package com.example.dsssss.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "instructor")
public class instructor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String affiliation;
    private int yearsOfExperience;
    private String bio;
    public instructor(String name, String email, String password, String affiliation, int yearsOfExperience, String bio) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.affiliation = affiliation;
        this.yearsOfExperience = yearsOfExperience;
        this.bio = bio;
    }

    public instructor() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}


