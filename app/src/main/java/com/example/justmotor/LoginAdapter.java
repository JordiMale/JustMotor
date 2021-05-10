package com.example.justmotor;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.justmotor.ui.RegistrarLogin.LoginFragment;
import com.example.justmotor.ui.RegistrarLogin.RegistrarFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int TotalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int TotalTabs){
        super(fm);
        this.context=context;
        this.TotalTabs=TotalTabs;
    }

    @Override
    public int getCount() {
        return TotalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                RegistrarFragment registrarFraagment = new RegistrarFragment();
                return registrarFraagment;
            default:
                return null;


        }
    }
}
