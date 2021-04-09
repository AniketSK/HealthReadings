package com.aniketkadam.healthreadings.dao

import com.aniketkadam.healthreadings.readings.HealthReading
import com.aniketkadam.healthreadings.realminit.SyncedRealmHelper
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import javax.inject.Inject

class RealmReadingsDao @Inject constructor(private val realmHelper: SyncedRealmHelper) :
    ReadingsDao {

    override fun getAllReadings(): Flow<List<HealthReading>> =
        realmHelper.getRealmForPartition(realmHelper.getUserAfterLoginScreen().id)
            .where(HealthReading::class.java)
            .sort(HealthReading::_id.name, Sort.DESCENDING)
            .findAllAsync()
            .toFlow()
            .flowOn(Dispatchers.Main)

    override fun submitReading(healthReading: HealthReading) {
        realmHelper.getRealmForPartition(realmHelper.getUserAfterLoginScreen().id)
            .executeTransactionAsync {
                it.copyToRealmOrUpdate(healthReading.apply {
                    userId = realmHelper.getUserAfterLoginScreen().id
                })
            }
    }

    override suspend fun getReading(id: String): HealthReading? =
        withContext(Realm.WRITE_EXECUTOR.asCoroutineDispatcher()) {
            var healthReading: HealthReading? = null

            realmHelper.getRealmForPartition(realmHelper.getUserAfterLoginScreen().id)
                .use { realm ->

                    realm.where(HealthReading::class.java)
                        .equalTo(HealthReading::_id.name, ObjectId(id))
                        .findFirst()?.let { nonNullExistingReading ->
                            healthReading = realm.copyFromRealm(nonNullExistingReading)
                        }
                }

            healthReading
        }

}