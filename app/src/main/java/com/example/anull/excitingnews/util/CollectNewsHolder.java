package com.example.anull.excitingnews.util;

import com.example.anull.excitingnews.bean.NewsCollect;
import com.example.anull.excitingnews.bean.NewsList;

/**
 * Created by DESKTOP-1JBF8Q8$ on 2016/7/19.
 */
public class CollectNewsHolder {

    private CollectNewsHolder() {
        newsCollect = new NewsCollect();
    }

    private NewsCollect newsCollect;
    private String collectId;

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public boolean add(NewsList.StoriesBean newsItem){
        if(newsCollect.contains(newsItem)){
            return false;
        }
        newsCollect.add(newsItem);
        return true;
    }

    public boolean isEmpty(){
        return newsCollect.getList().isEmpty();
    }

    public void remove(NewsList.StoriesBean newsItem){
        newsCollect.remove(newsItem);
    }

    public NewsCollect getNewsCollect() {
        return newsCollect;
    }

    public void setNewsCollect(NewsCollect newsCollect) {
        this.newsCollect = newsCollect;
    }

    public static CollectNewsHolder getSingle(){
        return CollectNewsHolderHolder.collectNewsHolder;
    }

    static class CollectNewsHolderHolder{
        public static CollectNewsHolder collectNewsHolder = new CollectNewsHolder();
    }

}
