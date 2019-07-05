package com.mthrsj.conveniareventos.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mthrsj.conveniareventos.Utils.Session;
import com.mthrsj.conveniareventos.favorito_frag;
import com.mthrsj.conveniareventos.inicial_frag;
import com.mthrsj.conveniareventos.insc_frag;
import com.mthrsj.conveniareventos.login_frag;
import com.mthrsj.conveniareventos.perfil_frag;

public class SliderAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 4;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private Session session;

    public SliderAdapter(FragmentManager fm, Session s) {
        super(fm);
        session = s;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new inicial_frag();
            case 1:
                return new favorito_frag();
            case 2:
                return new insc_frag();
            case 3:
                if (session.isLogged())
                    return new perfil_frag();
                else {
                    return new login_frag();
                }
        }
        return new inicial_frag();
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
