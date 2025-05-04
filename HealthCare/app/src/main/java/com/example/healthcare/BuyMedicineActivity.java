package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private  String[][] packages =
            {
                    {"Uprise-D3 1000Iu Capsule", "", "", "","50"},
                    {"Healthvit Chromium Picolimate 200mcg Capsule", "", "", "","305"},
                    {"Vitamin B Complex Capsule", "", "", "","440"},
                    {"Inline vitamin E Wheat germ Oil Capsule", "", "", "","539"},
                    {"Dolo 650 Tablet", "", "", "","50"},
                    {"Crocin 650 Advance Tablet", "", "", "","50"},
                    {"Strepsils Medicated Lozengens for Sore Throat", "", "", "","40"},
                    {"Tata 1mg Calcium + Vitamin d3", "", "", "","50"},
                    {"Feronin", "", "", "","130"}
            };
    private  String[] package_details ={
            "Building and keeping bones and teeth strong\n"+
                    "Reducing fatigue/ strss and mascular pain\n"+
                    "Boosting immunity and increasing resistance against infection",

            "Helps regulate blood sugar levels\n"+
            "Promotes healthy metabolism\n"+
            "Supports weight management",

            "Supports energy production\n"+
            "Promotes healthy skin, hair, and nails\n"+
            "Aids in the function of the nervous system",

            "Powerful antioxidant properties\n"+
            "Supports skin health and wound healing\n"+
            "May help reduce risk of chronic diseases",

            "Relieves pain and fever\n"+
            "Effective for headache and body ache\n"+
            "Safe and well-tolerated",

            "Effective pain relief\n"+
            "Reduces fever quickly\n"+
            "Safe for adults and children",

            "Soothes sore throat and irritation\n"+
            "Provides relief from cough\n"+
            "Contains antibacterial ingredients",

            "Supports bone health and strength\n"+
            "Promotes proper calcium absorption\n"+
            "May reduce risk of osteoporosis",

            "Helps treat iron deficiency anemia\n"+
            "Restores iron levels in the body\n"+
            "Improves overall energy and vitality"
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnback, btngotocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
lst = findViewById(R.id.lvBM);
btnback= findViewById(R.id.btnBMBack);
btngotocart= findViewById(R.id.btnBMcart);


     btnback.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
         }
     });
     btngotocart.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
         }
     });
        list = new ArrayList();
        for (int i =0; i<packages.length; i++ ) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:"+ packages[i][4]+"/-");
            list.add( item );
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2","line3", "line4", "line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e });
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);

                startActivity(it);

            }
        });
    }
}