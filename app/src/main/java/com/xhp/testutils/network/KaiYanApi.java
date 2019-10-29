package com.xhp.testutils.network;

import com.xhp.testutils.bean.FindBean;
import com.xhp.testutils.bean.HomeBean;
import com.xhp.testutils.bean.HotBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KaiYanApi {

    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Call<HomeBean> getHomeData();

    //首页更多
    //    @GET("v2/feed?date={date}&num={num}")
    @GET("v2/feed")
    Call<HomeBean>  getHomeMoreData(@Query("date") String date ,@Query("num") String num );

    //获取发现频道信息
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    Call<List<FindBean>> getFindData();

    //获取关键词搜索相关信息
    //@GET("v2/feed?query={query}&num={num}&start={start}")
    @GET("v1/search")
    Call<HotBean> getSearchData(@Query("num") int num , @Query("query")String query,
                                @Query("start")int start ) ;



}
