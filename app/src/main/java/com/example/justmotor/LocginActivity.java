package com.example.justmotor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LocginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locgin);

        tabLayout = findViewById(R.id.Tab_Layout);
        viewPager = findViewById(R.id.View_Pager);



        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setTranslationY(300);


        tabLayout.setAlpha(v);


        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();


        viewPager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener (new TabLayout.OnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem (tab.getPosition ());
                Log.i ("TAG", "onTabSelected:" + tab.getPosition ());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i ("TAG", "onTabUnselected:" + tab.getPosition ());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i ("TAG", "onTabReselected:" + tab.getPosition ());
            }

        });
    }


}