package com.example.minyakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minyakapp.Model.Data;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Button btn_submit;
    EditText jumlah_tahun;
    int jum_thn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submit = findViewById(R.id.btn_submit1);
        jumlah_tahun = findViewById(R.id.jumlah_tahun);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jum_thn = Integer.parseInt(jumlah_tahun.getText().toString());
                Data model = new Data();
                model.setJumlah(jum_thn);

                Intent intent = new Intent(MainActivity.this, Produksi.class);

                intent.putExtra("ModelClass", model);
//                intent.putExtra("jumlah",jum_thn);
                startActivity(intent);
            }
        });
    }
}
