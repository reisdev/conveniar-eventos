package com.mthrsj.conveniareventos.Utils.Database;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Database {
    private static Realm db = Realm.getDefaultInstance();

    public static Realm getInstance() {
        return db;
    }

    public static void configure(RealmConfiguration cfg){
        db.setDefaultConfiguration(cfg);
    }
}
