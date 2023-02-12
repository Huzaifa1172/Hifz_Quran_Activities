package com.example.cardviewpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycle_StudentData_Adapter extends RecyclerView.Adapter<Recycle_StudentData_Adapter.ViewHolder> {
    Context context;
    ArrayList<Student_data_Module> students;
    //StudentDBHelper dbHelper;

    Recycle_StudentData_Adapter(Context context,ArrayList<Student_data_Module> students)
    {
        this.context=context;
        this.students=students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.student_data_card,parent,false);
        ViewHolder view=new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.std=(Student_data_Module) students.get(holder.getAdapterPosition());
        holder.Date.setText(holder.std.getDate());
        holder.Manzil.setText(holder.std.manzil.toString());
        holder.Sabaq.setText(holder.std.sabaq.toString());
        holder.Sabqi.setText(holder.std.sabqi.toString());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Date,Manzil,Sabaq,Sabqi;
        Student_data_Module std;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Date=itemView.findViewById(R.id.Date);
            Manzil=itemView.findViewById(R.id.Manzil);
            Sabaq=itemView.findViewById(R.id.Sabaq);
            Sabqi=itemView.findViewById(R.id.Sabqi);

        }
    }
}
