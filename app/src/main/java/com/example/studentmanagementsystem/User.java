package com.example.studentmanagementsystem;

public class User {
    public String FullName,Email,Password,PhoneNumber;

    public User(){


    }
    public User(String FullName,String Email,String Password,String PhoneNumber){
        this.FullName= FullName;
        this.Email= Email;
        this.Password=Password;
        this.PhoneNumber=PhoneNumber;
    }
}
