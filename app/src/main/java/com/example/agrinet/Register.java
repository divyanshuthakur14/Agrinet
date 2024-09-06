package com.example.agrinet;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;

    FirebaseAuth mAuth;
    ProgressBar progressBar;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name = findViewById(R.id.fullNameET);
        final EditText email = findViewById(R.id.emailET);
        final EditText mobile = findViewById(R.id.mobileET);
        final EditText password = findViewById(R.id.passwordET);
        final EditText conpassword = findViewById(R.id.conpasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conpasswordIcon = findViewById(R.id.conpasswordIcon);

        final AppCompatButton signUpBtn = findViewById(R.id.signupBtn);
        final TextView signInBtn = findViewById(R.id.signinBtn);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


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

            progressBar.setVisibility(View.VISIBLE);
            final String getMobileTxt = mobile.getText().toString();
            final String getEmailTxt = email.getText().toString();
            final String getPasswordTxt = password.getText().toString();


            if(TextUtils.isEmpty(getEmailTxt)){

                Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }

                if(TextUtils.isEmpty(getPasswordTxt)){

                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(getEmailTxt, getPasswordTxt)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {


                                    Toast.makeText(Register.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Register.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


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