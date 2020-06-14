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

public class InputDetail extends AppCompatActivity {

    Data data;
    EditText etCapital, etNonCapital, etOpex, etPajak, etHarga;
    Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_detail);

        etCapital = findViewById(R.id.etCapital);
        etNonCapital = findViewById(R.id.etNonCapital);
        etOpex = findViewById(R.id.etOpex);
        etPajak = findViewById(R.id.etPajak);
        etHarga = findViewById(R.id.etHarga);
        btn_next = findViewById(R.id.btn_next_method);


        Bundle ex = getIntent().getExtras();
        if (ex != null){
            data = (Data) ex.getSerializable("ModelClass");
//            Toast.makeText(this,"Berhasil Passing Model",Toast.LENGTH_LONG).show();
        }

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setCapital(Double.parseDouble(etCapital.getText().toString()));
                data.setNoncapital(Double.parseDouble(etNonCapital.getText().toString()));
                data.setOpex(Float.parseFloat(etOpex.getText().toString()));
                data.setPajak(Float.parseFloat(etPajak.getText().toString()));
                data.setHarga(Double.parseDouble(etHarga.getText().toString()));

//                Log.d("Capital :", String.valueOf(data.getCapital()));
//                Log.d("Non Capital :", String.valueOf(data.getNoncapital()));
//                Log.d("Opex :", String.valueOf(data.getOpex()));
//                Log.d("Pajak :", String.valueOf(data.getPajak()));
//                Log.d("Harga :", String.valueOf(data.getHarga()));
                Intent intent = new Intent(InputDetail.this, Metode.class);
                intent.putExtra("ModelClass", data);
                startActivity(intent);
            }
        });

    }
}
