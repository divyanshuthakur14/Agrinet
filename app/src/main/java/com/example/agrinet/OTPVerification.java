package com.example.agrinet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OTPVerification extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        enternumber = findViewById(R.id.input_mobile_number);
        getotpbutton= findViewById(R.id.buttongetotp);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!enternumber.getText().toString().trim().isEmpty()){
                    if(enternumber.getText().toString().trim().length() ==10){

                        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
                        intent.putExtra("mobile",enternumber.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(OTPVerification.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                    }}else{

                        Toast.makeText(OTPVerification.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                    }

                }


        });

    }
}