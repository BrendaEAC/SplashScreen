package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import modelos.Modelo;

public class MenuActivity extends AppCompatActivity {

     ListView lst;
     ArrayList<Modelo> arrayList;
     ListAdapter listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lst = findViewById(R.id.lstTiendas);

        arrayList = new ArrayList<Modelo>();

        arrayList.add(new Modelo("tiendaUno", "descrip1"));
        arrayList.add(new Modelo("tiendDoso", "descrip2"));


        listadapter = new ListAdapter(this, arrayList);
        lst.setAdapter(listadapter);




    }


}
