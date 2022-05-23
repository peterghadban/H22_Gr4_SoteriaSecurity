package com.example.soteriasecurityapp;

import android.content.Context;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity extends AppCompatActivity {

    public  ArrayAdapter adapter;
    public  ArrayList<String> entries;
    public  ArrayList<DataObj> data;
    public  ListView lv_historique;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        lv_historique = findViewById(R.id.lv_historique); //Lie le listview de la class à celui du xml
        entries = new ArrayList<>();
        entries.add("Format AAAA/MM/JJ- Heure:Min"); //Permet de mieux comprendre le format du listview
        data = MainActivity.historique; //Forme une copie de l'historique pour l'afficher en listView

        //Ce loop recueille la date et l'heure de chaque enclenchement de l'alarme pour l'afficher
        for (int k = 0; k < data.size(); k++) {
            //  System.out.println(data.get(k).getDate());
            //  System.out.println(data.get(k).getHeure());
            entries.add(data.get(k).getDate() + " à " + data.get(k).getHeure());
        }


        // Le adapter permet de transformaer le ArrayList en ListView
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, entries);
        lv_historique.setAdapter(adapter);
    }




}