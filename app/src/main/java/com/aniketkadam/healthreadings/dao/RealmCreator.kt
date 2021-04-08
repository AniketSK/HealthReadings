package com.aniketkadam.healthreadings.dao

import android.app.Application
import com.aniketkadam.healthreadings.realminit.SyncedRealmHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmCreator {

    @Singleton
    @Provides
    @Named("localRealm")
    fun provideRealm(application: Application): Realm {
        Realm.init(application)
        val realmName = "Diary"
        val config = RealmConfiguration.Builder()
            .name(realmName)
            .allowQueriesOnUiThread(false)
            .allowWritesOnUiThread(false)
            .build()
        Realm.setDefaultConfiguration(config)
        return Realm.getInstance(config)
    }

    @Singleton
    @Provides
    fun getSyncedRealmHelper(): SyncedRealmHelper =
        SyncedRealmHelper("introspectionandroidapp-hlhyo")

}
