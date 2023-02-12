package com.example.cardviewpractice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycle_StudentView_Adapter extends RecyclerView.Adapter<Recycle_StudentView_Adapter.ViewHolder> {

    Context context;
    ArrayList<Student_Module> students;
    StudentDBHelper dbHelper;
    Recycle_StudentView_Adapter(Context context, ArrayList<Student_Module> students)
    {
        this.context=context;
        this.students=students;
        this.dbHelper=new StudentDBHelper(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.student_card,parent,false);
        ViewHolder view=new ViewHolder(v);
        return view;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.std=(Student_Module) students.get(holder.getAdapterPosition());
        holder.std_name.setText(holder.std.name);
        holder.std_age.setText(holder.std.age);

        holder.std_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_upd_lay);

                EditText edit_name=dialog.findViewById(R.id.edit_name);
                EditText edit_Age=dialog.findViewById(R.id.edit_age);
                TextView std_name_title=dialog.findViewById(R.id.std_name_title);
                Button btn_Action=dialog.findViewById(R.id.btn_action);
                std_name_title.setText("Update Student");
                btn_Action.setText("Update");

                edit_name.setText(students.get(holder.getAdapterPosition()).name);
                edit_Age.setText(students.get(holder.getAdapterPosition()).age);

                btn_Action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",age="";

                        if(!edit_name.getText().toString().equals("") && !edit_Age.getText().toString().equals("") )
                        {
                            name=edit_name.getText().toString();
                            age=edit_Age.getText().toString();
                        }
                        else {
                            Toast.makeText(context, "Please Enter All Details", Toast.LENGTH_SHORT);
                        }
                        Student_Module student=new Student_Module(students.get(holder.getAdapterPosition()).id,name,age);
                        students.set(position,new Student_Module(name,age));
                        notifyItemChanged(position);
                        dbHelper.update_student(student);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                }
            }
        );
        holder.std_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete Student")
                        .setMessage("Are You Sure want to delete")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int id =students.get(position).id;
                                students.remove(position);
                                notifyItemRemoved(position);
                                dbHelper.delete_student(id);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView std_name,std_age;
        ImageView std_img;
        Student_Module std;
        Button std_update,std_delete;
        LinearLayout Student_Row;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            std_name=itemView.findViewById(R.id.std_name);
            std_age=itemView.findViewById(R.id.std_age);
            std_img=itemView.findViewById(R.id.std_img);
            std_update=itemView.findViewById(R.id.std_update);
            std_delete=itemView.findViewById(R.id.std_delete);
            Student_Row=itemView.findViewById(R.id.Student_Row);
            Student_Row.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent =new Intent(v.getContext(),Student_data.class);
            intent.putExtra("ID",std.id);
            v.getContext().startActivity(intent);
        }
    }
}
