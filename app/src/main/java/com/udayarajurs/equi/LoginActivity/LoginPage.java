package com.udayarajurs.equi.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.common.ConnectionResult;
import com.udayarajurs.equi.R;

public class LoginPage extends AppCompatActivity {
    private Button GoogleLogin;
    private Button OtpLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        GoogleLogin = findViewById(R.id.google_Sign_up);
        OtpLogin = findViewById(R.id.Phone_Otp_login);


        GoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,ProfileFirstPage.class));

            }
        });

        OtpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,OtpLoginPage.class));
            }
        });

    }

}