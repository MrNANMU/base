package com.dasong.common.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {

    private String tag = "net";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.d(tag, "---> "+request.method()+" "+request.url()+""+chain.connection().protocol());

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.d(tag,"<--- "+response.code()+" "+response.message()+response.request().url()+" ("+(t2-t1)+"ms) ");

        return response;
    }
}
