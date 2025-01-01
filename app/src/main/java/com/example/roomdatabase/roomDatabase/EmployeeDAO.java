package com.example.roomdatabase.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
@TypeConverters({dateConverter.class})
public interface EmployeeDAO {

    @Insert
    void insertEmployee(Employee employee);

    @Update
    void updateEmployee(Employee... employee);

    @Delete
    void deleteEmployee(Employee... employee);

    @Query("delete from Employee where email =:email")
    void deleteEmployeebyemail(String email);

    @Query("select * from Employee order by name asc")
    LiveData<List<Employee>> getAllEmployees();

    @Query("select * from Employee where name like '%'||:empName||'%'")
    LiveData<List<Employee>> getEmployeeByName(String empName);

    @Query("select * from Employee where email =:email")
    LiveData<List<Employee>> getEmployeebyemail(String email);
}
