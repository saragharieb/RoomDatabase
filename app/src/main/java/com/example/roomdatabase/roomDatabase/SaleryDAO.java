package com.example.roomdatabase.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters({dateConverter.class})
public interface SaleryDAO {
    @Insert
    void insertSalery(Salery salery);

    @Update
    void updateSalery(Salery salery);

    @Delete
    void deleteSalery(Salery salery);

    @Query("select * from Salery where emp_id=:id order by date desc")
    LiveData<List<Salery>> getEmpSaleries(int id);

    @Query("select * from Salery where emp_id=:id AND date>=:from AND date<=:to order by date desc")
    LiveData<List<Salery>> getEmpSaleries(int id , Date from, Date to);

    @Query("select * from Salery where date>=:from AND date<=:to order by date desc")
    LiveData<List<Salery>> getEmpSaleries(Date from, Date to);

    @Query("select sum(amount) from Salery where emp_id =:emp_id")
    double getSaleriesSum(int emp_id);

}
