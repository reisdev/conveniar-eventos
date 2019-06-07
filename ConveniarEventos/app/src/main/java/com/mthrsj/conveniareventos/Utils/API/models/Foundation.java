package com.mthrsj.conveniareventos.Utils.API.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Foundation implements Serializable {
    private String Fundacao;
    private URLS URLs;

    public Foundation(Foundation f) {
        Fundacao = f.getName();
        URLs = f.getURLS();
    }

    public Foundation(String n, URLS u){
        Fundacao = n;
        URLs = u;
    }

    public String getName(){
        return Fundacao;
    }
    public String getNameReduced(){
        return getName().split("\\s*([-]|[|])")[0];
    }
    public URLS getURLS(){
        return URLs;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
