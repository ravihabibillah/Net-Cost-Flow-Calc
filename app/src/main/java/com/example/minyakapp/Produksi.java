package com.example.minyakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minyakapp.Model.Data;

import java.util.ArrayList;

public class Produksi extends AppCompatActivity {

    int jumlah = 0;
    int i;
    Data data;
    ArrayList<String> produksiPertahun = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produksi);

//        Intent intent = getIntent();
        Bundle ex = getIntent().getExtras();
        if (ex != null){
//            jumlah = ex.getInt("jumlah");
            data = (Data) ex.getSerializable("ModelClass");
            jumlah = data.getJumlah();
//            Toast.makeText(this,String.valueOf(jumlah),Toast.LENGTH_LONG).show();

        }
//        jumlah = Integer.parseInt(ex.getString("jumlah"));
        LinearLayout linearLayout = findViewById(R.id.layout_produksi);

        for (i = 1; i <= jumlah; i++){
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(p);
            tv.setTextColor(getResources().getColor(android.R.color.black));
            tv.setText("Produksi Tahun ke-" + i + " (Mbbl)");
            linearLayout.addView(tv);

            EditText et = new EditText(this);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(p2);
            et.setGravity(Gravity.CENTER);
            et.setId(i);
            et.setInputType(InputType.TYPE_CLASS_NUMBER);
            linearLayout.addView(et);

        }

        Button btn_next = new Button(this);
        LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btn_next.setLayoutParams(p3);
        btn_next.setText("Submit");
        btn_next.setId(i+1);
        btn_next.setBackgroundColor(Color.parseColor("#da3932"));
        btn_next.setTextColor(getResources().getColor(android.R.color.white));
        linearLayout.addView(btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=1; i<=jumlah; i++){
//                    String id = "R.id." + i;
                    EditText et = findViewById(i);
                    produksiPertahun.add(et.getText().toString());

                }
//                Toast.makeText(Produksi.this,produksiPertahun.get(0),Toast.LENGTH_LONG).show();
                data.setListProduksi(produksiPertahun);
                Intent intent = new Intent(Produksi.this, InputDetail.class);
                intent.putExtra("ModelClass", data);
                startActivity(intent);
            }
        });
    }
}
