package com.aniketkadam.healthreadings.dao

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReadingsModule {

    @Binds
    @ViewModelScoped
    abstract fun provideDao(realmReadingsDao: RealmReadingsDao): ReadingsDao
}