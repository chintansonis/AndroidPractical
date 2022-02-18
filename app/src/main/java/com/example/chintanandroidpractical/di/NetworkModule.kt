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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// dependency for network instance throughout appp
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
//    val logging = HttpLoggingInterceptor()
//    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      // enable this line and intercaptor library for loggin
      //.addInterceptor(logging)
      .build()
  }

  // provide single instance of imageloader
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

  // provide single instance of retrofit
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


  // provide single instance of product api service
  @Provides
  @Singleton
  fun provideProductService(retrofit: Retrofit): ProductService {
    return retrofit.create(ProductService::class.java)
  }

}
