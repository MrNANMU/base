package com.dasong.common.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private OkHttpClient mOkHttpClient;
    private Retrofit.Builder mRetrofitBuilder;
    private static Api instance;

    private Api(){
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(HttpConfig.READ_TIMEOUT,TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
        mRetrofitBuilder = new Retrofit.Builder();
        mRetrofitBuilder.client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    private static Api getInstance(){
        if(instance == null){
            synchronized (Api.class){
                if(instance == null){
                    instance = new Api();
                }
            }
        }
        return instance;
    }

    public static <T> T create(String url,Class<T> api){
        if(instance == null){
            instance = getInstance();
        }
        return instance.mRetrofitBuilder.baseUrl(url).build().create(api);
    }
}
