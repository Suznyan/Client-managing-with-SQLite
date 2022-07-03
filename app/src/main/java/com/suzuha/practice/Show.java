package com.suzuha.practice;

import android.content.Context;
import android.widget.Toast;

public class Show {
    public static void Toast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
