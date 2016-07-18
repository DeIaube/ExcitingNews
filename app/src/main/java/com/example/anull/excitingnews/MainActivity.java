package com.example.anull.excitingnews;

import android.support.v7.widget.Toolbar;

import com.example.anull.excitingnews.base.BaseActivity;
import com.example.anull.excitingnews.home.HomeFragment;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void init() {

        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.mainContent, new HomeFragment()).commit();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
