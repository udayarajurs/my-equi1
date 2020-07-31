package com.udayarajurs.equi.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.udayarajurs.equi.R;

public class EnterOtpPage extends AppCompatActivity {

    private EditText OtpNumber;
    private TextView showNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp_page);

        OtpNumber = findViewById(R.id.enter_otp_number);
         showNumber = findViewById(R.id.user_number_EnterOtp);
        Button changeNumber = findViewById(R.id.change_number);
        Button PhoneOtpLogin = findViewById(R.id.Phone_Otp_login);
        Button LoginGmailOtpPage = findViewById(R.id.Login_Gmail_otp_page);



        LoginGmailOtpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnterOtpPage.this,LoginPage.class));
            }
        });


        PhoneOtpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number1 = OtpNumber.getText().toString().trim();
                if(TextUtils.isEmpty(number1)){
                    OtpNumber.setError("Please Enter OTP Number");
                }else {
                    startActivity(new Intent(EnterOtpPage.this, ProfileFirstPage.class));
                }
            }
        });

        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToPass = showNumber.getText().toString();
                Intent intent = new Intent(EnterOtpPage.this, OtpLoginPage.class);
                intent.putExtra("returnNumber", textToPass);
                startActivity(intent);;
            }
        });


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String UseNumber = extras.getString("number");
            showNumber.setText(UseNumber);
        }


        }
}