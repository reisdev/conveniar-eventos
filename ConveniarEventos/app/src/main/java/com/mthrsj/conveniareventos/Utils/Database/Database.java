package com.mthrsj.conveniareventos.Utils.Database;

import io.realm.Realm;

public class Database {
    private static Realm db;

    public Database() {
        db = Realm.getDefaultInstance();
    }

    public static Realm getInstance() {
        return db;
    }
}
