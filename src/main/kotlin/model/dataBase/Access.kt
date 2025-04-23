package model.dataBase

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class Access(
    @BsonId
    val _id: ObjectId = ObjectId(),
    var count: Int = 0,
)
