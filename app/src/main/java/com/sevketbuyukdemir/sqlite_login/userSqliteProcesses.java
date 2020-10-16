package com.sevketbuyukdemir.sqlite_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
/*
TABLE NAME: usertable
COLUMN COUNT: 5
COLUMN NAMES:-userid------username------userpassword------useremail------userluckynumber
COLUMN TYPES:-INTEGER-----STRING--------STRING------------STRING---------INTEGER
userid otomatik oluşturulup artırılacak biz oynamıyoruz.
 */
/*
user classı değişkenler ve constructor
public String userName;
public String userPassword;
public String userMail;
public int userLuckyNumber;
public user(String userName, String userPassword, String userMail, int userLuckyNumber)
 */
public class userSqliteProcesses {
    public SQLiteDatabase androiddb;
    public userCreateSqlite userdatabase;

    public userSqliteProcesses(Context c){
        try
        {
            userdatabase = new userCreateSqlite(c);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void openDatabase(){
        try
        {
            androiddb = userdatabase.getWritableDatabase();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void closeDatabase(){
        try
        {
            userdatabase.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public boolean isInDatabase(user newUser) {
        boolean find = false;
        try
        {
            user readingUser = new user("bulunamadı", "", "", 0);
            String columns[] = {"userid" , "username" , "userpassword" , "useremail" , "userluckynumber"};

            Cursor c = androiddb.query("usertable", columns, null,
                    null, null, null,null);
            String takenName = newUser.getUserName();
            c.moveToFirst();
            while(!c.isAfterLast()){
                if(takenName.equals(c.getString(1)))
                {
                    int id = c.getInt(0);
                    String name = c.getString(1);
                    String pass = c.getString(2);
                    String mail = c.getString(3);
                    int lucknum = c.getInt(4);

                    readingUser = new user(name, pass, mail, lucknum);
                    find = true;
                    break;
                }else
                {
                    c.moveToNext();
                }
            }
            c.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return find;
    }
    public void refreshDatabase(){
        try
        {
            userdatabase.close();
            androiddb = userdatabase.getWritableDatabase();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void createUser(user newUser){
        try
        {
            if(isInDatabase(newUser))
            {

            }else{
                ContentValues userInformations = new ContentValues();

                userInformations.put("username",newUser.getUserName());
                userInformations.put("userpassword",newUser.getUserPassword());
                userInformations.put("useremail",newUser.getUserMail());
                userInformations.put("userluckynumber",newUser.getUserLuckyNumber());

                androiddb.insert("usertable", null, userInformations);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteUser(String takenName){
        try
        {
            final String whereClause = "username=?";
            final String[] whereArgs = new String[] { takenName };
            androiddb.delete("usertable", whereClause, whereArgs);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public user showUser(String takenName){
        boolean find = false;
        user readingUser = new user("bulunamadı", "", "", 0);
        try
        {
            String columns[] = {"userid" , "username" , "userpassword" , "useremail" , "userluckynumber"};

            Cursor c = androiddb.query("usertable", columns, null,
                    null, null, null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                if(takenName.equals(c.getString(1)))
                {
                    int id = c.getInt(0);
                    String name = c.getString(1);
                    String pass = c.getString(2);
                    String mail = c.getString(3);
                    int lucknum = c.getInt(4);

                    readingUser = new user(name, pass, mail, lucknum);
                    find = true;
                    break;
                }else
                {
                    c.moveToNext();
                }
            }
            c.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return readingUser;
    }
    public List<user> listAllUser(){
        List<user> userList = new ArrayList<user>();
        try
        {
            String columns[] = {"userid" , "username" , "userpassword" , "useremail" , "userluckynumber"};

            Cursor c = androiddb.query("usertable", columns, null,
                    null, null, null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                int id = c.getInt(0);
                String name = c.getString(1);
                String pass = c.getString(2);
                String mail = c.getString(3);
                int lucknum = c.getInt(4);

                user readingUser = new user(name, pass, mail, lucknum);
                userList.add(readingUser);
                c.moveToNext();
            }
            c.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return  userList;
    }
    public List<String> listAllUserName(){
        List<String> userListName = new ArrayList<String>();
        try
        {
            String columns[] = {"userid" , "username" , "userpassword" , "useremail" , "userluckynumber"};

            Cursor c = androiddb.query("usertable", columns, null,
                    null, null, null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                int id = c.getInt(0);
                String name = c.getString(1);
                String pass = c.getString(2);
                String mail = c.getString(3);
                int lucknum = c.getInt(4);

                userListName.add(name);
                c.moveToNext();
            }
            c.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return  userListName;
    }
    public void resetTable(){
        try
        {
            androiddb.delete("usertable", null, null);
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public String loginPasswordControl(String takenName){
        String password = "";
        try
        {
            String columns[] = {"userid" , "username" , "userpassword" , "useremail" , "userluckynumber"};

            Cursor c = androiddb.query("usertable", columns, null,
                    null, null, null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                if(takenName.equals(c.getString(1)))
                {
                    int id = c.getInt(0);
                    String name = c.getString(1);
                    String pass = c.getString(2);
                    String mail = c.getString(3);
                    int lucknum = c.getInt(4);

                    password = pass;
                    break;
                }else
                {
                    c.moveToNext();
                }
            }
            c.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return password;
    }
    public user forgotPasswordProcess(String takenName){
        user user = new user("","","",0);
        try
        {
            String columns[] = {"userid" , "username" , "userpassword" , "useremail" , "userluckynumber"};

            Cursor c = androiddb.query("usertable", columns, null,
                    null, null, null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                if(takenName.equals(c.getString(1)))
                {
                    int id = c.getInt(0);
                    String name = c.getString(1);
                    String pass = c.getString(2);
                    String mail = c.getString(3);
                    int lucknum = c.getInt(4);

                    user = new user(name,pass,mail,lucknum);

                    break;
                }else
                {
                    c.moveToNext();
                }
            }
            c.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
