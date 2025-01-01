package com.example.roomdatabase;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomdatabase.databinding.ActivityAddSaleryBinding;
import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.roomDatabase.Employee;
import com.example.roomdatabase.roomDatabase.MyViewModel;
import com.example.roomdatabase.roomDatabase.Salery;
import com.example.roomdatabase.views.EmployeesSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddSaleryActivity extends AppCompatActivity {

    ActivityAddSaleryBinding binding;
    Calendar selectedDate;
    MyViewModel myViewModel;
    EmployeesSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddSaleryBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myViewModel= new ViewModelProvider(this).get(MyViewModel.class);

        binding.btnPickSaleryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        adapter=new EmployeesSpinnerAdapter(new ArrayList<>());
        binding.spEmployees.setAdapter(adapter);
        myViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });




        binding.btnSaveSalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strAmount=binding.etSaleryAmount.getText().toString();
                int id= (int) binding.spEmployees.getSelectedItemId();
                if(strAmount.isEmpty() || selectedDate == null)
                {
                    Toast.makeText(AddSaleryActivity.this, "Please enter a valid data",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                double amount =Double.parseDouble(strAmount);
                Salery salery=new Salery(amount ,selectedDate.getTime(),id);
                myViewModel.insertSalery(salery);
                finish();
            }
        });
    }



    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(selectedDate.getTime());

                    binding.etDateSalery.setText(formattedDate);
                },
                year, month, dayOfMonth
        );datePickerDialog.show();
    }
}