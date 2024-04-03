package com.example.countryinfo.Api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    public static final String BASE_URL="https://countryapi.io/api/";
    public static final String API_KEY="OBBOT6DH2wev8z3HDGEWivROUwaMlfVJ8bjfz4zF";

    public static Retrofit retrofit= null;

    public static ApiInterface getApiInterface() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + API_KEY)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
