package com.xhp.testutils.network;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static UgankApi gankApi;
    private static KaiYanApi kaiYanApi;

    public static UgankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(gsonConverterFactory)
                    .baseUrl("http://gank.io/api/")
                    .client(okHttpClient)
                    .build();
            gankApi = retrofit.create(UgankApi.class);
        }
        return gankApi;
    }

    public static KaiYanApi getKaiYanApi(){
        if (kaiYanApi == null) {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://baobab.kaiyanapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            kaiYanApi =retrofit.create(KaiYanApi.class);
        }
        return kaiYanApi;
    }

}
