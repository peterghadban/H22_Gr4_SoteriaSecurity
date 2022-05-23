package com.example.soteriasecurityapp;


public class DataObj {
    private String date, heure, userName;

    /**
     * Initialisation de l'information provenant du FireBase
     * @param date la date à laquelle l'information à été perçu par le détecteur de mouvement
     * @param heure l'heure à laquelle l'information à été perçu par le détecteur de mouvement
     * @param userName
     */
    public DataObj(String date, String heure, String userName) {
        this.date = date;
        this.heure = heure;
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    /**
     * Méthode permettant la division des heures dans les quatre quarts différents
     * @return le quart de la journée dans laquelle se trouve l'heure (1-4)
     */
    public int getQuarterOfDay() {
        int quarter = 0;
        System.out.println("heure123 "+heure);
        int valeurHeure = Integer.parseInt(heure.substring(0,2));
        System.out.println("Parse succesful");
        System.out.println("valeurHeure: " + valeurHeure);

        if (valeurHeure <= 5)
            quarter = 1;
        else if (valeurHeure <= 11)
            quarter = 2;
        else if (valeurHeure <= 17)
            quarter = 3;
        else
            quarter = 4;

        return quarter;
    }
}
