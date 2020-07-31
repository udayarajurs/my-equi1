package com.udayarajurs.equi.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.udayarajurs.equi.R;

public class AppInformationPage extends AppCompatActivity {

    private Button getStared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_information_page);

        getStared = findViewById(R.id.get_Stared);
        getStared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppInformationPage.this,LoginPage.class));
            }
        });
    }
}