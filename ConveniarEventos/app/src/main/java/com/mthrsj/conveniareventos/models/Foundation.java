package com.mthrsj.conveniareventos.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Foundation implements Serializable {
    protected String Name;
    protected String Domain;

    public Foundation(String n,String d){
        Name = n;
        Domain = d;
    }

    public String getName(){
        return Name;
    }
    public String getDomain(){
        return Domain;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
