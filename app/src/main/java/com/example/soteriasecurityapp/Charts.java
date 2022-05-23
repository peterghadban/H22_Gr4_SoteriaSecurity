package com.example.soteriasecurityapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class Charts extends AppCompatActivity {

    TextView tvQuarter1, tvQuarter2, tvQuarter3, tvQuarter4;
    PieChart pieChart;

    //source: https://www.geeksforgeeks.org/how-to-add-a-pie-chart-into-an-android-application/

    @Override
    /**
     * Création de l'interface pour la page contenant les tableaux
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        tvQuarter1 = findViewById(R.id.tvquarter1);
        tvQuarter2 = findViewById(R.id.tvquarter2);
        tvQuarter3 = findViewById(R.id.tvquarter3);
        tvQuarter4 = findViewById(R.id.tvquarter4);//division du tableau en quatre parties différentes(les 4 quarts de la journées)
        pieChart = findViewById(R.id.piechart);

        setData();

    }

    /**
     * Méthode qui s'occupe de la gestion du data obtenus par le raspberry Pi pour mettre à jour les données du Tableau
     */
    private void setData() {

        ArrayList<DataObj> dataObjs = MainActivity.historique;//arrayList contenant les informations perçus par le Raspberry Pi et envoyer à notre class historique
        int quarter1 = 0,quarter2 = 0, quarter3 = 0, quarter4 = 0;

        for (DataObj obj : dataObjs){

            int quarter = obj.getQuarterOfDay();

            switch(quarter){//selon la methode getQuarterOfDay, on augmente de 1 la variable int correspondant au quart de la journée pour pouvoir l'afficher par la suite

                case 1 : quarter1++;
                    break;

                case 2: quarter2++;
                    break;

                case 3: quarter3++;
                    break;

                case 4: quarter4++;
                    break;

                default:
                    break;
            }


        }

        // Initialisation du text selon le nombre d'interaction capter par le Raspberry aux différents quarts de la journée
        tvQuarter1.setText(Integer.toString(quarter1));
        tvQuarter2.setText(Integer.toString(quarter2));
        tvQuarter3.setText(Integer.toString(quarter3));
        tvQuarter4.setText(Integer.toString(quarter4));

        // Mise à jour du tableau et l'initialisation des couleurs pour chaque quart du tableau
        pieChart.addPieSlice(
                new PieModel(
                        "12-5 AM",
                        Integer.parseInt(tvQuarter1.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "6-11 AM",
                        Integer.parseInt(tvQuarter2.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "12-5 PM",
                        Integer.parseInt(tvQuarter3.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "6-11 PM",
                        Integer.parseInt(tvQuarter4.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // appel de la méthode permettant la mise à jour en tant réelle du tableau qui change ses proportions selon les mouvements captés par le Raspberry Pi
        pieChart.startAnimation();
    }

}
