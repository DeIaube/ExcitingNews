package com.example.anull.excitingnews.network;

import com.example.anull.excitingnews.bean.NewsDetail;
import com.example.anull.excitingnews.bean.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by null on 2016/7/18.
 */
public interface ZhiHuApi {
    @GET("api/4/news/latest")
    Observable<NewsList> latest();
    @GET("api/4/news/{id}")
    Observable<NewsDetail> detail(@Path("id") String id);
    @GET("api/4/news/before/{data}")
    Observable<NewsList> befare(@Path("data") String data);
}
