package com.psp.activityresultlauncher_example;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.psp.activityresultlauncher_example.databinding.ActivityMainBinding;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public static final int REQUEST_CODE = 101;
    public static final String REQUEST_KEY = "myKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intentLauncher.launch(intent);
            }
        });


        binding.btnReqPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        });
    }


    private final ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result != null && result.getResultCode() == REQUEST_CODE) {
                        if(result.getData() != null) {
                            String value = result.getData().getStringExtra(REQUEST_KEY);
                            Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    private final ActivityResultLauncher<String> requestLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {

                    if(result != null) {
                        if(result) {
                            // granted
                            Toast.makeText(MainActivity.this, "Granted", Toast.LENGTH_SHORT).show();
                        } else {
                            // denied
                            Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

}