package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText edusername, edpassword;
Button btnlogin;
TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edusername = findViewById(R.id.etusername);
        edpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btlogin);
        tv = findViewById(R.id.tvregister);
btnlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String username = edusername.getText().toString();
        String password= edpassword.getText().toString();
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        if(username.length()==0 || password.length()==0){
            Toast.makeText(getApplicationContext(), "Enter valid credentials", Toast.LENGTH_SHORT).show();

        }
        else {
            if (db.login(username, password)==1) {
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedprefrences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedprefrences.edit();
                editor.putString("username", username);
                editor.apply();

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid user name and password", Toast.LENGTH_SHORT).show();

            }
        }
    }
});
tv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
});
    }
}