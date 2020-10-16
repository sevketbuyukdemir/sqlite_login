package com.sevketbuyukdemir.sqlite_login;

import androidx.appcompat.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;

public class userListView extends ListActivity {
    //Database
    userSqliteProcesses uSqlP = new userSqliteProcesses(this);
    //Database
    //Activity
    EditText userName;
    EditText userPassword;
    Button deleteUser;
    Button backLogin;
    //Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);
        //Database
        uSqlP.openDatabase();
        //Database
        List<String> userListName = uSqlP.listAllUserName();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, userListName);
        setListAdapter(adapter);
        //Click listeners
        userName = (EditText) findViewById(R.id.uLVNameLogin);
        userPassword = (EditText) findViewById(R.id.uLVPassLogin);
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setText("");
            }
        });
        userPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPassword.setText("");
            }
        });
        deleteUser = (Button) findViewById(R.id.uLVDeleteButton);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEntryN = userName.getText().toString();
                String userEntryP = userPassword.getText().toString();
                String password = uSqlP.loginPasswordControl(userEntryN);
                if(userEntryP.equals(password))
                {
                    uSqlP.deleteUser(userEntryN);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(userListView.this);
                    builder.setCancelable(false);
                    builder.setMessage(R.string.uLVDeleteDoneMessage);
                    builder.setNegativeButton(R.string.uLVDeleteDoneMessageButton, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {
                            Intent ulva = new Intent(userListView.this,MainActivity.class);
                            startActivity(ulva);
                        }
                    });
                    builder.show();
                }else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(userListView.this);
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
        backLogin = (Button) findViewById(R.id.uLVbackLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lba = new Intent(userListView.this,MainActivity.class);
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
