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
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt,passwordEdt;
    private Button loginBtn;
    private ProgressBar loadingPB;
    private TextView registerTV;
    private FirebaseAuth firebaseAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        userNameEdt=findViewById(R.id.idEdtUserName);
        passwordEdt=findViewById(R.id.idEdtPassword);
        loadingPB=findViewById(R.id.idPBLoading);
        loginBtn=findViewById(R.id.idBtnLogin);
        registerTV=findViewById(R.id.idTVLogin);
        firebaseAuth=FirebaseAuth.getInstance();

//        registerTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(LogInActivity.this,RegistrationActivity.class);
//                startActivity(i);
//            }
//        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=userNameEdt.getText().toString();
                String password=passwordEdt.getText().toString();
                loadingPB.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {

                    Toast.makeText(LogInActivity.this,"Please fill the credential properly",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    firebaseAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(LogInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(LogInActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(LogInActivity.this,"Fail to login",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    public void hello(){
        Intent i=new Intent(LogInActivity.this,RegistrationActivity.class);
        startActivity(i);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            Intent i=new Intent(LogInActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}