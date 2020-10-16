package com.sevketbuyukdemir.sqlite_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createAccount extends AppCompatActivity {
    //Database
    userSqliteProcesses uSqlP = new userSqliteProcesses(this);
    //Database
    //Activity
    Button backLogin;
    Button createAccountButton;//SQLite****
    EditText cuNameText;
    EditText cuPassText;
    EditText cuEmailText;
    EditText cuSecQueText;
    //Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //Database
        uSqlP.openDatabase();
        //Database
        //**************************************************************************
        createAccountButton = (Button) findViewById(R.id.cuButton);
        cuNameText = (EditText) findViewById(R.id.cuNameText);
        cuPassText = (EditText) findViewById(R.id.cuPassText);
        cuEmailText = (EditText) findViewById(R.id.cuEmailText);
        cuSecQueText = (EditText) findViewById(R.id.cuSecQueText);
        //**************************************************************************
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cuNameText.getText().toString();
                String password = cuPassText.getText().toString();
                String email = cuEmailText.getText().toString();

                String luckyNumberStr = cuSecQueText.getText().toString();
                int luckyNumber;
                try
                {
                    luckyNumber = Integer.valueOf(luckyNumberStr);
                }catch (NumberFormatException e)
                {
                    luckyNumber = -1;
                }

                if(name.equals("") || password.equals(""))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(createAccount.this);
                    builder.setCancelable(false);
                    builder.setTitle(R.string.caAlertTitle);
                    builder.setMessage(R.string.caAlertMessage);
                    builder.setNegativeButton(R.string.caAlertButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
                else if(email.equals(""))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(createAccount.this);
                    builder.setCancelable(false);
                    builder.setTitle(R.string.caAlertTitle);
                    builder.setMessage(R.string.caAlertMessageTwo);
                    builder.setNegativeButton(R.string.caAlertButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
                else if(luckyNumberStr.equals(""))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(createAccount.this);
                    builder.setCancelable(false);
                    builder.setTitle(R.string.caAlertTitle);
                    builder.setMessage(R.string.caAlertMessageTwo);
                    builder.setNegativeButton(R.string.caAlertButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }else
                {
                    //Kullanıcı oluşturuldu veritabanına kaydedilecek..
                    user user = new user(name,password,email,luckyNumber);
                    boolean isindb = uSqlP.isInDatabase(user);
                    //Kullamıcı veritabanına kaydedildi.
                    uSqlP.createUser(user);
                    //OLUSTURULDU MESAJI
                    if(isindb)
                    {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(createAccount.this);
                        builder.setCancelable(false);
                        builder.setTitle(R.string.caErrorDone);
                        builder.setMessage(R.string.caErrorDoneMessage);
                        builder.setNegativeButton(R.string.caErrorDoneButton, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                Intent lba = new Intent(createAccount.this,MainActivity.class);
                                startActivity(lba);
                            }
                        });
                        builder.show();
                    }else
                    {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(createAccount.this);
                        builder.setCancelable(false);
                        builder.setTitle(R.string.caDone);
                        builder.setMessage(R.string.caDoneMessage);
                        builder.setNegativeButton(R.string.caDoneButton, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                Intent lba = new Intent(createAccount.this,MainActivity.class);
                                startActivity(lba);
                            }
                        });
                        builder.show();
                    }
                }
            }
        });
        //**************************************************************************
        //Click listeners
        backLogin = (Button) findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lba = new Intent(createAccount.this,MainActivity.class);
                startActivity(lba);
            }
        });
        //Click listeners

    }

    @Override
    protected void onStart() {
        //Database
        uSqlP.openDatabase();
        //Database
        super.onStart();
    }
    @Override
    protected void onResume() {
        //Database
        uSqlP.openDatabase();
        //Database
        super.onResume();
    }
    @Override
    protected void onPause() {
        //Database
        uSqlP.closeDatabase();
        //Database
        super.onPause();
    }
    @Override
    protected void onStop() {
        //Database
        uSqlP.closeDatabase();
        //Database
        super.onStop();
    }
    @Override
    protected void onRestart() {
        //Database
        uSqlP.openDatabase();
        //Database
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        //Database
        uSqlP.closeDatabase();
        //Database
        super.onDestroy();
    }

}
