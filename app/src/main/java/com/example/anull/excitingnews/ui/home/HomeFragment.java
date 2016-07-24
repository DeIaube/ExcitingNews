package com.example.anull.excitingnews.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.adapter.NewsListAdapter;
import com.example.anull.excitingnews.base.BaseFragment;
import com.example.anull.excitingnews.bean.NewsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by null on 2016/7/18.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, NewsListAdapter.OnItemClickListener {

    @InjectView(R.id.newsList)
    RecyclerView newsList;
    @InjectView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    ProgressDialog progressDialog;
    NewsListAdapter adapter;
    HomeContract.Presenter presenter;
    int lastItemIndex = 0;
    @InjectView(R.id.toTop)
    FloatingActionButton toTop;

    boolean isUp = false;
    boolean isDown = false;

    @Override
    protected void init() {
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(this);
        adapter = new NewsListAdapter(getContext(), new ArrayList<NewsList.StoriesBean>());
        adapter.setOnItemClickListener(this);
        newsList.setAdapter(adapter);
        newsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemIndex + 2 >= adapter.getItemCount()) {
                    presenter.loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemIndex = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if(dy > 0 && !isUp){
                    toTop.animate().translationY(toTop.getHeight() + 300).setInterpolator(new LinearInterpolator()).start();
                    isUp = true;
                    isDown = false;
                }else if (dy < 0 && !isDown){
                    toTop.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
                    isUp = false;
                    isDown = true;
                }
            }
        });

        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsList.smoothScrollToPosition(0);
            }
        });

        presenter = new HomePresenter(this);
        presenter.start();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle(getString(R.string.loading));
            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(null, R.string.no_network_connected, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_to_set, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                }).show();
    }

    @Override
    public void refreshData(List<NewsList.StoriesBean> newsList) {
        adapter.refresh(newsList);
    }

    @Override
    public void loadMoreData(List<NewsList.StoriesBean> newsList) {
        adapter.loadMore(newsList);
    }


    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void click(View v, NewsList.StoriesBean item) {
        presenter.showNewsDetail(getContext(), item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
