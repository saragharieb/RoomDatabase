package com.example.roomdatabase.roomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity
@TypeConverters({dateConverter.class})
public class Employee implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private Date brithDate;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Date brithDate) {
        this.brithDate = brithDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee() {
    }

    public Employee(int id, String name,  String email, Date brithDate) {
        this.id = id;
        this.name = name;
        this.brithDate = brithDate;
        this.email = email;
    }
}
