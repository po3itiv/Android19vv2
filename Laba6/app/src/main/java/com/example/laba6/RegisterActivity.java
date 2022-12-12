package com.example.laba6;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    DBHelper dbHelper;

    private Button crAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);

        username = (EditText) findViewById(R.id.enter_username);
        password = (EditText) findViewById(R.id.enter_password);
        dbHelper = new DBHelper(this);

        crAccount = (Button) findViewById(R.id.btnAdd);

        crAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crAccount.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        addAccount();
                        crAccount.post(new Runnable() {
                            @Override
                            public void run() {
                                crAccount.setEnabled(true);
                            }
                        });
                    }
                }).start();
            }
        });

    }

    public void addAccount(){


       String name = username.getText().toString();
       String pass = password.getText().toString();
         User user = new User(name);
        User user1 = new User(name,pass);
        User userVoidFields = new User();

        if(userVoidFields.checkVoidFields(name, pass)){
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Ошибка. Есть незаполненные поля", Toast.LENGTH_SHORT).show();
                }
            });

            return;
        }

        if(userVoidFields.checkLongPass(pass)){
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(),
                    "Ошибка. Пароль меньше 8", Toast.LENGTH_SHORT).show();
                }
            });

            return;
        }


        if(dbHelper.checkUsername(user)){
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Ошибка. Такой пользователь уже существует", Toast.LENGTH_SHORT).show();
                }
            });

            return;
        }


        if(dbHelper.insertData(user1)){
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Пользователь успешно зарегистрирован", Toast.LENGTH_SHORT).show();
                }
            });

    }
        else {
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),
                        "Ошибка регистрации", Toast.LENGTH_SHORT).show();
            }
        });

    }

    }

    public void backToAuthorization(View view){
        finish();
    }
}