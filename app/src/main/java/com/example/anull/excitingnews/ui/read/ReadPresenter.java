package com.example.anull.excitingnews.ui.read;

import com.example.anull.excitingnews.bean.NewsDetail;
import com.example.anull.excitingnews.bean.NewsList;
import com.example.anull.excitingnews.network.ZhiHuRequest;
import com.example.anull.excitingnews.util.CollectNewsHolder;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by null on 2016/7/18.
 */
public class ReadPresenter extends ReadContract.Presenter {

    public ReadPresenter(ReadContract.View view) {
        super(view);
    }

    @Override
    void collect(NewsList.StoriesBean newsItem) {
        if(CollectNewsHolder.getSingle().add(newsItem)){
            view.showCollectSuccess();
        }else{
            view.showCollectFaile();
        }
    }

    @Override
    void loadDetail(String id) {
        view.showProgressBar();
        ZhiHuRequest.getSingle().getZhiHuApi().detail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsDetail>() {
                    @Override
                    public void call(NewsDetail newsDetail) {
                        view.hideProgressBar();
                        view.showDetail(newsDetail);
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
    public void start() {

    }
}
