package com.example.e15gestitb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public static boolean isReturningFromOtherFragment = false;
    public static boolean isUpdating = false;
    static String[] status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = getResources().getStringArray(R.array.status);
    }


}