package com.example.agrinet;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = findViewById(R.id.emailET);
        final EditText mobile = findViewById(R.id.mobileET);

        final EditText password = findViewById(R.id.passwordET);
        final EditText conpassword = findViewById(R.id.conpasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conpasswordIcon = findViewById(R.id.conpasswordIcon);

        final AppCompatButton signUpBtn = findViewById(R.id.signupBtn);
        final TextView signInBtn = findViewById(R.id.signinBtn);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passwordShowing){
                    passwordShowing = false;

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_visibility_24);
                }
                else{
                    passwordShowing = true;

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.baseline_visibility_24);
                }
                password.setSelection(password.length());

            }
        });

        conpasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(conPasswordShowing){
                    conPasswordShowing = false;

                    conpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conpasswordIcon.setImageResource(R.drawable.baseline_visibility_24);
                }
                else{
                    conPasswordShowing = true;

                    conpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conpasswordIcon.setImageResource(R.drawable.baseline_visibility_24);
                }
                conpassword.setSelection(conpassword.length());

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final String getMobileTxt = mobile.getText().toString();
            final String getEmailTxt = email.getText().toString();

            Intent intent = new Intent(Register.this, OTPVerification.class);

            intent.putExtra("mobile",getMobileTxt);
            intent.putExtra("email", getEmailTxt);

            startActivity(intent);

            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}