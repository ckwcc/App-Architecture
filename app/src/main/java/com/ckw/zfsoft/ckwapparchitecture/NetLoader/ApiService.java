package com.ckw.zfsoft.ckwapparchitecture.NetLoader;

import com.ckw.zfsoft.ckwapparchitecture.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ckw
 * on 2017/12/12.
 * 网络请求api接口
 */

public interface ApiService {
    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return Response<User>对象
     */
    @FormUrlEncoded
    @POST("zftal-mobile/commonHttp/commonHttp_login.html")
    Observable<Response<User>> login(@Field("username") String username, @Field("password")
            String password, @Field("strKey") String strKey);
}
