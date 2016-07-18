package com.example.anull.excitingnews.read;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.base.BaseActivity;
import com.example.anull.excitingnews.bean.NewsDetail;
import com.example.anull.excitingnews.bean.NewsList;
import com.example.anull.excitingnews.util.HtmlUtil;
import com.example.anull.excitingnews.util.StatusBarUtil;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

/**
 * Created by null on 2016/7/18.
 */
public class ReadActivity extends BaseActivity implements ReadContract.View{
    @InjectView(R.id.headImg)
    ImageView ivHead;
    @InjectView(R.id.tvCopyRight)
    TextView tvCopyRight;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.wbRead)
    WebView wbRead;

    ProgressDialog progressDialog;

    NewsList.StoriesBean newsItem;

    ReadContract.Presenter presenter;

    @Override
    protected void init() {
        StatusBarUtil.transparencyBar(this);
        newsItem = getIntent().getParcelableExtra("NewsItem");
        initView();
        initData();
    }

    private void initData() {
        String id = String.valueOf(newsItem.getId());
        presenter = new ReadPresenter(this);
        presenter.loadDetail(id);
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(newsItem.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebSettings settings = wbRead.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLoadWithOverviewMode(true);
        wbRead.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_read;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressBar() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getString(R.string.loading));
            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
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
    public void showDetail(NewsDetail newsDetail) {
        Picasso.with(ReadActivity.this).load(newsDetail.getImage()).into(ivHead);
        tvCopyRight.setText(newsDetail.getImage_source());
        String content = HtmlUtil.createHtmlData(newsDetail.getBody(), HtmlUtil.createCssTag(newsDetail.getCss()), HtmlUtil.createJsTag(newsDetail.getJs()));
        wbRead.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);
    }
}
