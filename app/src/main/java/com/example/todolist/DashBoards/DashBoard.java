package com.example.todolist.DashBoards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todolist.DataBase.AppDataBase;
import com.example.todolist.MainActivity;
import com.example.todolist.Models.taskModel;
import com.example.todolist.Pickers.DatePickerFragment;
import com.example.todolist.Pickers.TimePickerFragment;
import com.example.todolist.R;

import java.util.Calendar;


public class DashBoard extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {
    public int pr = 1;
    EditText taskname;
    Spinner spinner;
    Button Add , pick_time1,pick_date1,update;
    AppDataBase appDataBase;
    String name;
    int year,month,day,hour,minute;
    int pr1=1;
    int key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        //spinner
        spinner = findViewById(R.id.Spinner);
        pick_date1 = findViewById(R.id.pick_date1);
        pick_time1 = findViewById(R.id.pick_time1);
        taskname = findViewById(R.id.Task_Name_txt);
        update = findViewById(R.id.Add_btn2);
        Add = findViewById(R.id.Add_btn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class,"room2").build();
        spinner.setOnItemSelectedListener(this);
        key = getIntent().getIntExtra("key",0);

        if (key== 1)
        {
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name = taskname.getText().toString();
                    if(spinner.getSelectedItem().toString().equals("High"))
                    {
                        pr=1;
                    }else if(spinner.getSelectedItem().toString().equals("Medium"))
                    {
                        pr=2;
                    }else if(spinner.getSelectedItem().toString().equals("Low"))
                    {
                        pr=3;
                    }
                    taskModel tM = new taskModel(name, pr,year,month,day,hour,minute);
                    new insert().execute(tM);
                    startActivity(new Intent(DashBoard.this, MainActivity.class));
                    finish();
                }
            });
        }else
        {
            taskModel tt =(taskModel) getIntent().getSerializableExtra("tt");
                name=tt.getTaskName();
                pr1=tt.getPriority();
                year=tt.getDateYear();
                month=tt.getDateMonth();
                day=tt.getDateDay();
                hour=tt.getTimeHoure();
                minute=tt.getTimeMin();

                taskname.setText(name);
                pick_time1.setText(hour+" : "+minute);
                pick_date1.setText(year+" / "+month+" / "+day);

           final int id=tt.getId();
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name =  taskname.getText().toString();
                    if(spinner.getSelectedItem().toString().equals("High"))
                    {
                        pr1=1;
                    }else if(spinner.getSelectedItem().toString().equals("Medium"))
                    {
                        pr1=2;
                    }else if(spinner.getSelectedItem().toString().equals("Low"))
                    {
                        pr1=3;
                    }
                    taskModel tM = new taskModel(id,name, pr1,year,month,day,hour,minute);
                    new update().execute(tM);
                    startActivity(new Intent(DashBoard.this, MainActivity.class));
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DashBoard.this , MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
//        TimePickerDialog tpd = new TimePickerDialog(getSupportFragmentManager(), new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
//                Calendar datetime = Calendar.getInstance();
//                Calendar calendar = Calendar.getInstance();
//                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                datetime.set(Calendar.MINUTE, minute);
//                if(datetime.getTimeInMillis()>=calendar.getTimeInMillis()){
//                    int hour = hourOfDay % 12;
//                }else
//                {Toast.makeText(DashBoard.class, "Invalid Time", Toast.LENGTH_LONG).show();}
//
//            }
//        }, hour, minute, true);
//        tpd.show();
        newFragment.show(getSupportFragmentManager(),"timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int Y, int M, int D) {
        year = Y;
        month = M;
        day = D;
        pick_date1.setText(year+" / "+month+" / "+day);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        hour = h;
        minute=m;
        pick_time1.setText(hour+" : "+minute);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "not selected", Toast.LENGTH_SHORT).show();
    }
    //**********************************************
    class insert extends  AsyncTask<taskModel ,Void,Void>
    {
        @Override
        protected Void doInBackground(taskModel... taskModels) {
            appDataBase.taskDao().insert(taskModels);
            return null;
        }
    }
    //**********************************************
    class update extends  AsyncTask<taskModel ,Void,Void>
    {
        @Override
        protected Void doInBackground(taskModel... taskModels) {
            appDataBase.taskDao().update(taskModels[0]);
            return null;
        }
    }
}


