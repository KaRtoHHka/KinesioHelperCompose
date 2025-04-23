package model.dataBase

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class User(
    @BsonId
    val _id: Id<User> = newId(),
    var title: String = "",
)
