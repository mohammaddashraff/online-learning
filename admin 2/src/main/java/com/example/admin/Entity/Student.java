package com.example.admin.Entity;

public class Student {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String affiliation;
    private String password;
    private String bio;

    public Student() {
    }

    public Student(Long id, String name, String email, String phone, String affiliation, String password, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.affiliation = affiliation;
        this.password = password;
        this.bio = bio;
    }

    // Getters and setters

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
