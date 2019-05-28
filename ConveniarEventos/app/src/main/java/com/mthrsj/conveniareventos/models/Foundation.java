package com.mthrsj.conveniareventos.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Foundation implements Serializable {
    public String Name;
    public String Domain;

    public Foundation(String n,String d){
        Name = n;
        Domain = d;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
