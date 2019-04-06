package com.example.Odev2_14011028;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recycle extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> list;
    MyAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();


        for(int i=0; i<50; i++){
            String ders="Ders adi: Ders "+ Integer.toString(i)+" Puan: "+Integer.toString(i);
            list.add(ders);
        }

        adapter=new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

    }
}
