package com.example.anull.excitingnews.network;

import com.example.anull.excitingnews.config.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by null on 2016/7/18.
 */
public class ZhiHuRequest {
    private ZhiHuApi zhiHuApi;
    private OkHttpClient client;
    private ZhiHuRequest() {
        client = new OkHttpClient.Builder()
                .readTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(6, TimeUnit.SECONDS)
                .connectTimeout(6, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        zhiHuApi = retrofit.create(ZhiHuApi.class);
    }

    public ZhiHuApi getZhiHuApi() {
        return zhiHuApi;
    }

    public static ZhiHuRequest getSingle(){
        return ZhiHuRequestHolder.request;
    }

    static class ZhiHuRequestHolder {
        public static ZhiHuRequest request = new ZhiHuRequest();
    }

}
