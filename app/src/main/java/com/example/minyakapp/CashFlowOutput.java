package com.example.minyakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minyakapp.Model.Data;

public class CashFlowOutput extends AppCompatActivity {

    TextView tv_hasil;
    int hasil = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_flow_output);

        tv_hasil = findViewById(R.id.tv_hasil);

        Bundle ex = getIntent().getExtras();
        if (ex != null){
            hasil =  ex.getInt("Hasil");
//            Toast.makeText(this,"Berhasil Passing Hasil",Toast.LENGTH_LONG).show();
        }

        if (hasil > 0){
            tv_hasil.setText("+" + String.valueOf(hasil));
        } if (hasil < 0){
            tv_hasil.setText(String.valueOf(hasil));

        }


    }
}
