package com.sevketbuyukdemir.sqlite_login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class userCreateSqlite extends SQLiteOpenHelper {

    public userCreateSqlite(Context c)
    {
        super(c,"usertable", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE IF  NOT EXISTS "+ "usertable " + "(userid integer primary key autoincrement" +
                ", username TEXT, userpassword TEXT, useremail TEXT, userluckynumber integer)";
        /*
        TABLE NAME: usertable
        COLUMN COUNT: 5
        COLUMN NAMES:-userid------username------userpassword------useremail------userluckynumber
        COLUMN TYPES:-INTEGER-----STRING--------STRING------------STRING---------INTEGER
        userid otomatik oluşturulup artırılacak biz oynamıyoruz.
         */
        db.execSQL(sql);//sql query çalıştırılıp tablo oluşturuluyor.
    }

    public void onUpgrade (SQLiteDatabase db, int oldversion, int newversion){
    }
}
