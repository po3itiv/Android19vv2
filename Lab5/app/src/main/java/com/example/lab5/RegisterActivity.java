package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);

        username = (EditText) findViewById(R.id.enter_username);
        password = (EditText) findViewById(R.id.enter_password);
        dbHelper = new DBHelper(this);


    }

    public void addAccount(View view){


       String name = username.getText().toString();
       String pass = password.getText().toString();


        User user2 = new User(pass);
        User user = new User(name);
        User user1 = new User(name,pass);

        User userVoidFields = new User();


        if(userVoidFields.checkVoidFields(name, pass)){
            Toast.makeText(this,
                    "Ошибка. Есть незаполненные поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userVoidFields.checkLongPass(pass)){
            Toast.makeText(this,
                    "Ошибка. Пароль меньше 8", Toast.LENGTH_SHORT).show();
            return;
        }


        if(dbHelper.checkUsername(user)){
            Toast.makeText(this,
                    "Ошибка. Такой пользователь уже существует", Toast.LENGTH_SHORT).show();
            return;
        }


        if(dbHelper.insertData(user1))
            Toast.makeText(this,
                    "Пользователь успешно зарегистрирован", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,
                    "Ошибка регистрации", Toast.LENGTH_SHORT).show();


    }

    public void backToAuthorization(View view){
        finish();
    }
}