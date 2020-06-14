package com.example.minyakapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    int jumlah;
    ArrayList<String> listProduksi;
    ArrayList<String> income;
    ArrayList<String> taxable_income;
    ArrayList<String> tax;
    ArrayList<String> NCF;
    double capital, noncapital;
    double opex;
    float pajak;
    double harga;
    int totalProduksi;

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public ArrayList<String> getListProduksi() {
        return listProduksi;
    }


    public void setListProduksi(ArrayList<String> listProduksi) {
        this.listProduksi = listProduksi;
    }

    public int getTotalProduksi(){
        totalProduksi = 0;
        for (int i = 0; i < getJumlah() ; i++){
            totalProduksi = totalProduksi + Integer.parseInt(getListProduksi().get(i));
        }

        return totalProduksi;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getNoncapital() {
        return noncapital;
    }

    public void setNoncapital(double noncapital) {
        this.noncapital = noncapital;
    }

    public double getOpex() {
        return opex;
    }

    public void setOpex(double opex) {
        this.opex = opex;
    }

    public float getPajak() {
        return pajak;
    }

    public void setPajak(float pajak) {
        this.pajak = pajak;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public ArrayList<String> getIncome() {
        return income;
    }

    public void setIncome(ArrayList<String> income) {
        this.income = income;
    }

    public ArrayList<String> getTaxable_income() {
        return taxable_income;
    }

    public void setTaxable_income(ArrayList<String> taxable_income) {
        this.taxable_income = taxable_income;
    }

    public ArrayList<String> getTax() {
        return tax;
    }

    public void setTax(ArrayList<String> tax) {
        this.tax = tax;
    }

    public ArrayList<String> getNCF() {
        return NCF;
    }

    public void setNCF(ArrayList<String> NCF) {
        this.NCF = NCF;
    }
}
