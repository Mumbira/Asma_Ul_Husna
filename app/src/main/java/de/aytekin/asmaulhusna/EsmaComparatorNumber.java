package de.aytekin.asmaulhusna;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class EsmaComparatorNumber implements Comparator<Esma> {
    public int compare(@NonNull Esma e1, @NonNull Esma e2){
        return Integer.compare(e1.getNumber(),e2.getNumber());
    }
}