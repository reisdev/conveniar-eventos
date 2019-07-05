package com.mthrsj.conveniareventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mthrsj.conveniareventos.Adapter.SliderAdapter;
import com.mthrsj.conveniareventos.Utils.API.models.Foundation;
import com.mthrsj.conveniareventos.Utils.Database.Database;
import com.mthrsj.conveniareventos.Utils.Session;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Foundation foundation;
    Session session;
    int actual_fragment = 0;

    private ViewPager mPager;
    private SliderAdapter pagerAdapter;
    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();
        foundation = new Foundation((Foundation) it.getSerializableExtra("foundation"));

        session = new Session(this);
        mPager = findViewById(R.id.pager);
        nav = findViewById(R.id.bottom_navigation);
        //nav.setItemIconSize(100);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.frag_inicial:
                        if (actual_fragment != 0) {
                            mPager.setCurrentItem(0);
                            actual_fragment = 0;
                        }
                        break;
                    case R.id.frag_fav:
                        if (actual_fragment != 1) {
                            mPager.setCurrentItem(1);
                            actual_fragment = 1;
                        }
                        break;
                    case R.id.frag_ins:
                        if (actual_fragment != 2) {
                            mPager.setCurrentItem(2);
                            actual_fragment = 2;
                        }
                        break;
                    case R.id.frag_perfil:
                        if (actual_fragment != 3) {
                            mPager.setCurrentItem(3);
                            actual_fragment = 3;
                        }
                        break;
                }
                nav.setSelectedItemId(actual_fragment);
                return true;
            }
        });
        updatePager(1);
        mPager.setCurrentItem(0);
    }

    public void setView(int position) {
        if (mPager != null)
            mPager.setCurrentItem(position);
    }

    public void updatePager(int position) {
        // Instantiate a ViewPager and a PagerAdapter.
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels == 0)
                    switch (position) {
                        case 0:
                            nav.setSelectedItemId(R.id.frag_inicial);
                            break;
                        case 1:
                            nav.setSelectedItemId(R.id.frag_fav);
                            break;
                        case 2:
                            nav.setSelectedItemId(R.id.frag_ins);
                            break;
                        case 3:
                            nav.setSelectedItemId(R.id.frag_perfil);
                            break;
                    }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagerAdapter = new SliderAdapter(getSupportFragmentManager());
        if (session.isLogged())
            pagerAdapter.changeToAuthenticated();
        else {
            pagerAdapter.changeToNotAuthenticated();
        }
        mPager.setAdapter(pagerAdapter);
        mPager.setCurrentItem(position);
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Realm db = Database.getInstance();
        db.close();
    }
}
