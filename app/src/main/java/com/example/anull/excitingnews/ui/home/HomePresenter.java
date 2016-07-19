package com.example.anull.excitingnews.ui.home;

import android.content.Context;
import android.content.Intent;

import com.example.anull.excitingnews.bean.NewsList;
import com.example.anull.excitingnews.network.ZhiHuRequest;
import com.example.anull.excitingnews.ui.read.ReadActivity;
import com.example.anull.excitingnews.util.DataUtil;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by null on 2016/7/18.
 */
public class HomePresenter extends HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    void refresh() {
        view.showProgressBar();
        ZhiHuRequest.getSingle().getZhiHuApi().latest()
                .map(new Func1<NewsList, List<NewsList.StoriesBean>>() {
                    @Override
                    public List<NewsList.StoriesBean> call(NewsList newsList) {
                        DataUtil.setCurrentData(newsList.getDate());
                        return newsList.getStories();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsList.StoriesBean>>() {
                    @Override
                    public void call(List<NewsList.StoriesBean> storiesBeen) {
                        view.hideProgressBar();
                        view.refreshData(storiesBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.hideProgressBar();
                        view.showErrorMessage();
                    }
                });
    }

    @Override
    void loadMore() {
        view.showProgressBar();
        ZhiHuRequest.getSingle().getZhiHuApi()
                .befare(DataUtil.getLasterData())
                .map(new Func1<NewsList, List<NewsList.StoriesBean>>() {
                    @Override
                    public List<NewsList.StoriesBean> call(NewsList newsList) {
                        return newsList.getStories();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsList.StoriesBean>>() {
                    @Override
                    public void call(List<NewsList.StoriesBean> storiesBeen) {
                        DataUtil.dataReduce();
                        view.hideProgressBar();
                        view.loadMoreData(storiesBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.hideProgressBar();
                        view.showErrorMessage();
                    }
                });
    }

    @Override
    void showNewsDetail(Context context, NewsList.StoriesBean newsItem) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra("NewsItem", newsItem);
        context.startActivity(intent);
    }


    @Override
    public void start() {
        refresh();
    }
}
