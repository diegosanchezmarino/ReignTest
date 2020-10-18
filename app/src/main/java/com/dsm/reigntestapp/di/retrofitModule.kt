package com.dsm.reigntestapp.di

import com.dsm.reigntestapp.network.Apis
import com.dsm.reigntestapp.network.deserializer.PostsDeserializer
import com.dsm.reigntestapp.network.deserializer.PostsResponse
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

val retrofitModule = module {

    single { provideRetrofit(get(), get()) }
    single { provideGson() }
    single { provideHttpClient() }
}


fun provideHttpClient(): OkHttpClient {

    return OkHttpClient.Builder()
        .readTimeout(15L, SECONDS)
        .connectTimeout(15L, SECONDS)
        .callTimeout(15L, SECONDS)
        .writeTimeout(15L, SECONDS)
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

fun provideGson(): GsonConverterFactory {

    var gsonBuilder = GsonBuilder().also {
        it.registerTypeHierarchyAdapter(PostsResponse::class.java, PostsDeserializer())
    }

    return GsonConverterFactory.create(gsonBuilder.create())

}

fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, client: OkHttpClient): Apis {
    return Retrofit.Builder()
        .baseUrl("https://hn.algolia.com/api/v1/")
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()
        .create(Apis::class.java)
}

