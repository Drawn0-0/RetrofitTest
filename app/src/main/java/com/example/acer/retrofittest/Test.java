package com.example.acer.retrofittest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by acer on 2017/4/9.
 */

public interface Test {
    @GET("book/search")//设置请求为get，后面的括号可写可不写，写了那么就会将基本的url和括弧参数拼接到一起
    Call<BookSearchResponse> getSearchBooks(@Query("q") String name,//BookSearchResponse是一个类
                                            @Query("tag") String tag,//@Query表示请求参数，将会以key=value的方式拼接在url后面
                                            @Query("start") int start,
                                            @Query("count") int count);
}
