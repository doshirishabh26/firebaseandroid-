package com.chattingstation.dell.chattingstation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
      private android.support.v7.widget.Toolbar mtoolbar;

      private FirebaseAuth mauth;
      private ViewPager myviewpager;
      private TabLayout mytablayout;
      private TabsPagerAdapter mytabpageadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mauth=FirebaseAuth.getInstance();

        myviewpager=(ViewPager) findViewById(R.id.main_tabs_pager);
        mytabpageadapter=new TabsPagerAdapter(getSupportFragmentManager());
        myviewpager.setAdapter(mytabpageadapter);
        mytablayout=(TabLayout)findViewById(R.id.main_tabs);
        mytablayout.setupWithViewPager(myviewpager);


        mtoolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Chatting Station");
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentuser= mauth.getCurrentUser();
        if(currentuser==null){
          LogoutUser();

        }
    }

    private void LogoutUser() {
        Intent i=new Intent(Main2Activity.this,Main3Activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
          if(item.getItemId()==R.id.main_logout){
              mauth.signOut();
               LogoutUser();
          }
          return true;

    }
}