package com.suzuha.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView data;
    DataBase dataBase = new DataBase(this);
    ArrayList<Client> al_Client = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data = findViewById(R.id.FullData);
        data.setLayoutManager(new LinearLayoutManager(this));

        al_Client =  dataBase.readList();

        Adapter adapter = new Adapter(this, al_Client);

        runOnUiThread(adapter::notifyDataSetChanged);

        data.setAdapter(adapter);


    }
}