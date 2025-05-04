package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
EditText edusername, edemail, edpassword, edconfirmpass;
Button btnregister;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edusername = findViewById(R.id.edAppUsername);
        edemail= findViewById(R.id.edAppemail);
        edpassword = findViewById(R.id.edApppassword);
        edconfirmpass= findViewById(R.id.edAppconfirmpass);
        btnregister = findViewById(R.id.btnregister);
        tv = findViewById(R.id.tvalreadyauser);
                    tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            });
                    btnregister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           String username = edusername.getText().toString();
                           String email = edemail.getText().toString();
                           String password = edpassword.getText().toString();
                           String confirm = edconfirmpass.getText().toString();
                           Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                            if(username.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0){
                               Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                           }
                            else {
                                if (password.compareTo(confirm)==0){
                                    if (isValid(password)){
                                       db.register(username, email,password);
                                        Toast.makeText(getApplicationContext(), "Record Inserted successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Password must consist atleast 8 character having a letter, digit, and special character", Toast.LENGTH_SHORT).show();
                                }}
                                else{
                                    Toast.makeText(getApplicationContext(), " Password do not match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });}
    public static boolean isValid(String passwordhere){
        int f1=0, f2=0, f3=0;
        if (passwordhere.length() < 8){
            return false;
        }
        else {
            for(int p = 0; p < passwordhere.length(); p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r =0; r< passwordhere.length(); r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s =0; s<passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if (c>=33&&c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}