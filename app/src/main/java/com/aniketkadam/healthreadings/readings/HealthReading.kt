package com.aniketkadam.healthreadings.readings

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.bson.types.ObjectId

@RealmClass
open class HealthReading : RealmModel {
    @PrimaryKey
    var _id: ObjectId = ObjectId.get()
    var temperature: Float? = null
    var respiratoryRate : Int? = null
    var oxygenation : Int? = null
    var pulse : Int? = null
    var userId : String = ""
}