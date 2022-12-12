package com.example.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    ArrayList<String> accounts = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView accountsList;
    String accountName;
    DBHelper dbHelper;

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

    public void removeAccount(View view){
        EditText accountET = findViewById(R.id.adm_acc_entrie);
        String login  = accountET.getText().toString();
        User user = new User(login);
        if(dbHelper.deleteUser(user))
            Toast.makeText(this,
                    "Пользователь " + login + " успешно удалён", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,
                    "Ошибка удаления", Toast.LENGTH_SHORT).show();
        restoreTable();
    }

    // ????
    private List<User> restoreTable()
    {
        ArrayList<String> user = dbHelper.getDateList();
        List<User> usersList = new ArrayList<User>();

        adapter.clear();
        accounts.clear();




            for (int i = 0 , j = 0 ; i < user.size(); i = i + 2 , j++) {
                accounts.add(user.get(i) + " " + user.get(i + 1));

                User users = new User();

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