package com.mthrsj.conveniareventos.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.concurrent.atomic.DoubleAccumulator;

public class Foundation implements Serializable {
    protected String Name;
    protected String Domain;

    public Foundation(Foundation f) {
        Name = f.getName();
        Domain = f.getDomain();
    }

    public Foundation(String n, String d){
        Name = n;
        Domain = d;
    }

    public String getName(){
        return Name;
    }
    public String getNameReduced(){
        return getName().split("\\s*([-]|[|])")[0];
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
