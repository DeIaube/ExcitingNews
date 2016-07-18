package com.example.anull.excitingnews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.util.StatusBarUtil;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by null on 2016/7/17.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected CompositeSubscription subscription = new CompositeSubscription();
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarColor(this, R.color.colorPrimaryDark);
        setContentView(getLayout());
        ButterKnife.inject(this);
        init();
    }

    protected void toast(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void log(String content){
        Log.i(TAG, content);
    }

    protected abstract void init();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
