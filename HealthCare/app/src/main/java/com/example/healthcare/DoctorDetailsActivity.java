package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private  String[][] doctor_details1=
            {
                    {"Doctor Name: Amisha Deshmukh", "Hospital Address: Pimpri", "Experience: 5yrs", "Mobile No.:9175586747", "600"},
                    {"Doctor Name: Srushti Khandare", "Hospital Address: Nigdi", "Experience: 6yrs", "Mobile No.:9275586747", "700"},
                    {"Doctor Name: Palasha Kawade", "Hospital Address: Wagholi", "Experience: 7yrs", "Mobile No.:9375586747", "800"},
                    {"Doctor Name: Vedika Kawankar", "Hospital Address: VimanNagar", "Experience: 8yrs", "Mobile No.:9475586747", "900"},
                    {"Doctor Name: Pushkar Jadhav", "Hospital Address: Magarpatta", "Experience: 10yrs", "Mobile No.:9175583747", "1000"}
            };
    private  String[][] doctor_details2=
            {
                    {"Doctor Name: Neha Gupta", "Hospital Address: Cidco", "Experience: 5yrs", "Mobile No.:9175586741", "610"},
                    {"Doctor Name: Asma Sahin", "Hospital Address: Nigdi", "Experience: 16yrs", "Mobile No.:9275586742", "710"},
                    {"Doctor Name: Sanket Parekh", "Hospital Address: Shivaji Nagar", "Experience: 2yrs", "Mobile No.:9375586747", "1800"},
                    {"Doctor Name: Ved Kawankar", "Hospital Address: Nagar", "Experience: 18yrs", "Mobile No.:9875586747", "2200"},
                    {"Doctor Name: Vilas Jadhav", "Hospital Address: Magarpatta", "Experience: 10yrs", "Mobile No.:9175583747", "1000"}
            };
    private  String[][] doctor_details3=
            {
                    {"Doctor Name: Neha Mehta", "Hospital Address: Andheri", "Experience: 1yrs", "Mobile No.:9175586737", "600"},
                    {"Doctor Name: Om Sinha", "Hospital Address: Nigdi", "Experience: 7yrs", "Mobile No.:9275586757", "7000"},
                    {"Doctor Name: Vyom Joshi", "Hospital Address: Tv Center", "Experience: 17yrs", "Mobile No.:8375586747", "800"},
                    {"Doctor Name: Sarah Kay", "Hospital Address: Town Center", "Experience: 18yrs", "Mobile No.:9075586747", "1900"},
                    {"Doctor Name: Navin Kumar", "Hospital Address: Das Colony", "Experience: 11yrs", "Mobile No.:9175583740", "1000"}
            };
    private  String[][] doctor_details4=
            {
                    {"Doctor Name: Neha Joshi", "Hospital Address: Hudco", "Experience: 9yrs", "Mobile No.:7175586749", "410"},
                    {"Doctor Name: Nirav Modi", "Hospital Address: Rajkot", "Experience: 1yrs", "Mobile No.:9212586742", "610"},
                    {"Doctor Name: Sanket Joshi", "Hospital Address: Shivaji Nagar", "Experience: 2yrs", "Mobile No.:9371186747", "1800"},
                    {"Doctor Name: Ved Joshi", "Hospital Address: Nagar", "Experience: 18yrs", "Mobile No.:9875186747", "2200"},
                    {"Doctor Name: Akash Kolay", "Hospital Address: Mg Road", "Experience: 10yrs", "Mobile No.:9175513747", "1000"}
            };
    private  String[][] doctor_details5=
            {
                    {"Doctor Name: Devesh Pawar", "Hospital Address: Andheri", "Experience: 1yrs", "Mobile No.:9175586737", "600"},
                    {"Doctor Name: Om Borade", "Hospital Address: Nigdi", "Experience: 7yrs", "Mobile No.:7275586750", "1300"},
                    {"Doctor Name: Veer Mehta", "Hospital Address: Navjot Colony", "Experience: 17yrs", "Mobile No.:7375586740", "300"},
                    {"Doctor Name: Luice Martin", "Hospital Address: Cidco", "Experience: 18yrs", "Mobile No.:8075586740", "1400"},
                    {"Doctor Name: Deepak Patel", "Hospital Address: Sadashiv Peth", "Experience: 11yrs", "Mobile No.:7175583740", "1100"}
            };

TextView tv;
Button btn;
String [][] doctor_details ={};
HashMap<String, String> item;
ArrayList list;
SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.btnLTBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if (title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dieticien")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++){
           item = new HashMap<String,String>();
           item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);



        }
        sa = new SimpleAdapter(this, list,R.layout.multi_lines,
                new String[]{"line1", "line2","line3", "line4", "line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e });
        ListView lst = findViewById(R.id.lvCL);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);

            }
        });

    }
}
