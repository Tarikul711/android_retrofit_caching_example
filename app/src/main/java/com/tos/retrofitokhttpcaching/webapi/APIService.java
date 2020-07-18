package com.tos.retrofitokhttpcaching.webapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    public static <T> T createService(final Class<T> clazz, final String endPoint) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(10, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(clazz);
    }

}
