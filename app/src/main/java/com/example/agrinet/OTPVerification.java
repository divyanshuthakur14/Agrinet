package com.example.agrinet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPVerification extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        enternumber = findViewById(R.id.input_mobile_number);
        getotpbutton= findViewById(R.id.buttongetotp);

        final ProgressBar progressbar = findViewById(R.id.progressbar_sending_otp);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!enternumber.getText().toString().trim().isEmpty()){
                    if(enternumber.getText().toString().trim().length() ==10){

                        progressbar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + enternumber.getText().toString(), 60, TimeUnit.SECONDS, OTPVerification.this,

                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progressbar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.INVISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {


                                        progressbar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
                                        Toast.makeText(OTPVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                                        progressbar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
                                        intent.putExtra("mobile",enternumber.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);
                                    }
                                }

                        );

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