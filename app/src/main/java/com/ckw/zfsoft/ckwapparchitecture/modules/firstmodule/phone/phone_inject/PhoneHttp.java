package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.phone_inject;

/**
 * Created by ckw
 * on 2018/1/30.
 */

public class PhoneHttp {

//    public PhoneHttp(Context context) {
//        this.context = context;
//    }
//
//    private  Context context;
//    private  String baseUrl = Config.URL.BASE_URL;
//
//    private HttpManager mHttpManager = new HttpManager();
//
//    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//
//    private Interceptor parameterInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Interceptor.Chain chain) throws IOException {
//            Request request;
//            Request originalRequest = chain.request();
//            String method = originalRequest.method();
//            String token = DbHelper.getAppToken(context);
//            String userId = DbHelper.getUserId(context);
//            if (method != null && method.equals("GET")) {
//                HttpUrl.Builder builder = originalRequest.url().newBuilder();
//                HttpUrl newHttpUrl = builder.build();
//                int size = newHttpUrl.querySize();
//                for (int i = 0; i < size; i++) {
//                    String name = newHttpUrl.queryParameterName(i);
//                    String value = newHttpUrl.queryParameterValue(i);
//                    builder.setQueryParameter(name, CodeUtil.getEncodedValueWithToken(value, token));
//                }
//                newHttpUrl = builder
//                        .addQueryParameter("username", CodeUtil.getEncodedValueWithToken(userId, token))
//                        .addQueryParameter("strKey", CodeUtil.getEncodedValueWithToken(Config.STRkEY, token))
//                        .addQueryParameter("apptoken", token)
//                        .build();
//                request = originalRequest.newBuilder().url(newHttpUrl).build();
//                return chain.proceed(request);
//            } else if (method != null && method.equals("POST")) {
//                RequestBody body = originalRequest.body();
//                if (body != null && body instanceof FormBody) {
//                    FormBody.Builder formBuilder = new FormBody.Builder();
//                    FormBody formBody = (FormBody) body;
//                    int size = formBody.size();
//                    for (int i = 0; i < size; i++) {
//                        String name = formBody.name(i);
//                        String value = formBody.value(i);
//                        formBuilder.add(name, CodeUtil.getEncodedValueWithToken(value, token));
//                    }
//                    formBody = formBuilder
//                            .add("username", CodeUtil.getEncodedValueWithToken(userId, token))
//                            .add("strKey", CodeUtil.getEncodedValueWithToken(Config.STRkEY, token))
//                            .add("apptoken", token)
//                            .build();
//                    request = originalRequest.newBuilder().post(formBody).build();
//                    return chain.proceed(request);
//                }
//            }
//            return chain.proceed(originalRequest);
//        }
//    };
//
//    private Interceptor cacheInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Interceptor.Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetworkUtils.isConnected(context)) {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build();
//            }
//            okhttp3.Response response = chain.proceed(request);
//            if (NetworkUtils.isConnected(context)) {
//                CacheControl cacheControl = request.cacheControl();
//                String value = cacheControl.toString();
//                String noCache = "public, max-age=" + 0;
//                response = response.newBuilder()
//                        .header("Cache-Control", (value == null || value.trim().length() == 0) ? noCache : value)
//                        .build();
//            } else {
//                response = response.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 24 * 60 * 60 * 7)
//                        .build();
//            }
//            return response;
//        }
//    };
//
//    private OkHttpClient httpClient = new OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(cacheInterceptor)
//            .addInterceptor(parameterInterceptor)
//            .addNetworkInterceptor(cacheInterceptor)
//            .addNetworkInterceptor(new StethoInterceptor())
//            .build();
//
//    public Retrofit mRetrofit = new Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(httpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build();


}
