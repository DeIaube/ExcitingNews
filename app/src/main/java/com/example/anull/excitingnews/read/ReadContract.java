package com.example.anull.excitingnews.read;

import com.example.anull.excitingnews.base.MvpPresenter;
import com.example.anull.excitingnews.base.MvpView;
import com.example.anull.excitingnews.bean.NewsDetail;

/**
 * Created by null on 2016/7/18.
 */
public interface ReadContract {
    interface View extends MvpView{
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage();
        void showDetail(NewsDetail newsDetail);
    }

    abstract class Presenter implements MvpPresenter{
        protected View view;

        public Presenter(View view) {
            this.view = view;
        }

        abstract void loadDetail(String id);
    }


}
