package com.chattingstation.dell.chattingstation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
protected Button existbutton;
protected Button newbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        newbutton= (Button) findViewById(R.id.button);
        existbutton=(Button) findViewById(R.id.button2);
        existbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main3Activity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        newbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main3Activity.this,Registerdet.class);
                startActivity(i);
            }
        });



    }
}
