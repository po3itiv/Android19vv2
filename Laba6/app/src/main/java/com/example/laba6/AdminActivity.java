package com.example.laba6;

import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    ArrayList<String> accounts = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView accountsList;
    String accountName;
    DBHelper dbHelper;
    Button delAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        dbHelper = new DBHelper(this);

        // добавляем начальные элементы
        Collections.addAll(accounts);

        // получаем элемент ListView
        accountsList = findViewById(R.id.acc_table);
        // создаем адаптер
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, accounts);
        // устанавливаем для списка адаптер
        accountsList.setAdapter(adapter);

        delAccount = (Button) findViewById(R.id.adm_del);

        delAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delAccount.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        removeAccount();
                        delAccount.post(new Runnable() {
                            @Override
                            public void run() {
                                delAccount.setEnabled(true);
                            }
                        });
                    }
                }).start();
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override
    protected void onStart(){
        restoreTable();
        super.onStart();

    }
    @Override
    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onRestart(){
        super.onRestart();

    }

    public void removeAccount() {
        EditText accountET = findViewById(R.id.adm_acc_entrie);
        String login = accountET.getText().toString();
        User user = new User(login);
        if (dbHelper.deleteUser(user)) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Пользователь " + login + " успешно удалён", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Ошибка удаления", Toast.LENGTH_SHORT).show();
                }
            });

        }

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                restoreTable();
            }
        });
    }

    // ????
    private List<User> restoreTable()
    {
        ArrayList<String> user = dbHelper.getDateList();
        List<User> usersList = new ArrayList<User>();

        adapter.clear();
        accounts.clear();




            for (int i = 0 , j = 0 ; i < user.size(); i = i + 2 , j++) {
                accounts.add(j + " " + user.get(i) + " " + user.get(i + 1));

                User users = new User();
                users.setID(j);
                users.setLogin(user.get(i));
                users.setPass(user.get(i + 1));
                usersList.add(users);

        }
    return usersList;
    }



    public void backToAuthorization(View view) {
        finish();
    }
}