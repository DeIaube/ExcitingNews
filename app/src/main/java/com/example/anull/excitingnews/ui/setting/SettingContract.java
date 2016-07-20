package com.example.anull.excitingnews.ui.setting;

import com.example.anull.excitingnews.base.MvpPresenter;
import com.example.anull.excitingnews.base.MvpView;

/**
 * Created by null on 2016/7/20.
 */
public interface SettingContract {
    interface View extends MvpView{
        void setSwitchMode(boolean isOpen);
    }

    abstract class Presenter implements MvpPresenter {
        protected View view;

        public Presenter(View view) {
            this.view = view;
        }

        abstract void initSwitch();

        abstract void changSwitchMode(boolean isOpen);
    }
}
