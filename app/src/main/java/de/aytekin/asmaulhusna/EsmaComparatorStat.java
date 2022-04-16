package de.aytekin.asmaulhusna;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class EsmaComparatorStat implements Comparator<Esma> {
    public int compare(@NonNull Esma e1, @NonNull Esma e2){
        if(!e1.hasStats() && e2.hasStats()){
            return 1;
        }else if(e1.hasStats() && !e2.hasStats()){
            return -1;
        }else if(!e1.hasStats() && !e2.hasStats()){
            return Integer.compare(e1.getNumber(),e2.getNumber());
        }


        if(e1.getRating()>e2.getRating() ){
            return -1;
        }else if(e1.getRating() == e2.getRating()){
            return Integer.compare(e1.getNumber(),e2.getNumber());
        }else {
            return 1;
        }
    }
}