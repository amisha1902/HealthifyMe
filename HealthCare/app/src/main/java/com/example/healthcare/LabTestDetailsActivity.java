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

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnback, btnaddtocart;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackageName = findViewById(R.id.tvLTD);
        tvTotalCost= findViewById(R.id.tvtotalcostLTD);
        edDetails = findViewById(R.id.editTextTextMultiLine);
        btnback = findViewById(R.id.btnLTDBack);
        btnaddtocart = findViewById(R.id.btnLTDcart);

        edDetails.setKeyListener(null);

        Intent intent = getIntent();

        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost:"+intent.getStringExtra("text3")+"/-");
         btnback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class ));
             }
         });
         btnaddtocart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SharedPreferences sharedpreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                 String username = sharedpreferences.getString("username", "").toString();
                 String product = tvPackageName.getText().toString();
                 float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                 Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                 if(db.checkCart(username, product)==1){
                     Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();

                 }else {
                     db.addCart(username, product, price, "lab");
                     Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(LabTestDetailsActivity.this, CartLabActivity.class));
                 }
             }
         });
    }
}