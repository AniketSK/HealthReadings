package com.aniketkadam.healthreadings.realminit


import io.realm.Realm
import io.realm.mongodb.*
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * SyncedRealmHelper will be injected anywhere Realm is expected.
 * Then the caller decides what partition they require and get it.
 *
 */
class SyncedRealmHelper(
    private val appId: String,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val realmDispatcher: CoroutineDispatcher = Realm.WRITE_EXECUTOR.asCoroutineDispatcher()
) {
    private val app = App(AppConfiguration.Builder(appId).build())

    var isLoggedIn = app.currentUser() != null
        private set

    private val realm: HashMap<String, Realm> = HashMap()

    init {
        if (app.currentUser() != null) {
            isLoggedIn = true
        }
    }

    fun getUserAfterLoginScreen(): User =
        app.currentUser()!! // Shouldn't be null for any reason after logging in.. mostly.

    suspend fun init(userName: String, password: String): InitializationState =
        withContext(ioDispatcher) {
            try {
                getUser(Credentials.emailPassword(userName, password))
                isLoggedIn = true
                InitializationState.SUCCESS
            } catch (e: AppException) {
                InitializationState.ERROR(e.errorMessage ?: "No message received")
            }
        }

    fun getRealmForPartition(partition: String): Realm =
        getRealm(partition, app.currentUser()!!)


    private suspend fun getUser(creds: Credentials): User =
        withContext(ioDispatcher) {
            return@withContext app.login(creds)
        }

    /**
     * It needs to be created on the realm executor thread anyway.
     */
    private fun getRealm(partition: String, user: User): Realm {


        val config = SyncConfiguration.Builder(user, partition)
            .allowQueriesOnUiThread(false)
            .allowWritesOnUiThread(false)
            .build()

        return Realm.getInstance(config)
    }

}