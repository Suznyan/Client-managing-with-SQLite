package com.suzuha.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {
    ImageView imageView;
    ArrayList<Client> AL = new ArrayList<>();
    DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);

        AL = dataBase.readList();

        imageView.setImageURI(Uri.parse(AL.get(MainActivity2.idx).getUrl()));

    }
}