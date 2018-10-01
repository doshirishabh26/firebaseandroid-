package com.chattingstation.dell.chattingstation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registerdet extends AppCompatActivity {

    private FirebaseAuth mauth;
    private Toolbar mtoolbar;
    private ProgressDialog loadingbar;
    private EditText registerusername;
    private EditText registeruseremail;
    private EditText registerpassword;
    private Button registerbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerdet);

        mauth=FirebaseAuth.getInstance();


        mtoolbar =(Toolbar)findViewById(R.id.register_bar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Register");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       registerusername=(EditText) findViewById(R.id.register_name);
       registeruseremail= (EditText) findViewById(R.id.register_email);
       registerpassword= (EditText) findViewById(R.id.register_password);
        registerbut =(Button) findViewById(R.id.register_button);
          loadingbar = new ProgressDialog(this);
        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=registerusername.getText().toString();
                String email=registeruseremail.getText().toString();
                String password=registerpassword.getText().toString();

                RegisterAccount(name,email,password);
            }
        });

    }
    private  void RegisterAccount(String name,String email,String password){
        if(TextUtils.isEmpty(name)){
            Toast.makeText(Registerdet.this,"Please write your name" ,Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(Registerdet.this,"Please write your email" ,Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(Registerdet.this,"Please write your password" ,Toast.LENGTH_LONG).show();

        }
        else{
            loadingbar.setTitle("Creating new Account");
            loadingbar.setMessage("Please wait while we create account for you");
            loadingbar.show();
            mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Intent main =new Intent(Registerdet.this,Main2Activity.class);
                   main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(main);
                   finish();
               }
               else{
                   Toast.makeText(Registerdet.this,"Error occured",Toast.LENGTH_LONG).show();
               }
               loadingbar.dismiss();
                }
            });
        }
    }
}
