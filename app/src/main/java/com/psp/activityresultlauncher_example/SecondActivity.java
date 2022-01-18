package com.psp.activityresultlauncher_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.psp.activityresultlauncher_example.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnPassData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra(MainActivity.REQUEST_KEY,"Hello brother");
                setResult(MainActivity.REQUEST_CODE,i);
                finish();
            }
        });

    }
}