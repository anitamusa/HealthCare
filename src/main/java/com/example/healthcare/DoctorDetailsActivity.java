package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String [][] doctor_details1=
            {
                    {"Doctor Name: Gzim Abrashi"," Hospital Address: Prishtine","Exp: 8 yrs","Mobile:+38349825654"},
                    {"Doctor Name: Vlora Begu"," Hospital Address: Mitrovice","Exp: 11 yrs","Mobile:+38349851409"},
                    {"Doctor Name: Anita Musa"," Hospital Address: Peje","Exp: 5 yrs","Mobile:+38349861857"},
                    {"Doctor Name: Artina Azemi"," Hospital Address: Vushtrri","Exp: 5 yrs","Mobile:+38345678244"},
                    {"Doctor Name: Halim Braha"," Hospital Address: Ferizaj","Exp: 20 yrs","Mobile:+38344681734"},
            };
    private String [][] doctor_details2=
            {
                    {"Doctor Name: Valbone Musa"," Hospital Address: Prishtine","Exp: 8 yrs","Mobile:+38349825654"},
                    {"Doctor Name: Besime Azemi"," Hospital Address: Mitrovice","Exp: 11 yrs","Mobile:+38349851409"},
                    {"Doctor Name: Vesat Musa"," Hospital Address: Gjilane","Exp: 5 yrs","Mobile:+38349861857"},
                    {"Doctor Name: Muharrem Uka"," Hospital Address: Vushtrri","Exp: 5 yrs","Mobile:+38345678244"},
                    {"Doctor Name: Vlera Mehmeti"," Hospital Address: Ferizaj","Exp: 20 yrs","Mobile:+38344681734"},
            };
    private String [][] doctor_details3=
            {
                    {"Doctor Name: Halide Feka"," Hospital Address: Prishtine","Exp: 8 yrs","Mobile:+38349825654"},
                    {"Doctor Name: Besarta Neziri"," Hospital Address: Mitrovice","Exp: 11 yrs","Mobile:+38349851409"},
                    {"Doctor Name: Adem Gjosha"," Hospital Address: Gjilane","Exp: 5 yrs","Mobile:+38349861857"},
                    {"Doctor Name: Hasan Qazimi"," Hospital Address: Vushtrri","Exp: 5 yrs","Mobile:+38345678244"},
                    {"Doctor Name: Edon Zeka"," Hospital Address: Ferizaj","Exp: 20 yrs","Mobile:+38344681734"},
            };
    private String [][] doctor_details4=
            {
                    {"Doctor Name: Fatmir Azemi"," Hospital Address: Prishtine","Exp: 8 yrs","Mobile:+38349825654"},
                    {"Doctor Name: Lindita Abazi"," Hospital Address: Mitrovice","Exp: 11 yrs","Mobile:+38349851409"},
                    {"Doctor Name: Dijane Berisha"," Hospital Address: Gjilane","Exp: 5 yrs","Mobile:+38349861857"},
                    {"Doctor Name: Bleranda Hyseni"," Hospital Address: Vushtrri","Exp: 5 yrs","Mobile:+38345678244"},
                    {"Doctor Name: Arbnora Karaqa"," Hospital Address: Ferizaj","Exp: 20 yrs","Mobile:+38344681734"},
            };
    private String [][] doctor_details5=
            {
                    {"Doctor Name: Verone Verbovci"," Hospital Address: Prishtine","Exp: 8 yrs","Mobile:+38349825654"},
                    {"Doctor Name: Fatjon Neziri"," Hospital Address: Mitrovice","Exp: 11 yrs","Mobile:+38349851409"},
                    {"Doctor Name: Altin Halimi"," Hospital Address: Gjilane","Exp: 5 yrs","Mobile:+38349861857"},
                    {"Doctor Name: Diellza Ibishi"," Hospital Address: Vushtrri","Exp: 5 yrs","Mobile:+38345678244"},
                    {"Doctor Name: Mergime Ismeti"," Hospital Address: Ferizaj","Exp: 20 yrs","Mobile:+38344681734"},
            };

    private String[][] doctor_details = {};
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        TextView tv = findViewById(R.id.textViewDDTitle);
        Button btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();

        String title = it.getStringExtra("title");
        if (title == null) title = "Default Title";

        tv.setText(title);

        switch (title) {
            case "Family Physicians":
                doctor_details = doctor_details1;
                break;
            case "Dietician":
                doctor_details = doctor_details2;
                break;
            case "Surgeon":
                doctor_details = doctor_details3;
                break;
            case "Dentists":
                doctor_details = doctor_details4;
                break;
            case "CardioLogists":
                doctor_details = doctor_details5;
                break;

            default:
                doctor_details = new String[][]{};
                break;
        }

        list = new ArrayList<>();
        for (String[] doctor : doctor_details) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", doctor[0]);
            item.put("line2", doctor[1]);
            item.put("line3", doctor[2]);
            item.put("line4", doctor[3]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d});

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        btn.setOnClickListener(v -> finish());
        lst.setOnClickListener(new AdapterView.OnItemClickListener()
             {
                 @Override
                 public void onItemClick(AdapterView<?>adapterView,View view,int i,long l)
                 {
                     Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentAcitvity.class);
                     it.putExtra("text1", title);
                     it.putExtra("text2",doctor_details[i][0]);
                     it.putExtra("text3",doctor_details[i][1]);
                     it.putExtra("text5",doctor_details[i][3]);
                     it.putExtra("text6",doctor_details[i][4]);
                     startActivity(it);
                 }

             }

        );
    }
}
