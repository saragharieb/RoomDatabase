package com.example.roomdatabase;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.roomDatabase.Employee;
import com.example.roomdatabase.roomDatabase.MyViewModel;
import com.example.roomdatabase.views.SaleriesEmployeesRecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyViewModel myViewModel;
    SaleriesEmployeesRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        adapter = new SaleriesEmployeesRecyclerView(new ArrayList<>(), myViewModel);
        binding.mainRv.setAdapter(adapter);
        binding.mainRv.setHasFixedSize(true);
        binding.mainRv.setLayoutManager(new LinearLayoutManager(this));

       // String dateString = "2025-01-01";
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date;
        //try {
          // date = dateFormat.parse(dateString);
      //  } catch (ParseException e) {
        //    throw new RuntimeException(e);
        //}
        //Employee emp_1=new Employee(1,"mohamed","mohamed12@gmail.com",date);
       // myViewModel.insertEmployee(emp_1);
       // Employee emp_2=new Employee(2,"ahmed","ahmed12@gmail.com",date);
       // myViewModel.insertEmployee(emp_2);

        myViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });

        ActivityResultLauncher arl = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Employee employee = (Employee) result.getData()
                                    .getSerializableExtra(AddEmployeeActivity.EMPOYEE_KEY);
                            myViewModel.insertEmployee(employee);
                        }

                    }
                });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddSaleryActivity.class);
                arl.launch(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_salery_menu) {
            Intent intent = new Intent(this, AddSaleryActivity.class);
            startActivity(intent);
            return true;
        } else
            return false;
    }
}

















