package com.example.anull.excitingnews.ui.home;

import android.content.Context;

import com.example.anull.excitingnews.base.MvpPresenter;
import com.example.anull.excitingnews.base.MvpView;
import com.example.anull.excitingnews.bean.NewsList;

import java.util.List;

/**
 * Created by null on 2016/7/18.
 */
public interface HomeContract {
    interface View extends MvpView{
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage();
        void refreshData(List<NewsList.StoriesBean> newsList);
        void loadMoreData(List<NewsList.StoriesBean> newsList);
    }

    abstract class Presenter implements MvpPresenter{
        protected View view;

        public Presenter(View view) {
            this.view = view;
        }

        abstract void refresh();

        abstract void loadMore();

        abstract void showNewsDetail(Context context, NewsList.StoriesBean newsItem);
    }
}
