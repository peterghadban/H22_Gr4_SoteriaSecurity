package com.example.soteriasecurityapp;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public FirebaseDatabase database;
    public DatabaseReference reference;
    public DataObj data;
    public int id = 0, nbOfMotions = 0;
    public static ArrayList<DataObj> historique;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        historique = new ArrayList<>();


        reference = database.getReference();

        reference.child("motion").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            /**
             * OnComplete est exécutée lorsqu'on ouvre la
             * page MainActivity et recueille toutes les données nécessaires
             */
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();


                if (snapshot.exists()) {
                    String str, date, heure;

                    String[] tabSplit;

                    id = (int) snapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        str = dataSnapshot.toString().substring(52);

                        // str = dataSnapshot.toString();
                        tabSplit = str.split("-");
                        System.out.println(snapshot);
                        for (int k = 0; k < tabSplit.length; k++) {
                            System.out.println(tabSplit[k] + k);
                        }


                        date = tabSplit[0] + "-" + tabSplit[1] + "-" +
                                tabSplit[2].charAt(0) + tabSplit[2].charAt(1);

                        heure = str.substring(11, 16);

                        Date dateMtn = new Date();

                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                        try {
                            dateMtn = formatter.parse(heure);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateMtn);
                        cal.add(Calendar.HOUR_OF_DAY, 20);
                        cal.add(Calendar.MINUTE, -9);
                        heure = cal.getTime().toString().substring(11,16);


                        data = new DataObj(date, heure, "fe");

                        historique.add(data);
                    }
                }


            }
        });

        reference.child("motion").addValueEventListener(new ValueEventListener() {
            @Override
            /**
             * Cette méthode est exécutée dès qu'il y a un changement dans la base de données
             * Ce qui nous permet de mettre à jour notre historique et notre graphique
             */
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nbOfMotions = Integer.parseInt(String.valueOf(snapshot.getChildrenCount()));

                reference.child("num").addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot1) {

                        int x = 0, i = 0;
                        if (snapshot1.toString().length() < 46) {
                            x = Integer.parseInt(String.valueOf(snapshot1.toString().charAt(41)));
                        } else {
                            x = (int) (Integer.parseInt(String.valueOf(snapshot1.toString().charAt(41)))
                                    * Math.pow(10, 1) +
                                    Integer.parseInt(String.valueOf(snapshot1.toString().charAt(42))));
                        }

                        if (x < nbOfMotions) {
                            String str, date, heure;

                            String[] tabSplit;
                            System.out.println(snapshot.getChildrenCount());
                            id = (int) snapshot.getChildrenCount();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                str = dataSnapshot.toString().substring(52);
                                tabSplit = str.split("-"); //On sépare ce qu'on recoit de FireBase selon "-"

                                date = tabSplit[0] + "-" + tabSplit[1] + "-" +
                                        tabSplit[2].charAt(0) + tabSplit[2].charAt(1);
                                //On receuille l'année le mois et la date

                                heure = str.substring(11, 16); //On recueille les charactères 11 à 15 pour l'heure


/*
//https://stackoverflow.com/questions/759036
/how-to-convert-string-into-time-format-and-add-two-hours

 */

                                Date dateMtn = new Date();

                                @SuppressLint("SimpleDateFormat")
                                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                                try {
                                    dateMtn = formatter.parse(heure);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(dateMtn);
                                cal.add(Calendar.HOUR_OF_DAY, 20);
                                cal.add(Calendar.MINUTE, -9);
                                heure = cal.getTime().toString().substring(11,16);



                                if (i == snapshot.getChildrenCount() - 1) {

                                    data = new DataObj(date, heure, "fe");
                                    historique.add(data);
                                    System.out.println("i: " + i);
                                    System.out.println("size arraylist: " + historique.size());
                                }


                                notification();
                                notifyThis();
                                reference.child("num").child("32rrw").setValue(nbOfMotions);

                                System.out.println("...");


                                i++;

                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                int i = 0;

                if (snapshot.exists()) {
                    String str, date, heure;

                    String[] tabSplit;
                    System.out.println(snapshot.getChildrenCount());
                    id = (int) snapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        str = dataSnapshot.toString();
                        tabSplit = str.split("-");

                        date = tabSplit[2] + "-" + tabSplit[3] + "-" +
                                tabSplit[4].charAt(0) + tabSplit[4].charAt(1);

                        heure = str.substring(50 + 12, 56 + 12);


                        data = new DataObj(date, heure, "fe"); //Ajoute la nouvelle entrée à l'historique

                        i++;
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activity = findViewById(R.id.Activity);
        Button charts = findViewById(R.id.Charts);
        Button settings = findViewById(R.id.Settings);
        Button account = findViewById(R.id.Account);
        Button logout = findViewById(R.id.Logout);

        mAuth = FirebaseAuth.getInstance();


        logout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });


        activity.setOnClickListener(view -> {
            openActivity_1(view);
        });

        settings.setOnClickListener(view -> {
            openAbout(view);
        });

        charts.setOnClickListener(view -> {
            openCharts(view);
        });

        account.setOnClickListener(view -> {
            openAccount(view);
        });

    }

    /**
     * Cette méthode ouvre la page Historique
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openActivity_1(View view) {

        Intent intent = new Intent(this, Activity.class);
        startActivity(intent, ActivityOptions.makeClipRevealAnimation(view,0,0,1,1).toBundle());
    }

    /**
     * Cette méthode ouvre la page À propos de nous
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openAbout(View view) {

        Intent intent = new Intent(this, About.class);
        startActivity(intent, ActivityOptions.makeClipRevealAnimation(view,0,0,1,1).toBundle());
    }


    /**
     * Cette méthode permet de passer à la page du graphique
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openCharts(View view) {

        Intent intent = new Intent(this, Charts.class);
        startActivity(intent, ActivityOptions.makeClipRevealAnimation(view,0,0,1,1).toBundle());
    }


    /**
     * Cette méthode permet de passer à la page du compte de l'utilisateur
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openAccount(View view) {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent, ActivityOptions.makeClipRevealAnimation(view,0,0,1,1).toBundle());
    }


    /**
     * Cette méthode crée le Channel pour envoyer la notification
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", "name", importance);
            channel.setDescription("description");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }


    }

    /**
     * Cette méthode envoie la notificaton
     */
    public void notifyThis() {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Mouvement détecté!")
                .setContentText("Alarme activée le " +
                        historique.get(historique.size() - 1).getDate() + " à  " +
                        historique.get(historique.size() - 1).getHeure())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setColor(Color.BLACK);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        notificationManager.notify(0, mBuilder.build());
    }

}











