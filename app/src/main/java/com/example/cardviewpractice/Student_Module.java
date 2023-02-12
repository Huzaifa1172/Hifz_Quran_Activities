package com.example.cardviewpractice;

public class Student_Module {
    int id;
    int image;
    String name;
    String age;
    public Student_Module(int id,int i,String n,String a)
    {
        this.id=id;
        image=i;
        name=n;
        age=a;
    }
    public Student_Module(String n,String a)
    {
        name=n;
        age=a;
    }
    public Student_Module(int id,String n,String a)
    {
        this.id=id;
        name=n;
        age=a;
    }


}
