package com.example.minyakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.minyakapp.Model.Data;

import java.util.ArrayList;

public class Metode extends AppCompatActivity {

    Data data;
    Button btn_straight_line
            ,btn_decline_balance
            ,btn_double_decline_balance
            ,btn_unit_of_production
            ,btn_sum_of_the_year;
    ArrayList<Integer> depresiasi = new ArrayList<>();
    ArrayList<String> income = new ArrayList<>();
    ArrayList<String> taxable_income = new ArrayList<>();
    ArrayList<String> tax = new ArrayList<>();
    ArrayList<String> ncf = new ArrayList<>();
    ArrayList<Integer> Di = new ArrayList<>();
    int totalNCF = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode);

        Bundle ex = getIntent().getExtras();
        if (ex != null){
            data = (Data) ex.getSerializable("ModelClass");
//            Toast.makeText(this,"Berhasil Passing Model",Toast.LENGTH_LONG).show();
        }

        btn_straight_line = findViewById(R.id.btn_straight_line);
        btn_decline_balance = findViewById(R.id.btn_decline_balance);
        btn_double_decline_balance = findViewById(R.id.btn_double_decline_balance);
        btn_unit_of_production = findViewById(R.id.btn_unit_of_production);
        btn_sum_of_the_year = findViewById(R.id.btn_sum_of_the_year);
        setIncome();

        btn_straight_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                straightLine();
                nextLayout();
            }
        });

        btn_decline_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineBalance();
                nextLayout();
            }
        });

        btn_double_decline_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleDeclineBalance();
                nextLayout();
            }
        });

        btn_unit_of_production.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitOfProduction();
                nextLayout();
            }
        });
        btn_sum_of_the_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumOfTheYear();
                nextLayout();
            }
        });
    }

    public void setIncome()
    {
        for (int i=1 ; i<=data.getJumlah(); i++){
            double temp = Double.parseDouble(data.getListProduksi().get(i-1)) * data.getHarga();
            income.add(String.valueOf(temp));
        }
        if (income != null){
            data.setIncome(income);
        }
//        income.clear();

    }

    public void setTaxableIncome(){
        for (int i=1 ; i<=data.getJumlah(); i++){
            int temp = (int) (Double.parseDouble(data.getIncome().get(i-1)) -  depresiasi.get(i-1) - data.getOpex());
            taxable_income.add(String.valueOf(temp));
        }
        if (taxable_income != null){
            data.setTaxable_income(taxable_income);
        }
    }

    public void setTax(){
        for (int i=1 ; i<=data.getJumlah(); i++){
            int temp = (int) (Integer.parseInt(data.getTaxable_income().get(i-1)) * (data.getPajak()/100));
            tax.add(String.valueOf(temp));
        }
        if (tax != null){
            data.setTax(tax);
        }
    }

    public void setNCFYear(){
        for (int i=1 ; i<=data.getJumlah(); i++){
            int temp = (int) (Double.parseDouble(data.getIncome().get(i-1)) -  data.getOpex() - Double.parseDouble(data.getTax().get(i-1)));
//            Log.d("NCF : ", data.getIncome().get(i-1) + "-" + data.getOpex() + "-" + data.getTax().get(i-1) + "=" + temp);
            ncf.add(String.valueOf(temp));
        }
        if (ncf != null){
            data.setNCF(ncf);
        }
    }

    public void sumNFC(){
        int temp = 0;
        for (int i=1; i<=data.getJumlah(); i++){
            temp = temp + Integer.parseInt(data.getNCF().get(i-1));
        }
        totalNCF = (int) (temp - (data.getCapital() + data.getNoncapital()));
    }

    public void nextLayout(){
        Intent intent = new Intent(Metode.this, CashFlowOutput.class);
        intent.putExtra("Hasil", totalNCF);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tax.clear();
        taxable_income.clear();
        ncf.clear();
        depresiasi.clear();
    }

    public void straightLine(){

        for (int i=1; i<=data.getJumlah();i++){
            int temp = (int) (data.getCapital() * (1/(float)data.getJumlah()));
            depresiasi.add(temp);
        }

        setTaxableIncome();
        setTax();
        setNCFYear();
        sumNFC();

//        Toast.makeText(this,String.valueOf(totalNCF),Toast.LENGTH_LONG).show();
    }

    public void declineBalance(){
        float rate = 1/(float)data.getJumlah();
        for (int i=1; i<=data.getJumlah();i++){
            float a = (float) Math.pow(1-rate,i-1);
            int temp = (int) (data.getCapital() * (rate) * a);
            depresiasi.add(temp);
        }

        setTaxableIncome();
        setTax();
        setNCFYear();
        sumNFC();

//        Toast.makeText(this,String.valueOf(depresiasi),Toast.LENGTH_LONG).show();
    }

    public void doubleDeclineBalance(){
        float rate = 1/(float)data.getJumlah();
        for (int i=1; i<=data.getJumlah();i++){
            float a = (float) Math.pow(1-(2*rate),i-1);
            int temp = (int) (data.getCapital() * (2 * rate) * a);
            depresiasi.add(temp);
        }

        setTaxableIncome();
        setTax();
        setNCFYear();
        sumNFC();

//        Toast.makeText(this,String.valueOf(depresiasi),Toast.LENGTH_LONG).show();
    }

    public void unitOfProduction(){
        for (int i=1; i<=data.getJumlah();i++){
            float rate = Float.parseFloat(data.getListProduksi().get(i-1)) / data.getTotalProduksi();
            int temp = (int) (rate * data.getCapital());
            depresiasi.add(temp);
        }

        setTaxableIncome();
        setTax();
        setNCFYear();
        sumNFC();

//        Toast.makeText(this,String.valueOf(depresiasi),Toast.LENGTH_LONG).show();
    }

    public void sumOfTheYear(){
        for (int i=1; i<=data.getJumlah();i++){

            int temp = (int) (data.getCapital() * 2 * (data.getJumlah() - (i - 1))) / (data.getJumlah() * (data.getJumlah() + 1));
            depresiasi.add(temp);
        }

        setTaxableIncome();
        setTax();
        setNCFYear();
        sumNFC();

//        Toast.makeText(this,String.valueOf(depresiasi),Toast.LENGTH_LONG).show();
    }

}
