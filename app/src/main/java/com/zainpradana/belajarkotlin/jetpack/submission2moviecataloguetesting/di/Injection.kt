package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.di

import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.LocalDataSource
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.DummyData

object Injection {
    fun provideRepository(): CatalogueRepository {

        val localDataSource = LocalDataSource.getInstance(DummyData)

        return CatalogueRepository.getInstance(localDataSource)
    }
}