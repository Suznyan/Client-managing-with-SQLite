package com.suzuha.practice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText ETpassword;
    private ExampleDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("ok", ((dialogInterface, i) -> {
                    String password = ETpassword.getText().toString();
                    listener.applyText(password);
                    if (Objects.equals(password, MainActivity2.realPass)){
                        Show.Toast(getContext(), "Password accepted");
                        Intent intent = new Intent(getContext(), Detail.class);
                        startActivity(intent);
                    }else Show.Toast(getContext(), "Wrong Password");

                }));

        ETpassword = view.findViewById(R.id.editTextTextPassword);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyText(String Password);
    }
}
