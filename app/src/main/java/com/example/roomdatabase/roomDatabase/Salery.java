package com.example.roomdatabase.roomDatabase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Employee.class,
        parentColumns = {"id"},childColumns= {"emp_id"},onUpdate = ForeignKey.CASCADE
        ,onDelete = ForeignKey.CASCADE  )})
@TypeConverters({dateConverter.class})
public class Salery {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double amount;
    private Date date;
    private int emp_id;

    public Salery(int id, double amount, Date date, int emp_id) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.emp_id = emp_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public Salery() {
    }

    public Salery( double amount, Date date, int emp_id) {
        this.amount = amount;
        this.date = date;
        this.emp_id = emp_id;
    }
}
