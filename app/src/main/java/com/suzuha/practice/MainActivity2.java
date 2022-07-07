package com.suzuha.practice;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    RecyclerView data;
    DataBase dataBase = new DataBase(this);
    ArrayList<Client> al_Client = new ArrayList<>();
    ActivityResultLauncher<Intent> SecondActivityResultLauncher;
    static String realPass;
    static int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data = findViewById(R.id.FullData);
        data.setLayoutManager(new LinearLayoutManager(this));

        al_Client = dataBase.readList();

        Adapter adapter = new Adapter(this, al_Client);

        data.setAdapter(adapter);

        data.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity2.this, data, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                realPass = al_Client.get(position).getPassword();
                idx = position;
                openDialog();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity2.this);
                view = inflater.inflate(R.layout.layout_delete, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setView(view)
                        .setTitle("Delete this user?")
                        .setNegativeButton("Cancel", ((dialogInterface, i) -> {

                        }))
                        .setPositiveButton("Yes", ((dialogInterface, i) -> {
                            dataBase.deleteClient(al_Client.get(position).getEmail());
                            adapter.notifyItemRemoved(position);
                            finish();
                            startActivity(getIntent());
                        }))
                        .show();
            }
        }));
    }

    private void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyText(String Password) {
    }

    public void LaunchActivityForResult() {
        SecondActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {

                }
        );
    }
}