package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MyActivity extends AppCompatActivity {
private int sValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button reset = findViewById(R.id.reset);

        TextView valueTv = findViewById(R.id.value);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                sValue++;
                button1.setBackgroundColor(0xFF00FF00);
                button1.setText("НАЖАТО!");
                String convert = Integer.toHexString(sValue).toUpperCase();
                valueTv.setText(String.valueOf(convert));


            }


        });
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                sValue++;
                button2.setBackgroundColor(0xFF00FF00);
                button2.setText("КЛИКНУТО!");
                String convert = Integer.toHexString(sValue).toUpperCase();
                valueTv.setText(String.valueOf(convert));
            }


        });
        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int oldValue = sValue;
                sValue = 0;
                valueTv.setText(String.valueOf(sValue));
                Snackbar.make(v,"Значение удалено!",Snackbar.LENGTH_SHORT)
                        .setAction("Отменить", ignored ->
                        {
                            button1.setBackgroundColor(0xFF00FF00);
                            button2.setBackgroundColor(0xFF00FF00);
                            sValue = oldValue;
                            valueTv.setText(String.valueOf(sValue));
                        })
                        .show();
            }


        });
    }
}