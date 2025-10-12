package com.arsha.code.userFile.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_details")
public class User {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID") // matches DB column
    private Long userID;


    private String name;
    private LocalDate date;
    private String state;
    private String createdBy;
    private LocalDate createdAt;

    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FileRecord> files = new ArrayList<>();*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FileRecord> files = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserID() {
        return userID;
    }

    public List<FileRecord> getFiles() {
        return files;
    }

    public void setFiles(List<FileRecord> files) {
        this.files = files;
    }

}
