package com.example.anull.excitingnews;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.example.anull.excitingnews.base.BaseActivity;
import com.example.anull.excitingnews.config.Config;
import com.example.anull.excitingnews.ui.collect.CollectFragment;
import com.example.anull.excitingnews.ui.home.HomeFragment;
import com.example.anull.excitingnews.ui.more.MoreFragment;
import com.example.anull.excitingnews.ui.setting.SettingFragment;
import com.example.anull.excitingnews.util.CollectNewsHolder;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.home)
    TextView home;
    @InjectView(R.id.collection)
    TextView collection;
    @InjectView(R.id.setting)
    TextView setting;
    @InjectView(R.id.more)
    TextView more;

    TextView[] textViews;

    int selectColor;

    HomeFragment homeFragment;
    CollectFragment collectFragment;
    SettingFragment settingFragment;
    MoreFragment moreFragment;


    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        initCollectData();
        textViews = new TextView[]{home, collection, setting, more};
        selectColor = getResources().getColor(R.color.colorAccent);

        homeFragment = new HomeFragment();
        collectFragment = new CollectFragment();
        settingFragment = new SettingFragment();
        moreFragment = new MoreFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.mainContent, homeFragment).commit();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTextColor();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent,homeFragment).commit();
                getSupportActionBar().setTitle(R.string.home);
                home.setTextColor(selectColor);
            }
        });

        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTextColor();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, collectFragment).commit();
                getSupportActionBar().setTitle(R.string.collection);
                collection.setTextColor(selectColor);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTextColor();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, settingFragment).commit();
                getSupportActionBar().setTitle(getString(R.string.setting));
                setting.setTextColor(selectColor);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTextColor();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, moreFragment).commit();
                getSupportActionBar().setTitle(getString(R.string.more));
                more.setTextColor(selectColor);
            }
        });
    }

    private void initTextColor() {
        int defaultColor = getResources().getColor(android.R.color.darker_gray);
        for (TextView textView : textViews) {
            textView.setTextColor(defaultColor);
        }
    }

    private void initCollectData() {
        AVQuery<AVObject> query = new AVQuery<>("Collect");
        query.whereEqualTo("user", Config.ID);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(e == null && !list.isEmpty()){
                    CollectNewsHolder.getSingle().setCollectId(list.get(0).getObjectId());
                    try {
                        CollectNewsHolder.getSingle().setNewsCollect(new Gson().getAdapter(CollectNewsHolder.getSingle().getNewsCollect().getClass()).fromJson(String.valueOf(list.get(0).get("collect"))));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveCollectData();
    }

    private void saveCollectData() {
        String data = new Gson().toJson(CollectNewsHolder.getSingle().getNewsCollect());
        if(CollectNewsHolder.getSingle().getCollectId() == null){
            //新增收藏信息
            AVObject object = new AVObject("Collect");
            object.put("user", Config.ID);
            object.put("collect", data);
            object.saveInBackground();
        }else{
            //更新收藏信息
            AVObject object = AVObject.createWithoutData("Collect", CollectNewsHolder.getSingle().getCollectId());
            object.put("collect",data);
            object.saveInBackground();
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
