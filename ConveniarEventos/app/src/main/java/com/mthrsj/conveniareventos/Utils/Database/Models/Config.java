package com.mthrsj.conveniareventos.Utils.Database.Models;

import io.realm.RealmObject;

public class Config extends RealmObject {

    private String name;
    private String value;

    public Config() {
        this.name = "";
        this.value = "";
    }

    public Config(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
