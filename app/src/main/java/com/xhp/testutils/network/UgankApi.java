package com.xhp.testutils.network;

import android.database.Observable;

import com.xhp.testutils.bean.Category;
import com.xhp.testutils.bean.SearchResult;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UgankApi {

    @GET("data/{category}/{number}/{pag}")
    Response<Category> getCategoryDate(@Path("category") String category, @Path("number") int number, @Path("page") int page);

    @GET("random/data/福利/{number}")
    Response<Category> getRandomBeauties(@Path("number") int number);

    @GET("search/query/{key}/category/all/count/{count}/page/{page}")
    Observable<SearchResult> getSearchResult(@Path("key") String key, @Path("count") int count, @Path("page") int page);

}
