package com.suzuha.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {
    TextView PwCheck;
    EditText Name, Email, Password, RePassword;
    RadioGroup Genders;
    Spinner Nationality;
    CheckBox Agree;
    ImageView PFP;
    String[] Nation = {"", "United State", "Japan", "Canada"};
    DataBase dataBase = new DataBase(MainActivity.this);
    private Uri selectedImage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiElement();

        intent = new Intent(MainActivity.this, MainActivity2.class);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Nation);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Nationality.setAdapter(aa);

        RePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!RePassword.getText().toString().equals(Password.getText().toString())) {
                    PwCheck.setText("Passwords doesn't match. Try again");
                } else PwCheck.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!RePassword.getText().toString().equals(Password.getText().toString())) {
                    PwCheck.setText("Passwords doesn't match. Try again");
                } else PwCheck.setText("");
            }
        });

        (findViewById(R.id.btChooseIMG)).setOnClickListener(view -> {
            ImagePicker.with(MainActivity.this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start();
        });

        (findViewById(R.id.btRegister)).setOnClickListener(view -> {
            if (selectedImage != null
                    && !Name.getText().toString().isEmpty() &&
                    !Email.getText().toString().isEmpty() &&
                    !Password.getText().toString().isEmpty() &&
                    RePassword.getText().toString().equals(Password.getText().toString()) &&
                    Genders.getCheckedRadioButtonId() != -1 &&
                    Nationality.getSelectedItemPosition() != 0 &&
                    Agree.isChecked()) {
                dataBase.ADD(selectedImage.toString(),
                        Name.getText().toString(),
                        Email.getText().toString(),
                        Password.getText().toString(),
                        genderGetter(),
                        Nationality.getSelectedItem().toString());
                Show.Toast(this, "Client Added");
            } else if (!RePassword.getText().toString().equals(Password.getText().toString())) {
                PwCheck.setText("Re enter password");
            } else if (Genders.getCheckedRadioButtonId() == -1) {
                Show.Toast(this, "Select your gender");
            } else if (Nationality.getSelectedItemPosition() == 0) {
                Show.Toast(this, "Select your country");
            } else if (!Agree.isChecked()){
                Show.Toast(this, "Please agree to term to register");
            }
        });

        (findViewById(R.id.btDatabase)).setOnClickListener(view -> startActivity(intent));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectedImage = data.getData();
        if (null != selectedImage) {
            PFP.setImageURI(selectedImage);
        }
    }

    public String genderGetter() {
        String GenderSelection = "";
        if (Genders.getCheckedRadioButtonId() != -1) {
            int radioButtonID = Genders.getCheckedRadioButtonId();
            View radioButton = Genders.findViewById(radioButtonID);
            int idx = Genders.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) Genders.getChildAt(idx);
            GenderSelection = (String) btn.getText();
        }
        return GenderSelection;
    }

    public void uiElement() {
        PwCheck = findViewById(R.id.tvPasswordConfimation);
        PFP = findViewById(R.id.pfp);
        Name = findViewById(R.id.etName);
        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        RePassword = findViewById(R.id.etRePassword);
        Genders = findViewById(R.id.radiogenderGroup);
        Nationality = findViewById(R.id.spinNation);
        Agree = findViewById(R.id.cbAgree);
    }
}