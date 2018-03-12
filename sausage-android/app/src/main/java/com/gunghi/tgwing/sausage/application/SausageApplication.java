package com.gunghi.tgwing.sausage.application;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.gunghi.tgwing.sausage.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class SausageApplication extends Application {

    private static volatile SausageApplication mInstance = null;
    private static Retrofit retrofit = null;

    // TODO: 2017. 8. 26. url 입력 
    private static final String BASE_URL = "";
    public static SausageApplication getGlobalApplicationContext() {
        if(mInstance == null)
            throw new IllegalStateException("this application does not inherit HawkiApplication");
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Log.d("SausageApplication"," onCreate");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Spoqa Han Sans Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Stetho.initializeWithDefaults(this);
    }

    @Override
    public void onTerminate() {
        mInstance = null;
        super.onTerminate();
    }

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient().newBuilder()
                            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .addNetworkInterceptor(new StethoInterceptor()).build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }



}

