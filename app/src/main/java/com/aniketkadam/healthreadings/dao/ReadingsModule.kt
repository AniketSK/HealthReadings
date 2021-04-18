package com.aniketkadam.healthreadings.dao

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ReadingsModule {

    @Binds
//    @ViewModelScoped
    abstract fun provideDao(realmReadingsDao: RealmReadingsDao): ReadingsDao
}