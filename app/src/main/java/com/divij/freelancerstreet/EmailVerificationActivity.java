package com.divij.freelancerstreet;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class EmailVerificationActivity extends AppCompatActivity {

    Button vBtn, eBtn;
    CheckBox cTCbox;
    private FirebaseAuth mAuth;
    private TextView mTerms;
    Timer timer;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EmailVerificationActivity.this,RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        vBtn = findViewById(R.id.verificationButton);
        cTCbox = findViewById(R.id.cBox);
        eBtn = findViewById(R.id.explore);
        mAuth = FirebaseAuth.getInstance();
        mTerms = findViewById(R.id.terms);
        Intent i1s = getIntent();
        Bundle bundle = i1s.getExtras();

        String currentUserEmailid = (String) bundle.get("email");
        String currentUserpassword = (String) bundle.get("password");
        mTerms.setMovementMethod(LinkMovementMethod.getInstance());

        vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = mAuth.getCurrentUser();
                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EmailVerificationActivity.this, "Please check your mail and verify your account.", Toast.LENGTH_SHORT).show();
                        // f = 1;
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(EmailVerificationActivity.this, IntroActivity.class);

                            }
                        }, 15000);
                        eBtn.setVisibility(View.VISIBLE);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EmailVerificationActivity.this, "Invalid mail id", Toast.LENGTH_SHORT).show();
                        //f = 0;
                    }
                });


            }
        });
        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EmailVerificationActivity.this, IntroActivity.class);
                startActivity(i);
            }
        });
    }
}
