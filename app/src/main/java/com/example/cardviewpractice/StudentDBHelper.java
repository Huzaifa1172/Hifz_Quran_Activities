package com.example.cardviewpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaCodec;

import java.util.ArrayList;

public class StudentDBHelper extends SQLiteOpenHelper {

    //Make a database name variable which has to be static ,final and private
    private static final String DATABASE_NAME="HafizDatabase";
    private static final int DATABASE_VERSION=1;

    //Student table name and column names
    private static final String STUDENT_TABLE="students";
    private static final String STD_ID="id";
    private static final String STD_NAME="std_name";
    private static final String STD_AGE="std_age";


    private static final String STUDENT_DATA_TABLE="std_data";
    private static final String STUDENT_ID="id";
    private static final String STUDENT_DATE="date";
    private static final String STUDENT_MANZIL="manzil";
    private static final String STUDENT_SABAQ="sabaq";
    private static final String STUDENT_SABQI="sabqi";

    public StudentDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + STUDENT_TABLE +
                "(" + STD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                STD_NAME + " TEXT NOT NULL,"  +
                STD_AGE + " TEXT NOT NULL" +
                ")"
        );
        db.execSQL("CREATE TABLE " + STUDENT_DATA_TABLE +
                "(" + STUDENT_ID + " INTEGER NOT NULL,"+
                STUDENT_DATE + " TEXT NOT NULL," +
                STUDENT_MANZIL + " TEXT NOT NULL," +
                STUDENT_SABAQ + " TEXT NOT NULL," +
                STUDENT_SABQI + " TEXT NOT NULL"+
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_DATA_TABLE);
        onCreate(db);
    }

    public void Add_Student(Student_Module std)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(STD_NAME,std.name);
        values.put(STD_AGE,std.age);
        db.insert(STUDENT_TABLE,null,values);
        //db.close();
    }
    public ArrayList<Student_Module> Fetch_Students()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + STUDENT_TABLE,null);
        ArrayList<Student_Module> students=new ArrayList<Student_Module>();
        while(cursor.moveToNext())
        {
            students.add(new Student_Module(cursor.getInt(0),cursor.getString(1), cursor.getString(2) ));
        }
        //db.close();
        return  students;


    }

    public void update_student(Student_Module std)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //String query="UPDATE " + STUDENT_TABLE + " SET " + STD_NAME + " = " +std.name +" , " + STD_AGE +" = " + std.age+" WHERE "+STD_ID+" = " +std.id;
        //db.execSQL(query);
        ContentValues values=new ContentValues();
        values.put(STD_NAME,std.name);
        values.put(STD_AGE,std.age);
        db.update(STUDENT_TABLE,values,STD_ID + " = " +std.id,null);
        //db.close();
    }
    public void delete_student(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(STUDENT_TABLE,STD_ID + " = ? ",new String []{String.valueOf(id)});
        //db.close();

    }

    public void Add_Student_Data(int id,Student_data_Module std)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(STUDENT_ID,id);
        values.put(STUDENT_DATE,std.date);
        values.put(STUDENT_MANZIL,std.manzil);
        values.put(STUDENT_SABAQ,std.sabaq);
        values.put(STUDENT_SABQI,std.sabqi);
        db.insert(STUDENT_DATA_TABLE,null,values);
        //db.close();
    }
    public ArrayList<Student_data_Module> Fetch_Student_Data( int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="SELECT * FROM " + STUDENT_DATA_TABLE +
                " WHERE " + STUDENT_ID + " = '" + id + "'";
        ArrayList<Student_data_Module> std=new ArrayList<Student_data_Module>();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex(STUDENT_DATE));
                String manzil = cursor.getString(cursor.getColumnIndex(STUDENT_MANZIL));
                String sabaq = cursor.getString(cursor.getColumnIndex(STUDENT_SABAQ));
                String sabqi = cursor.getString(cursor.getColumnIndex(STUDENT_SABQI));

                std.add(new Student_data_Module(date, manzil, sabaq, sabqi));
            } while (cursor.moveToNext());
        }
        cursor.close();
        //db.close();
        return std;
    }

}

