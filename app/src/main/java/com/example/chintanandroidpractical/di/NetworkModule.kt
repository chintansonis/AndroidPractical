package com.example.chintanandroidpractical.di

import android.content.Context
import coil.ImageLoader
import com.example.chintanandroidpractical.network.RequestInterceptor
import com.example.chintanandroidpractical.network.service.ProductService
import com.example.chintanandroidpractical.utils.AppConstants
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .addInterceptor(logging)
      .build()
  }

  @Provides
  @Singleton
  fun provideImageLoader(
    @ApplicationContext context: Context,
    okHttpClient: OkHttpClient
  ): ImageLoader {
    return ImageLoader.Builder(context)
      .okHttpClient { okHttpClient }
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okhHttpClient)
      .baseUrl(AppConstants.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }



  @Provides
  @Singleton
  fun provideProductService(retrofit: Retrofit): ProductService {
    return retrofit.create(ProductService::class.java)
  }

}
