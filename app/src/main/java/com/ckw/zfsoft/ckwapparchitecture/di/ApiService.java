package com.ckw.zfsoft.ckwapparchitecture.di;

import com.ckw.zfsoft.ckwapparchitecture.NetLoader.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ckw
 * on 2017/12/7.
 * 接口文档
 */

public interface ApiService {

    @GET("article/details/78687032")
    Observable<Response<Object>> getCkwArticleInfo();
}
