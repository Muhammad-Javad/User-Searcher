package com.javadsh98.usersearcher.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.javadsh98.usersearcher.BuildConfig
import com.javadsh98.usersearcher.data.remote.api.SearchApi
import com.javadsh98.usersearcher.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule{

    @Singleton
    @Provides
    fun getInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun getChucker(@ApplicationContext context: Context): ChuckerInterceptor{
        return ChuckerInterceptor(context)
    }

    @Singleton
    @Provides
    fun getClient(httpLoggingInterceptor: HttpLoggingInterceptor
                  , chuckerInterceptor: ChuckerInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG){
                    addInterceptor(httpLoggingInterceptor)
                    addInterceptor(chuckerInterceptor)
                }
            }.build()
    }

    inline fun <reified T> makeApi(client: OkHttpClient): T{
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }

    @Singleton
    @Provides
    fun getSearchUser(client: OkHttpClient): SearchApi{
        return makeApi<SearchApi>(client)
    }

    @Singleton
    @Provides
    fun getUser(client: OkHttpClient): UserApi{
        return makeApi<UserApi>(client)
    }

}