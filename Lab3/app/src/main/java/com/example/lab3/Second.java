package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Second extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("AppLogger", "Переопределние onCreate Second");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        // добавляем начальные элементы
        Collections.addAll(users);

        String Name = getIntent().getExtras().getString("Lab3");
        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(Name);

        String Value = getIntent().getExtras().getString("Lab3.1");
        TextView textView2 = (TextView) findViewById(R.id.textView6);
        textView2.setText(Value);

        // получаем элемент ListView
        usersList = findViewById(R.id.usersList);
        // создаем адаптер
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, users);
        // устанавливаем для списка адаптер
        usersList.setAdapter(adapter);

        // обработка установки и снятия отметки в списке
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // получаем нажатый элемент
                String user = adapter.getItem(position);
                if(usersList.isItemChecked(position))
                    selectedUsers.add(user);
                else
                    selectedUsers.remove(user);
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("AppLogger", "Переопределение onStop у Second");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("AppLogger", "Переопределение onStart уSecond");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("AppLogger", "Переопределение onPause у Second");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("AppLogger", "Переопределение onResume у Second");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("AppLogger", "Переопределение onRestart у Second");
    }
    public void add(View view){

        EditText userName = findViewById(R.id.userName);
        String user = userName.getText().toString();
        if(!user.isEmpty()){
            adapter.add(user);
            userName.setText("");
            adapter.notifyDataSetChanged();
        }
    }
    public void remove(View view){
        // получаем и удаляем выделенные элементы
        for(int i=0; i< selectedUsers.size();i++){
            adapter.remove(selectedUsers.get(i));
        }
        // снимаем все ранее установленные отметки
        usersList.clearChoices();
        // очищаем массив выбраных объектов
        selectedUsers.clear();

        adapter.notifyDataSetChanged();
    }
}