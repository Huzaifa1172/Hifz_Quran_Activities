package com.example.cardviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Student_data extends AppCompatActivity {
    ArrayList<Student_data_Module> students=new ArrayList<Student_data_Module>();
    Recycle_StudentData_Adapter recycle_studentData_adapter;
    RecyclerView recyclerView;

    FloatingActionButton btnOpenDialog;
    int id=0;

    StudentDBHelper dbHelper=new StudentDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        Intent intent = getIntent();
        recyclerView=findViewById(R.id.Student_Data_Recycle);
        btnOpenDialog=findViewById(R.id.btnOpenNewDialog);

        //students.add(new Student_data_Module("1/2/467","2","1-35","2"));
        //students.add(new Student_data_Module("49/3/3","3","2-35","7"));
        //students.add(new Student_data_Module("1/4/5","4","4-65","5"));
        //students.add(new Student_data_Module("13/5/6","5","6-75","2"));
        //students.add(new Student_data_Module("1/6/7","6","1-33","3"));
        //students.add(new Student_data_Module("9/7/2","8","1-35","4"));


        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog= new Dialog(Student_data.this);
                dialog.setContentView(R.layout.add_std_data);
                EditText edit_date=dialog.findViewById(R.id.edit_date);
                EditText edit_manzil=dialog.findViewById(R.id.edit_manzil);
                EditText edit_sabaq=dialog.findViewById(R.id.edit_sabaq);
                EditText edit_sabqi=dialog.findViewById(R.id.edit_sabqi);
                Button btn_add_record=dialog.findViewById(R.id.btn_add_record);


                btn_add_record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date="",manzil="",sabaq="",sabqi="";
                        if(!edit_date.getText().toString().equals("") &&
                            !edit_manzil.getText().toString().equals("") &&
                            !edit_sabaq.getText().toString().equals("") &&
                            !edit_sabqi.getText().toString().equals(""))
                        {
                            date=edit_date.getText().toString();
                            manzil=edit_manzil.getText().toString();
                            sabaq=edit_sabaq.getText().toString();
                            sabqi=edit_sabqi.getText().toString();
                        }
                        else
                        {
                            Toast.makeText(Student_data.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                        }

                        Student_data_Module std_data=new Student_data_Module(date,manzil,sabaq,sabqi);
                        id=intent.getIntExtra("ID",0);
                        dbHelper.Add_Student_Data(id,std_data);
                        students.add(std_data);

                        recycle_studentData_adapter.notifyItemInserted(students.size()-1);
                        recyclerView.scrollToPosition(students.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        students=dbHelper.Fetch_Student_Data(intent.getIntExtra("ID",0));

        recycle_studentData_adapter=new Recycle_StudentData_Adapter(this,students);
        recyclerView.setAdapter(recycle_studentData_adapter);


    }
}