package com.xhp.testutils.network;


import com.xhp.testutils.bean.Category;
import com.xhp.testutils.bean.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UgankApi {

    @GET("data/{category}/{number}/{pag}")
    Call<Category> getCategoryDate(@Path("category") String category, @Path("number") int number, @Path("page") int page);

    @GET("random/data/福利/{number}")
    Call<Category> getRandomBeauties(@Path("number") int number);

    @GET("search/query/{key}/category/all/count/{count}/page/{page}")
    Call<SearchResult> getSearchResult(@Path("key") String key, @Path("count") int count, @Path("page") int page);

}
