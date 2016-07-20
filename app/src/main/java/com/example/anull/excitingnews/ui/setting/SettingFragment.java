package com.example.anull.excitingnews.ui.setting;

import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.base.BaseFragment;

import butterknife.InjectView;

/**
 * Created by null on 2016/7/20.
 */
public class SettingFragment extends BaseFragment implements SettingContract.View, CompoundButton.OnCheckedChangeListener {


    @InjectView(R.id.noImgMode)
    Switch noImgMode;

    SettingContract.Presenter presenter;

    @Override
    protected void init() {
        presenter = new SettingPresenter(this);
        presenter.start();
        noImgMode.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    public void setSwitchMode(boolean isOpen) {
        noImgMode.setChecked(isOpen);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        presenter.changSwitchMode(isChecked);
    }
}
