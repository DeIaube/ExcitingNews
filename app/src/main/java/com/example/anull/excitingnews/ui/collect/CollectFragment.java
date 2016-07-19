package com.example.anull.excitingnews.ui.collect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.adapter.NewsListAdapter;
import com.example.anull.excitingnews.base.BaseFragment;
import com.example.anull.excitingnews.bean.NewsList;
import com.example.anull.excitingnews.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by null on 2016/7/18.
 */
public class CollectFragment extends BaseFragment implements CollectContract.View, SwipeRefreshLayout.OnRefreshListener, NewsListAdapter.OnItemClickListener {

    @InjectView(R.id.newsList)
    RecyclerView newsList;
    @InjectView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    ProgressDialog progressDialog;
    NewsListAdapter adapter;
    CollectContract.Presenter presenter;

    @Override
    protected void init() {
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setColorSchemeResources( R.color.colorPrimary, android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        refreshLayout.setOnRefreshListener(this);
        adapter = new NewsListAdapter(getContext(), new ArrayList<NewsList.StoriesBean>());
        adapter.setOnItemClickListener(this);
        newsList.setAdapter(adapter);

        presenter = new CollectPresenter(this);
        presenter.start();

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            // 用于设置拖拽和滑动的方向
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0, swipeFlags = 0;
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END; //设置侧滑方向从左边和右边
                return makeMovementFlags(dragFlags, swipeFlags);//swipeFlags为0的话item不滑动
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteCollect(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                viewHolder.itemView.setAlpha(1-Math.abs(dX)/ ScreenUtil.getScreenHW(getContext())[0]);
            }
        });
        itemTouchHelper.attachToRecyclerView(newsList);












    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showProgressBar() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
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
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(newsList, R.string.no_network_connected, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_to_set, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                }).show();
    }

    @Override
    public void showEmptyMessage() {
        toast("抱歉，尚无数据");
    }

    @Override
    public void refreshData(List<NewsList.StoriesBean> newsList) {
        adapter.refresh(newsList);
    }

    @Override
    public void showRemoveItem(int position) {
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void showAddItem(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void showRevocation(final int position, final NewsList.StoriesBean newsItem) {
        Snackbar.make(newsList, R.string.delete_sure, Snackbar.LENGTH_LONG)
                .setAction(R.string.revocation, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.addCollect(position, newsItem);
                    }
                }).show();
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void click(View v, NewsList.StoriesBean item) {
        presenter.showNewsDetail(getContext(), item);
    }
}
