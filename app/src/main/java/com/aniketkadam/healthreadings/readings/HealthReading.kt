package com.aniketkadam.healthreadings.readings

import com.aniketkadam.healthreadings.serializer.DateAsLongSerializer
import com.aniketkadam.healthreadings.serializer.ObjectIdSerializer
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.util.*

@RealmClass
@Serializable
open class HealthReading : RealmModel {
    @PrimaryKey
    @Serializable(with = ObjectIdSerializer::class)
    var _id: ObjectId = ObjectId.get()
    var temperature: Float? = null
    var respiratoryRate: Int? = null
    var oxygenation: Int? = null
    var pulse: Int? = null

    @Serializable(with = DateAsLongSerializer::class)
    var date: Date = Date()
    var userId: String = ""
}