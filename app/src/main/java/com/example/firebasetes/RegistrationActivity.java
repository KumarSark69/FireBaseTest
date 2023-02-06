package com.example.firebasetes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt,passwordEdt,cnfpwdEdt;
    private Button registerBtn;
    private ProgressBar progressBar;
    TextView loginTV;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);




        userNameEdt=findViewById(R.id.idEdtUserName);
        passwordEdt=findViewById(R.id.idEdtPassword);
        cnfpwdEdt=findViewById(R.id.idEdtConfirmPwd);
        registerBtn=findViewById(R.id.idBtnRegister);
        loginTV=findViewById(R.id.idTVLogin);
        progressBar=findViewById(R.id.idPBLoading);
        mAuth=FirebaseAuth.getInstance();


//        loginTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(RegistrationActivity.this,LogInActivity.class);
//                startActivity(i);
//            }
//        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String userName=userNameEdt.getText().toString();
                String password=passwordEdt.getText().toString();
                String cnfPassword=cnfpwdEdt.getText().toString();

                if(password!=cnfPassword){
                    Toast.makeText(RegistrationActivity.this,"Password and Confirm password doesnot match",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userName)&&TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistrationActivity.this,"Please enter valid credential",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 progressBar.setVisibility(View.GONE);
                                 Toast.makeText(RegistrationActivity.this,"Registered Complete..",Toast.LENGTH_SHORT).show();
                                 Intent i=new Intent(RegistrationActivity.this,LogInActivity.class);
                                 startActivity(i);
                                 finish();
                             }
                             else {
                             progressBar.setVisibility(View.GONE);
                             Toast.makeText(getApplicationContext(),"Failed to register..",Toast.LENGTH_SHORT).show();
                             }
                        }
                    });
                }
            }
        });


    }
    public void hi(){
        Intent i=new Intent(RegistrationActivity.this,LogInActivity.class);
        startActivity(i);
    }
}