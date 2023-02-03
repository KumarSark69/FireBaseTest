package com.example.firebasetes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.button);
        txt=(TextView) findViewById(R.id.textView2);
        editText=(EditText) findViewById(R.id.editTextTextPersonName);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hello "+editText.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}