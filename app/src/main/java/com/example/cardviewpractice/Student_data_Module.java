package com.example.cardviewpractice;

public class Student_data_Module {
        String date;
        String manzil;
        String sabaq;
        String sabqi;

        public Student_data_Module(String date, String manzil, String sabaq, String sabqi) {
            this.date = date;
            this.manzil = manzil;
            this.sabaq = sabaq;
            this.sabqi = sabqi;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getManzil() {
            return manzil;
        }

        public void setManzil(String manzil) {
            this.manzil = manzil;
        }

        public String getSabaq() {
            return sabaq;
        }

        public void setSabaq(String sabaq) {
            this.sabaq = sabaq;
        }

        public String getSabqi() {
            return sabqi;
        }

        public void setSabqi(String sabqi) {
            this.sabqi = sabqi;
        }


}
