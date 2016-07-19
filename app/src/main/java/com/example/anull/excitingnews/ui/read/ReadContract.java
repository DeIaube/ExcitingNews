package com.example.anull.excitingnews.ui.read;

import com.example.anull.excitingnews.base.MvpPresenter;
import com.example.anull.excitingnews.base.MvpView;
import com.example.anull.excitingnews.bean.NewsDetail;
import com.example.anull.excitingnews.bean.NewsList;

/**
 * Created by null on 2016/7/18.
 */
public interface ReadContract {
    interface View extends MvpView{
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage();
        void showDetail(NewsDetail newsDetail);
        void showCollectSuccess();
        void showCollectFaile();
    }

    abstract class Presenter implements MvpPresenter{
        protected View view;

        public Presenter(View view) {
            this.view = view;
        }

        abstract void collect(NewsList.StoriesBean newsItem);

        abstract void loadDetail(String id);
    }


}
