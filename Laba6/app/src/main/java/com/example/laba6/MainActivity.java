package com.example.laba6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends Activity {

    // Объявляем об использовании следующих объектов:
    private EditText username;
    private EditText password;
    private TextView attempts;
    private TextView numberOfAttempts;
    private Button loginBttn;

    private SharedPreferences sharedPref;
    private final String saveLoginKey = "save_login";

    DBHelper dbHelper;

    // Число для подсчета попыток залогиниться:
    int numberOfRemainingLoginAttempts = 5;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        // Связываемся с элементами нашего интерфейса:
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        attempts = (TextView) findViewById(R.id.attempts);
        numberOfAttempts = (TextView) findViewById(R.id.number_of_attempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
        dbHelper = new DBHelper(this);

        loginBttn = (Button) findViewById(R.id.button_login);

        loginBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBttn.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Login();
                        loginBttn.post(new Runnable() {
                            @Override
                            public void run() {

                                loginBttn.setEnabled(true);
                            }
                        });

                    }
                }).start();
            }
        });

        Toast.makeText(MainActivity.this,
                "Переопределение onCreate у MainActivity", Toast.LENGTH_SHORT).show();
        Log.i("AppLogger", "Переопределение onCreate у MainActivity");


    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("AppLogger", "Переопределение onStop у MainActivity");
    }
    @Override
    protected void onStart(){
        super.onStart();


        Log.i("AppLogger", "Переопределение onStart у MainActivity");
    }
    @Override
    protected void onPause(){
        Log.i("AppLogger", "Locale до паузы: " + Locale.getDefault());
        super.onPause();
        Log.i("AppLogger", "Переопределение onPause у MainActivity");
        Log.i("AppLogger", "Locale после паузы: " + Locale.getDefault());
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("AppLogger", "Переопределение onResume у MainActivity");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("AppLogger", "Переопределение onRestart у MainActivity");
    }

    public void Login() {

        // показываем Toast сообщение об успешном входе:
        String name = username.getText().toString();
        String pass = password.getText().toString();
        User user = new User(name, pass);

        if(name.equals("admin") && pass.equals("admin")){
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
            return;
        }

        if (dbHelper.checkUsernamePassword(user)) {
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();
                }
            });
            // Выполняем переход на другой экран:
            Intent intent = new Intent(MainActivity.this,Second.class);
            intent.putExtra("Lab3", name);
            startActivity(intent);
        }

        // В другом случае выдаем сообщение с ошибкой:
        else {

            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
                }
            });

            numberOfRemainingLoginAttempts--;
            if(numberOfRemainingLoginAttempts == 0)
                finish();
            attempts.post(new Runnable() {
                @Override
                public void run() {
                    attempts.setVisibility(View.VISIBLE);

                }
            });

            numberOfAttempts.post(new Runnable() {
                @Override
                public void run() {
                    numberOfAttempts.setVisibility(View.VISIBLE);
                    numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

                }
            });

        }
    }

    public void Registrations(View view) {

        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }






}