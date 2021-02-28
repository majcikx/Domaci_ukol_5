package com.engeto.StateVat;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        File zdrojovySoubor = new File("vat-eu.txt");

        Scanner scanner = new Scanner(zdrojovySoubor);

        ArrayList<Stat> sazbyStatu = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String nactenyRadek = scanner.nextLine();
            sazbyStatu.add(rozparsujRadek(nactenyRadek));

        }
        //najdiStatPodleZkratky(sazbyStatu, "CZ");

        Scanner nacitaniZPrikazovehoRadku = new Scanner(System.in);

        String zkratka;
        String volba;
        double sazbaDane = 0;
        System.out.println("Zadej jestli chceš vypsat stát s vyšší než je zadaná sazba nebo informace o konkrétních státech");
        System.out.println("Jestli chceš vypsat státy s vyšší sazbou zadej 1");
        System.out.println("Jestli chceš vypsat informace o konkrétních státech, zadej 2");
        volba = nacitaniZPrikazovehoRadku.nextLine();
        if (volba.equals("1")) {
            System.out.println("Vybral jsi zobrazení států s vyšší než zadanou sazbou");
            System.out.println("Zadej sazbu dane: ");
            sazbaDane = nacitaniZPrikazovehoRadku.nextDouble();
            vypisVypisInformaceOStatech(vyfiltrujStatySeSazbouVetsiNez(sazbyStatu, sazbaDane));
        }
        if (volba.equals("2")) {
            System.out.println("Vybral sis vypsání informací o konkrétních státech");
            System.out.println("Zadej zkratky států oddělených čárkou");

            String vybraneStaty = nacitaniZPrikazovehoRadku.nextLine();
            String[] statyKvypsani = rozparsujVybraneStaty(vybraneStaty);

            for (int i = 0; i < statyKvypsani.length;i++) {
                boolean naslo = false;
                for (int j = 0; j < sazbyStatu.size();j++) {

                    if (sazbyStatu.get(j).getZkratkaStatu().equals(statyKvypsani[i])) {
                        System.out.println(sazbyStatu.get(j).getNazevStatu() + "(" + sazbyStatu.get(j).getZkratkaStatu() + ")" + " - " + "Plná daňová sazba: " +
                                sazbyStatu.get(j).getPlnaSazba() + " Snížená daňová sazba: " + sazbyStatu.get(j).getSnizenaSazba());
                        naslo = true;
                        break;
                    }
                }
                if (naslo) {
                    continue;
                }
                else {
                    System.out.println(statyKvypsani[i] + "není info");
                }
            }
        }
/*
        zkratka = nacitaniZPrikazovehoRadku.nextLine();

        System.out.println("Vybral sis tento stat: " + zkratka);

        Stat hledanaSazba = najdiStatPodleZkratky(sazbyStatu, zkratka);

        if (hledanaSazba == null) {
            System.out.println("Pro tento stat nemame žadné informace");
        } else {
            System.out.println("Jmeno: " + hledanaSazba.getNazevStatu());
            System.out.println("Plna danova sazba: " + hledanaSazba.getPlnaSazba() + "%");
            System.out.println("Snižená danova sazba: " + hledanaSazba.getSnizenaSazba() + "%");
        }*/
    }

    private static String[] rozparsujVybraneStaty(String vybraneStaty) {
        String[] rozsekanyRadek = vybraneStaty.split(",");

        return rozsekanyRadek;
    }

    private static Stat rozparsujRadek(String nactenyRadek) {
        String[] rozsekanyRadek = nactenyRadek.split("\t");

        Stat danoveSazby = new Stat();

        System.out.println("Zkratka: " + rozsekanyRadek[0]);
        System.out.println("Název: " + rozsekanyRadek[1]);
        System.out.println("Plná sazba: " + rozsekanyRadek[2]);
        System.out.println("Snížená sazba: " + rozsekanyRadek[3]);
        System.out.println("Speciální sazba: " + rozsekanyRadek[4]);

        danoveSazby.setNazevStatu(rozsekanyRadek[1]);
        danoveSazby.setZkratkaStatu(rozsekanyRadek[0]);
        danoveSazby.setPlnaSazba(Double.valueOf(rozsekanyRadek[2].replace(",", ".")));
        danoveSazby.setSnizenaSazba(Double.valueOf(rozsekanyRadek[3].replace(",", ".")));
        danoveSazby.setSpecialnisazba(Boolean.valueOf(rozsekanyRadek[4].replace(",", ".")));

        return danoveSazby;

    }

    private static List<Stat> vyfiltrujStatySeSazbouVetsiNez(List<Stat> kfiltrovani, Double sazba) {
        ArrayList<Stat> danoveSazbyStatu = new ArrayList<>();
        for (Stat stat : kfiltrovani) {
            if (stat.getPlnaSazba() > sazba)
                danoveSazbyStatu.add(stat);

        }
        return danoveSazbyStatu;
    }

    private static void vypisVypisInformaceOStatech(List<Stat> kVypsani) {
        for (Stat stat : kVypsani) {
            System.out.println("Název státu: " + stat.getNazevStatu() + ", VAT: (" + stat.getPlnaSazba() + "%/" + stat.getSnizenaSazba() + "%)");
        }
    }

    private static Stat najdiStatPodleZkratky(List<Stat> prohledavanyStat, String zkratka) {


        for (Stat stat : prohledavanyStat) {
            if (stat.getZkratkaStatu().equals(zkratka)) {
                return stat;
            }
        }

        return null;


    }

}
