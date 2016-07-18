package com.example.anull.excitingnews.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.adapter.NewsListAdapter;
import com.example.anull.excitingnews.base.BaseFragment;
import com.example.anull.excitingnews.bean.NewsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by null on 2016/7/18.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener{

    @InjectView(R.id.newsList)
    RecyclerView newsList;
    @InjectView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    ProgressDialog progressDialog;
    NewsListAdapter adapter;
    HomeContract.Presenter presenter;
    int lastItemIndex = 0;

    @Override
    protected void init() {
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setColorSchemeResources( R.color.colorPrimary, android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(this);
        adapter = new NewsListAdapter(getContext(), new ArrayList<NewsList.StoriesBean>());
        newsList.setAdapter(adapter);
        presenter = new HomePresenter(this);
        presenter.start();
        newsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemIndex + 2 >= adapter.getItemCount()){
                    presenter.loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemIndex = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showProgressBar() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("加载中");
            progressDialog.setMessage("请稍后......");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        if(progressDialog != null){
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
}
