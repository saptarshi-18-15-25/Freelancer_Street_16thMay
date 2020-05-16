package com.divij.freelancerstreet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;

public class RegistrationActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Button mRegister,fBtn,sBtn;
    private ImageButton vBtn;
    private EditText mEmail,mPassword,mName,mLinkedin,mDescription,mSkills;
    private RadioGroup mRadioGroup;
    private RadioButton radioButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private TextView mSignin, LinText,skillText,nmText,sText,oText;
    private String vemail, vpassword;
    private int f = 0;
    private Timer timer;
    private ProgressDialog progressDialog;
    LinearLayout formR;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        mSignin=findViewById(R.id.signin);
        fBtn = findViewById(R.id.freelanceBtn);
        sBtn = findViewById(R.id.startupBtn);
       // sText = findViewById(R.id.selectText);
       // oText = findViewById(R.id.obText);
        LinText = findViewById(R.id.LinkedinText);
        skillText = findViewById(R.id.skillsText);
        nmText = findViewById(R.id.nText);
        formR = findViewById(R.id.form);
        // vBtn = findViewById(R.id.vEBtn);
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LinText.setText("LinkedIN Profile :");
               radioButton = findViewById(R.id.freelance);
               //sText.setVisibility(View.INVISIBLE);
              // oText.setVisibility(View.INVISIBLE);
               sBtn.setVisibility(View.INVISIBLE);
               skillText.setText("Skills");
               mRadioGroup.setVisibility(View.INVISIBLE);
               mDescription.setHint("Previous Work Experience And Projects(Not more than 50 words)\"");
               nmText.setText("Your Name");
               fBtn.setText("My Details");
               formR.setVisibility(View.VISIBLE);
              // sBtn.setVisibility(View.INVISIBLE);

            }
        });
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinText.setText("Website Link :");
                radioButton = findViewById(R.id.startup);
                //sText.setVisibility(View.INVISIBLE);
               // oText.setVisibility(View.INVISIBLE);
                sBtn.setVisibility(View.INVISIBLE);
                skillText.setText("Skills Required");
                mRadioGroup.setVisibility(View.INVISIBLE);
                fBtn.setText("Organisation Details");
                mDescription.setHint("About Company(Not more than 50 words)");
                nmText.setText("Company Name");
                formR.setVisibility(View.VISIBLE);
               // fBtn.setVisibility(View.INVISIBLE);
            }
        });
        firebaseAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    Intent intent = new Intent(RegistrationActivity.this, EmailVerificationActivity.class);
                    intent.putExtra("email", vemail);
                    intent.putExtra("password", vpassword);
                    startActivity(intent);
                    finish();

                }

            }
        };

        mRegister=findViewById(R.id.register);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.password);
        mName=findViewById(R.id.name);
        mRadioGroup=findViewById(R.id.radioGroup);
        mSkills=findViewById(R.id.Skills);
        mDescription= findViewById(R.id.Description);
        mLinkedin=findViewById(R.id.Linkedin);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // int selectId = mRadioGroup.getCheckedRadioButtonId();
                //final RadioButton radioButton = findViewById(selectId);
                //if(radioButton.getText()==null)
                //{
                  //  return;
                //}

                final String email=mEmail.getText().toString();
                final String password=mPassword.getText().toString();
                final String name=mName.getText().toString();
                final String linkedin=mLinkedin.getText().toString();
                final String description=mDescription.getText().toString();
                final String skills=mSkills.getText().toString();
                vemail = email;
                vpassword = password;

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(!task.isSuccessful()){
                         Toast.makeText(RegistrationActivity.this,"Sign up error",Toast.LENGTH_SHORT).show();
                     }

                         String userId= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                         DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                       /* FirebaseUser user = mAuth.getCurrentUser();
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegistrationActivity.this, "Check your mail", Toast.LENGTH_SHORT).show();
                                f = 1;
                                timer = new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);

                                    }
                                }, 15000);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistrationActivity.this, "Invalid mail id", Toast.LENGTH_SHORT).show();
                                f = 0;
                            }
                        });*/
                         Map<String, Object> userInfo = new HashMap<String, Object>();
                         userInfo.put("name",name);
                         userInfo.put("LinkedIn",linkedin);
                         userInfo.put("Description",description);
                         userInfo.put("Skills",skills);
                         userInfo.put("Identity",radioButton.getText().toString());
                         userInfo.put("profileImageUrl","default");

                         currentUserDb.updateChildren(userInfo);
                     }

                });
            }
        });


      /*  vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.createUserWithEmailAndPassword(vemail,vpassword);
                FirebaseUser user = mAuth.getCurrentUser();
                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegistrationActivity.this, "Check your mail", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrationActivity.this, "Invalid mail id", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }); */

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.freelance :
                LinText.setText("Website Link :");
                break;
        }
    }
}
