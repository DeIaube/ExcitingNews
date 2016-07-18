package com.example.anull.excitingnews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by null on 2016/7/17.
 */
public abstract class BaseFragment extends Fragment {
    protected CompositeSubscription subscription = new CompositeSubscription();
    protected String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.inject(this, rootView);
        init();
        return rootView;
    }


    protected void toast(String content){
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    protected void log(String content){
        Log.i(TAG, content);
    }

    protected abstract void init();

    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
