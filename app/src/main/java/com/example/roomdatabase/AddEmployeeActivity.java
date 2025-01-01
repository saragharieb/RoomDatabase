package com.example.roomdatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.example.roomdatabase.databinding.ActivityAddEmployeeBinding;
import com.example.roomdatabase.roomDatabase.Employee;

public class AddEmployeeActivity extends AppCompatActivity {

    ActivityAddEmployeeBinding binding;
    Calendar selectedDate;
    public static final String EMPOYEE_KEY ="employee";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding.btnSaveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.etName.getText().toString();
                String email=binding.etEmail.getText().toString();
                String strId=binding.etId.getText().toString();

                if(name.isEmpty() || email.isEmpty() || strId.isEmpty() || selectedDate == null)
                {
                    Toast.makeText(AddEmployeeActivity.this, "Please enter a valid data ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                int id=Integer.parseInt(strId);
                Employee employee=new Employee(id ,name,email, selectedDate.getTime());
                Intent intent=new Intent();
                intent.putExtra(EMPOYEE_KEY,employee);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        binding.brithdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
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
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(selectedDate.getTime());
                    binding.etDate.setText(formattedDate);
                },
                year, month, dayOfMonth
        );
        datePickerDialog.show();
    }
}