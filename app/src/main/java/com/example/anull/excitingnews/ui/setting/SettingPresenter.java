package com.example.anull.excitingnews.ui.setting;

import com.example.anull.excitingnews.config.Config;
import com.example.anull.excitingnews.util.SpUtil;

/**
 * Created by null on 2016/7/20.
 */
public class SettingPresenter extends SettingContract.Presenter {

    public SettingPresenter(SettingContract.View view) {
        super(view);
    }

    @Override
    void initSwitch() {
        view.setSwitchMode(SpUtil.getBoolen(Config.NO_IMG_MODE, false));
    }

    @Override
    void changSwitchMode(boolean isOpen) {
        SpUtil.putBoolen(Config.NO_IMG_MODE, isOpen);
    }

    @Override
    public void start() {
        initSwitch();
    }
}
