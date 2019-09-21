package com.example.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import modelos.Modelo;

public class ListAdapter extends BaseAdapter {

 protected Activity activity;
 private ArrayList<Modelo> lst;

    public ListAdapter(Activity activity, ArrayList<Modelo> lst) {
        this.activity = activity;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v==null){
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.item_row,null);
        }
        Modelo store = lst.get(position);

        TextView lblNombre, lblDescrip;
        lblNombre = v.findViewById(R.id.txtNameItemStorage);
        lblDescrip = v.findViewById(R.id.txtDescripStorage);

        lblNombre.setText(store.getNombre());
        lblDescrip.setText(store.getDescripcion());


        return v;
    }
}
