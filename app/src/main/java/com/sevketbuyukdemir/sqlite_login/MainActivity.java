package com.sevketbuyukdemir.sqlite_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //Database
    userSqliteProcesses uSqlP = new userSqliteProcesses(this);
    //Database
    //Activity
    EditText userName;
    EditText userPassword;
    Button loginButton;
    Button forgottenButton;
    Button logCreBut;
    Button userListButton;
    //Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Database
        uSqlP.openDatabase();
        //Database
        //Click listeners
        userName = (EditText) findViewById(R.id.loginUserName);
        //For security
        userName.setText("");
        //For security
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setText("");
            }
        });
        userPassword = (EditText) findViewById(R.id.loginPassword);
        //For security
        userPassword.setText("");
        //For security
        userPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPassword.setText("");
            }
        });
        //***********************************************************************
        //***********************************************************************
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEntryN = userName.getText().toString();
                String userEntryP = userPassword.getText().toString();
                String password = uSqlP.loginPasswordControl(userEntryN);
                if(userEntryN.equals("") || userEntryP.equals("")){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.lpErrorDoneMessage);
                    builder.setNegativeButton(R.string.lpErrorDoneButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }else if(userEntryP.equals(password))
                {
                    //Intent gma = new Intent(MainActivity.this,galleryMainActivity.class); todo second
                    // todo for google maps
                    //startActivity(gma);
                }else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.lpErrorDoneMessage);
                    builder.setNegativeButton(R.string.lpErrorDoneButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
            }
        });
        //***********************************************************************
        //***********************************************************************
        forgottenButton = (Button) findViewById(R.id.forgottenPasswordButton);
        forgottenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpa = new Intent(MainActivity.this,forgottenPassword.class);
                startActivity(fpa);
            }
        });
        logCreBut = (Button) findViewById(R.id.logCreBut);
        logCreBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent caa = new Intent(MainActivity.this,createAccount.class);
                startActivity(caa);
            }
        });
        userListButton = (Button) findViewById(R.id.userListButton);
        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usla = new Intent(MainActivity.this,userListView.class);
                startActivity(usla);
            }
        });
        //Click Listener
    }


    @Override
    protected void onStart() {
        //For security
        userName.setText("");
        userPassword.setText("");
        //For security
        //Database
        uSqlP.openDatabase();
        //Database
        super.onStart();
    }
    @Override
    protected void onResume() {
        //For security
        userName.setText("");
        userPassword.setText("");
        //For security
        //Database
        uSqlP.openDatabase();
        //Database
        super.onResume();
    }
    @Override
    protected void onPause() {
        //For security
        userName.setText("");
        userPassword.setText("");
        //For security
        //Database
        uSqlP.closeDatabase();
        //Database
        super.onPause();
    }
    @Override
    protected void onStop() {
        //For security
        userName.setText("");
        userPassword.setText("");
        //For security
        //Database
        uSqlP.closeDatabase();
        //Database
        super.onStop();
    }
    @Override
    protected void onRestart() {
        //For security
        userName.setText("");
        userPassword.setText("");
        //For security
        //Database
        uSqlP.openDatabase();
        //Database
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        //For security
        userName.setText("");
        userPassword.setText("");
        //For security
        //Database
        uSqlP.closeDatabase();
        //Database
        super.onDestroy();
    }
}
