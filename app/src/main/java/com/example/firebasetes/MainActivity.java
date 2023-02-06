package com.example.firebasetes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;
    private FirebaseAuth mAuth;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        txt=(TextView) findViewById(R.id.textView2);
        editText=(EditText) findViewById(R.id.editTextTextPersonName);
        btn.setOnClickListener(view -> Toast.makeText(getApplicationContext(),"Hello "+editText.getText().toString(),Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.Logout:
                Toast.makeText(MainActivity.this,"User logout",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i=new Intent(MainActivity.this,LogInActivity.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }





    }
}