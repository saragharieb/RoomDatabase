package com.example.roomdatabase.roomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    public void insertEmployee(Employee employee) {
        repository.insertEmployee(employee);
    }

    public void updateEmployee(Employee... employee) {
        repository.updateEmployee(employee);
    }

    public void deleteEmployee(Employee... employee) {
        repository.deleteEmployee(employee);
    }

    public void deleteEmployeebyemail(String email) {
        repository.deleteEmployeebyemail(email);
    }

    public LiveData<List<Employee>> getAllEmployees(){
        return repository.getAllEmployees();
    }

    public LiveData<List<Employee>> getEmployeeByName(String empName){
        return repository.getEmployeeByName(empName);
    }

    public LiveData<List<Employee>> getEmployeebyemail(String email){
        return repository.getEmployeebyemail(email);
    }

    public void insertSalery(Salery salery){
                repository.insertSalery(salery);
    }

    public void updateSalery(Salery salery){
        repository.updateSalery(salery);
    }

    public void deleteSalery(Salery salery){
        repository.deleteSalery(salery);
    }

    public LiveData<List<Salery>> getEmpSaleries(int id){
        return repository.getEmpSaleries(id);
    }

    public LiveData<List<Salery>> getEmpSaleries(int id , Date from, Date to){
        return repository.getEmpSaleries(id,from,to);
    }

    public LiveData<List<Salery>> getEmpSaleries(Date from, Date to){
        return repository.getEmpSaleries(from,to);
    }

    public void getSaleriesSum(int emp_id , DoubleValueListner listner){
        repository.getSaleriesSum(emp_id,listner);
    }
}

