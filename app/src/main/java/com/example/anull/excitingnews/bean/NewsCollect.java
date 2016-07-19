package com.example.anull.excitingnews.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DESKTOP-1JBF8Q8$ on 2016/7/19.
 */
public class NewsCollect {
    private List<NewsList.StoriesBean> list;

    public NewsCollect() {
        list = new ArrayList<>();
    }

    public List<NewsList.StoriesBean> getList() {
        return list;
    }

    public void setList(List<NewsList.StoriesBean> list) {
        this.list = list;
    }

    public void add(NewsList.StoriesBean newsItem){
        list.add(newsItem);
    }

    public void add(int position, NewsList.StoriesBean newsItem){
        list.add(position, newsItem);
    }

    public void remove(NewsList.StoriesBean newsItem){
        list.remove(newsItem);
    }

    public void remove(int position){
        list.remove(position);
    }

    public boolean contains(NewsList.StoriesBean newsItem){
        return list.contains(newsItem);
    }
}
