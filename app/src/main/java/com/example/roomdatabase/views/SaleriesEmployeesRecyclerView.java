package com.example.roomdatabase.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.databinding.CustomSaleryEmployeeItemBinding;
import com.example.roomdatabase.roomDatabase.DoubleValueListner;
import com.example.roomdatabase.roomDatabase.Employee;
import com.example.roomdatabase.roomDatabase.MyViewModel;
import com.example.roomdatabase.roomDatabase.Salery;

import java.util.List;

public class SaleriesEmployeesRecyclerView extends
        RecyclerView.Adapter<SaleriesEmployeesRecyclerView.SalEmpHoler> {

    private List<Employee> employees;
    private MyViewModel myViewModel;

    public SaleriesEmployeesRecyclerView(List<Employee> employees, MyViewModel myViewModel) {
        this.employees = employees;
        this.myViewModel = myViewModel;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SaleriesEmployeesRecyclerView.SalEmpHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalEmpHoler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_salery_employee_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SaleriesEmployeesRecyclerView.SalEmpHoler holder, int position) {
        Employee e=employees.get(position);
        holder.bind(e,myViewModel);

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class SalEmpHoler extends RecyclerView.ViewHolder {
        CustomSaleryEmployeeItemBinding binding;
        Employee employee;
        public SalEmpHoler(@NonNull View itemView) {
            super(itemView);
            binding=CustomSaleryEmployeeItemBinding.bind(itemView);
        }
        public void bind(Employee employee,MyViewModel myViewModel)
        {
            this.employee=employee;
            binding.tvName.setText(employee.getName());
            myViewModel.getSaleriesSum(employee.getId(), new DoubleValueListner() {
                @Override
                public void onValueSubmit(Double value) {
                    binding.tvSalery.setText(String.valueOf(value));
                }
            });
        }
    }
}
