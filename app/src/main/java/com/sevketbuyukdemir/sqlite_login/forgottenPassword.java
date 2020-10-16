package com.sevketbuyukdemir.sqlite_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgottenPassword extends AppCompatActivity {
    //Database
    userSqliteProcesses uSqlP = new userSqliteProcesses(this);
    //Database
    //Activity
    Button backLoginForgot;
    Button takeNewPasswordButton;//SQLite****
    EditText fpNameUserText;
    EditText fpUserEmailText;
    EditText fpUserLuckyNumberText;
    //Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        //Database
        uSqlP.openDatabase();
        //Database
        //Click listeners
        fpNameUserText = (EditText) findViewById(R.id.fpNameUserText);
        fpUserEmailText = (EditText) findViewById(R.id.fpUserEmailText);
        fpUserLuckyNumberText = (EditText) findViewById(R.id.fpUserLuckyNumberText);

        takeNewPasswordButton = (Button) findViewById(R.id.fpButton);
        takeNewPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEntryN = fpNameUserText.getText().toString();
                String userEntryE = fpUserEmailText.getText().toString();
                String userEntryL = fpUserLuckyNumberText.getText().toString();
                int luck = -1;
                if(userEntryL.equals("")){

                }else{
                    luck = Integer.valueOf(userEntryL);
                }
                user controluser = uSqlP.forgotPasswordProcess(userEntryN);
                if(userEntryE.equals("") && userEntryL.equals("")){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(forgottenPassword.this);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.fpErrorDoneMessage);
                    builder.setNegativeButton(R.string.fpErrorDoneButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }else if(userEntryL.equals("")){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(forgottenPassword.this);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.fpErrorDoneMessage);
                    builder.setNegativeButton(R.string.fpErrorDoneButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }else if(userEntryE.equals(controluser.getUserMail()) && luck == (controluser.getUserLuckyNumber()))
                {
                    String message = "Password: "+controluser.getUserPassword();//user password given
                    final AlertDialog.Builder builder = new AlertDialog.Builder(forgottenPassword.this);
                    builder.setCancelable(false);
                    builder.setMessage(message);
                    builder.setNegativeButton(R.string.fpErrorDoneButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {
                            Intent lba = new Intent(forgottenPassword.this, MainActivity.class);
                            startActivity(lba);
                        }
                    });
                    builder.show();
                }else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(forgottenPassword.this);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.fpErrorDoneMessage);
                    builder.setNegativeButton(R.string.fpErrorDoneButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                }
            }
        });
        backLoginForgot = (Button) findViewById(R.id.backLoginForgot);
        backLoginForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lba = new Intent(forgottenPassword.this,MainActivity.class);
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
