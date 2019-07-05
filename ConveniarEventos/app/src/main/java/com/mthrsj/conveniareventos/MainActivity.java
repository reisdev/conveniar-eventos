package com.mthrsj.conveniareventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
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
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();
        foundation = new Foundation((Foundation) it.getSerializableExtra("foundation"));

        session = new Session(this);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        pagerAdapter = new SliderAdapter(getSupportFragmentManager(), session);
        mPager.setAdapter(pagerAdapter);

        final BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setItemIconSize(100);
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
        mPager.setCurrentItem(0);
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
