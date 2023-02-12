package com.example.cardviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Student_Module> students=new ArrayList<Student_Module>();
    Recycle_StudentView_Adapter recycle_studentView_adapter;
    RecyclerView recyclerView;

    FloatingActionButton btnOpenDialog;

    StudentDBHelper dbHelper=new StudentDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.Student_recycle);
        btnOpenDialog= findViewById(R.id.btnOpenDialog);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog= new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_upd_lay);
                EditText edit_name=dialog.findViewById(R.id.edit_name);
                EditText edit_Age=dialog.findViewById(R.id.edit_age);
                Button btn_Action=dialog.findViewById(R.id.btn_action);

                btn_Action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",age="";
                        if(!edit_name.getText().toString().equals("") &&
                            !edit_Age.getText().toString().equals("")) {
                            name = edit_name.getText().toString();
                            age = edit_Age.getText().toString();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                        }

                        dbHelper.Add_Student(new Student_Module(name,age));
                        students=dbHelper.Fetch_Students();
                        recycle_studentView_adapter=new Recycle_StudentView_Adapter(MainActivity.this,students);
                        recyclerView.setAdapter(recycle_studentView_adapter);
                        recycle_studentView_adapter.notifyItemInserted(students.size()-1);
                        recyclerView.scrollToPosition(students.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        students=dbHelper.Fetch_Students();

        recycle_studentView_adapter=new Recycle_StudentView_Adapter(this,students);
        recyclerView.setAdapter(recycle_studentView_adapter);

    }
}