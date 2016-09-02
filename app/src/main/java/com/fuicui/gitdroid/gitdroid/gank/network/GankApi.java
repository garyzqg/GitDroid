package com.fuicui.gitdroid.gitdroid.gank.network;

import com.fuicui.gitdroid.gitdroid.gank.model.GankResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 123 on 2016/9/1.
 */
public interface GankApi {

    /**
     * Api 获取每日干货
     *
     每日数据： http://gank.io/api/day/年/月/日

     例：
     http://gank.io/api/day/2015/08/06
     http://gank.io/api/day/{年}/{月}/{日}


     http://baidu.com/{name}/image

     htpp://baidu.com/zzz/image
     */
    String BASE_URL = "http://gank.io/api/";

    /**
     * 请求每日数据的api
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Call<GankResult> getDailyData(@Path("year")int year,@Path("month")int month,@Path("day")int day);

}
