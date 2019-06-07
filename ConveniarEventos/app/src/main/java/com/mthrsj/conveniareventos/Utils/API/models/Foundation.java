package com.mthrsj.conveniareventos.Utils.API.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Foundation implements Serializable {
    private String Fundacao;
    private URLS Urls;

    public Foundation(Foundation f) {
        Fundacao = f.getName();
        Urls = f.getURLS();
    }

    public Foundation(String n, URLS u){
        Fundacao = n;
        Urls = u;
    }

    public String getName(){
        return Fundacao;
    }
    public String getNameReduced(){
        return getName().split("\\s*([-]|[|])")[0];
    }
    public URLS getURLS(){
        return Urls;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
