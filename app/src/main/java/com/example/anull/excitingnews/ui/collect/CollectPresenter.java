package com.example.anull.excitingnews.ui.collect;

import android.content.Context;
import android.content.Intent;

import com.example.anull.excitingnews.bean.NewsList;
import com.example.anull.excitingnews.ui.read.ReadActivity;
import com.example.anull.excitingnews.util.CollectNewsHolder;

/**
 * Created by null on 2016/7/18.
 */
public class CollectPresenter extends CollectContract.Presenter {

    public CollectPresenter(CollectContract.View view) {
        super(view);
    }

    @Override
    void refresh() {

        if(CollectNewsHolder.getSingle().isEmpty()){
            view.showEmptyMessage();
        }else{
            view.refreshData(CollectNewsHolder.getSingle().getNewsCollect().getList());
        }
        view.hideProgressBar();
        // 存new Gson().toJson(CollectNewsHolder.getSingle().getNewsCollect());
        // 取NewsCollect collect = new Gson().getAdapter(CollectNewsHolder.getSingle().getNewsCollect().getClass()).fromJson("{\"list\":[{\"id\":8583293,\"images\":[\"http://pic2.zhimg.com/2c8f5ce9d7bbf7ec61c2c3f215360205.jpg\"],\"title\":\"80 亿的中超，1 亿身价的外援，场边的队医却只能拿出冰袋\"},{\"id\":8583636,\"images\":[\"http://pic3.zhimg.com/7b3aa4ea912fd89744e56afe636dcd7e.jpg\"],\"title\":\"大误 · 如何成为一名优秀的门房大爷？\"},{\"id\":8584156,\"images\":[\"http://pic2.zhimg.com/a31dc937505c72ce5140a1c9ac101a95.jpg\"],\"title\":\"知乎好问题 · 怎样搭配三餐有助于减掉脂肪？\"},{\"id\":8583572,\"images\":[\"http://pic2.zhimg.com/2360811c3a3848b5a6831485a5497789.jpg\"],\"title\":\"亲爱的沙丁鱼：有新鲜的，为什么要吃罐头？\"}]}");

    }

    @Override
    void showNewsDetail(Context context, NewsList.StoriesBean newsItem) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra("NewsItem", newsItem);
        context.startActivity(intent);
    }

    @Override
    void deleteCollect(int position) {
        NewsList.StoriesBean storiesBean = CollectNewsHolder.getSingle().getNewsCollect().getList().get(position);
        CollectNewsHolder.getSingle().getNewsCollect().remove(position);
        view.showRemoveItem(position);
        view.showRevocation(position, storiesBean);
    }

    @Override
    void addCollect(int position, NewsList.StoriesBean newsItem) {
        CollectNewsHolder.getSingle().getNewsCollect().add(position, newsItem);
        view.showAddItem(position);
    }

    @Override
    public void start() {
        refresh();
    }
}
