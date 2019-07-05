package com.mthrsj.conveniareventos.Adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mthrsj.conveniareventos.favorito_frag;
import com.mthrsj.conveniareventos.inicial_frag;
import com.mthrsj.conveniareventos.insc_frag;
import com.mthrsj.conveniareventos.login_frag;
import com.mthrsj.conveniareventos.perfil_frag;
import com.mthrsj.conveniareventos.signup_frag;

import java.util.Arrays;
import java.util.List;

public class SliderAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 5;
    private List<Fragment> authenticated = Arrays.asList(new inicial_frag(),
            new favorito_frag(),
            new insc_frag(),
            new perfil_frag()
    );
    private List<Fragment> notAuthenticated = Arrays.asList(new inicial_frag(),
            new favorito_frag(),
            new insc_frag(),
            new login_frag(),
            new signup_frag()
    );
    private List<Fragment> routes = notAuthenticated;

    public SliderAdapter(FragmentManager fm) {
        super(fm);
    }

    public void changeToAuthenticated(){
        Log.d("AUTH","Changed to authenticated");
        routes = authenticated;
        notifyDataSetChanged();
    }

    public void changeToNotAuthenticated(){
        Log.d("AUTH","Changed to not authenticated");
        routes = notAuthenticated;
        notifyDataSetChanged();
    }

    public Fragment getItem(int position) {
        return routes.get(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
