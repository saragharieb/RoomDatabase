package com.example.roomdatabase.roomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

public class Repository {
    EmployeeDAO employeeDAO;
    SaleryDAO saleryDAO;

    public Repository(Application application) {
        MyRoomDataBase dataBase = MyRoomDataBase.getDataBase(application);
        employeeDAO = dataBase.employeeDAO;
        saleryDAO = dataBase.saleryDAO;
    }

    public void insertEmployee(Employee employee) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.insertEmployee(employee);
            }
        });
    }

    public void updateEmployee(Employee... employee) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.updateEmployee(employee);
            }
        });
    }

    public void deleteEmployee(Employee... employee) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.deleteEmployee(employee);
            }
        });
    }

    public void deleteEmployeebyemail(String email) {
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.deleteEmployeebyemail(email);
            }
        });
    }

    public LiveData<List<Employee>> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }

    public LiveData<List<Employee>> getEmployeeByName(String empName){
        return employeeDAO.getEmployeeByName(empName);
    }

    public LiveData<List<Employee>> getEmployeebyemail(String email){
        return employeeDAO.getEmployeebyemail(email);
    }

    public void insertSalery(Salery salery){
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                saleryDAO.insertSalery(salery);
            }
        });
    }

    public void updateSalery(Salery salery){
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                saleryDAO.updateSalery(salery);
            }
        });
    }

    public void deleteSalery(Salery salery){
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                saleryDAO.deleteSalery(salery);
            }
        });
    }

    public LiveData<List<Salery>> getEmpSaleries(int id){
        return saleryDAO.getEmpSaleries(id);
    }

    public LiveData<List<Salery>> getEmpSaleries(int id , Date from, Date to){
        return saleryDAO.getEmpSaleries(id,from,to);
    }

    public LiveData<List<Salery>> getEmpSaleries(Date from, Date to){
        return saleryDAO.getEmpSaleries(from,to);
    }

    public void getSaleriesSum(int emp_id , DoubleValueListner listner){
        MyRoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               double value= saleryDAO.getSaleriesSum(emp_id);
               listner.onValueSubmit(value);
            }
        });
    }


}
