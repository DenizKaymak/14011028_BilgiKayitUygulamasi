package com.example.Odev2_14011028;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Classinfo extends AppCompatActivity {

    private static TextView setDersadi;
    private static TextView setOgrencisayisi;
    private static TextView setOrtalama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classinfo);
        Bundle bundle = getIntent().getExtras();
        setDersadi=(TextView)findViewById(R.id.textDersadi);
        setOgrencisayisi=(TextView)findViewById(R.id.textOgrencisayisi);
        setOrtalama=(TextView)findViewById(R.id.textOrtalama);

        setDersadi.setText("Ders Adi: Ders "+bundle.getString("listnum"));
        setOgrencisayisi.setText("Ogrenci Sayisi: "+bundle.getString("listnum"));
        setOrtalama.setText("Ortalama: "+bundle.getString("listnum"));

    }
}
