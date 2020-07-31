package com.udayarajurs.equi.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.udayarajurs.equi.R;

public class OtpLoginPage extends AppCompatActivity {

    private EditText PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_login_page);

        PhoneNumber = findViewById(R.id.User_number);

        Button otp_login = findViewById(R.id.Phone_Otp_login);
        Button otp_Gmail = findViewById(R.id.Login_Gmail);

        otp_Gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(OtpLoginPage.this,LoginPage.class));
            }
        });
        otp_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String number1 = PhoneNumber.getText().toString().trim();
                if(TextUtils.isEmpty(number1)){
                    PhoneNumber.setError("Please Enter Phone Number");
                }else {
                    String textToPass = PhoneNumber.getText().toString();
                    Intent intent = new Intent(OtpLoginPage.this, EnterOtpPage.class);
                    intent.putExtra("number", textToPass);
                    startActivity(intent);
                }

            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String UseNumber = extras.getString("returnNumber");
            PhoneNumber.setText(UseNumber);
        }
    }
}