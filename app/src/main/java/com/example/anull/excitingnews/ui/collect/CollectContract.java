package com.example.anull.excitingnews.ui.collect;

import android.content.Context;

import com.example.anull.excitingnews.base.MvpPresenter;
import com.example.anull.excitingnews.base.MvpView;
import com.example.anull.excitingnews.bean.NewsList;

import java.util.List;

/**
 * Created by null on 2016/7/18.
 */
public interface CollectContract {
    interface View extends MvpView{
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage();
        void showEmptyMessage();
        void refreshData(List<NewsList.StoriesBean> newsList);
        void showRemoveItem(int position);
        void showAddItem(int position);
        void showRevocation(int position, NewsList.StoriesBean newsItem);
    }

    abstract class Presenter implements MvpPresenter{
        protected View view;

        public Presenter(View view) {
            this.view = view;
        }

        abstract void refresh();

        abstract void showNewsDetail(Context context, NewsList.StoriesBean newsItem);

        abstract void deleteCollect(int position);

        abstract void addCollect(int position, NewsList.StoriesBean newsItem);
    }
}
