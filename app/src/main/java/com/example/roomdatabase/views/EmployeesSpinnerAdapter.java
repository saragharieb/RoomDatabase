package com.example.roomdatabase.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.roomDatabase.Employee;

import java.util.List;

public class EmployeesSpinnerAdapter extends BaseAdapter {
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    public EmployeesSpinnerAdapter(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return employees.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=view;
        if (view1 ==null)
        {
            view1= LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.custom_spinner_item,null,false);
        }
        TextView tv= view1.findViewById(R.id.tv_empName_sapinner);
        Employee emp = (Employee) getItem(i);
        tv.setText(emp.getName());
        return null;
    }
}
