package com.example.lab5;

import android.content.Context;
import android.widget.Toast;

public class User{

    int _id;
    String _login;
    String _pass;
    String _newpass;
    Context context;

    public User(Context context){
        this.context = context;
    }

    public User(String login, String pass, String newpass){
        this._login = login;
        this._pass = pass;
        this._newpass = newpass;
    }
    public User(String login, String pass){
        this._login = login;
        this._pass = pass;

    }

    public User(String login){
        this._login = login;
    }

    public User() {

    }

    public void setID(int id){
        this._id = id;
    }

    public String getLogin(){
        return this._login;
    }
    public void setLogin(String login){
        this._login = login;
    }

    public String getPass(){
        return this._pass;
    }
    public void setPass(String pass){
        this._pass = pass;
    }
    public String getNewPass(){
        return this._newpass;
    }


    public boolean checkVoidFields(String name, String pass){
        boolean result = false;
        if(name.equals("") || pass.equals("")){
            result = true;

        }

        return result;

    }

    public boolean checkLongPass(String pass){
        boolean result = false;
        if(pass.length()<8){
            result = true;

        }

        return result;

    }



}
