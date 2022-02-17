package com.example.chintanandroidpractical.di

import com.example.chintanandroidpractical.database.SummaryDao
import com.example.chintanandroidpractical.network.service.ProductService
import com.example.chintanandroidpractical.repository.ProductSummaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideProductSummaryRepository(
    productService: ProductService,
    summaryDao: SummaryDao
  ): ProductSummaryRepository {
    return ProductSummaryRepository(productService, summaryDao)
  }

}
