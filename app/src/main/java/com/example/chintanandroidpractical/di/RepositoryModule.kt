package com.example.chintanandroidpractical.di

import com.example.chintanandroidpractical.database.SummaryDao
import com.example.chintanandroidpractical.network.service.ProductService
import com.example.chintanandroidpractical.repository.LocalDataSourceRepository
import com.example.chintanandroidpractical.repository.ProductSummaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

// dependency for repository throughout appp
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    // provide single instance of product summary repository throught the viewmodel scope
    @Provides
    @ViewModelScoped
    fun provideProductSummaryRepository(
        productService: ProductService,
        summaryDao: SummaryDao
    ): ProductSummaryRepository {
        return ProductSummaryRepository(productService, summaryDao)
    }

    // provide single instance of product local repository throught the viewmodel scope which observe viewmodel lifecycle
    @Provides
    @ViewModelScoped
    fun provideLocalDataSourceRepository(
        summaryDao: SummaryDao
    ): LocalDataSourceRepository {
        return LocalDataSourceRepository(summaryDao)
    }

}
