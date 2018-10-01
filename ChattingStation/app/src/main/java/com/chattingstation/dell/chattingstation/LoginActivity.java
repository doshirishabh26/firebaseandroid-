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

public class LoginActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private Button loginbutt;
    private EditText logemail;
    private ProgressDialog loadingbar;
    private FirebaseAuth mauth;
    private EditText logpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mtoolbar= (Toolbar) findViewById(R.id.login_bar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Login");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       mauth=FirebaseAuth.getInstance();
       loginbutt=(Button) findViewById(R.id.login_button);
       logemail=(EditText) findViewById(R.id.log_email);
       logpassword=(EditText) findViewById(R.id.log_pass);
       loadingbar=new ProgressDialog(this);
       loginbutt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email=logemail.getText().toString();
               String password=logpassword.getText().toString();
               Loginuseraccount(email,password);
           }
       });
    }

    private void Loginuseraccount(String email, String password) {
      if(TextUtils.isEmpty(email)){
          Toast.makeText(LoginActivity.this,"pleases enter your email",Toast.LENGTH_SHORT).show();
      }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"please enter your password",Toast.LENGTH_SHORT).show();
        }
        else{
          loadingbar.setTitle("Loading Account");
          loadingbar.setMessage("Please wait while we verify your account");
          loadingbar.show();
          mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      Intent i =new Intent(LoginActivity.this,Main2Activity.class);
                      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(i);
                      finish();
                  }
                  else{

                          Toast.makeText(LoginActivity.this,"Please check your email and password",Toast.LENGTH_SHORT).show();

                  }
                  loadingbar.dismiss();
              }
          });
        }
    }
}
