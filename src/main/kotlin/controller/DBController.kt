package controller

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.empty
import com.mongodb.client.model.Filters.gt
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import model.dataBase.Access
import model.dataBase.User
import org.bson.BsonInt64
import org.bson.Document
import screens.log


object DBController {

    val mongo = MongoClient.create("mongodb://kartohhka:123s456o789s0@192.168.1.74:27777/?authSource=admin")

    private val database = mongo.getDatabase("kinesio_helper_compose") //normal java driver usage

    private val accessDoc = database.getCollection<Access>("access")
    private val userDoc = database.getCollection<User>("user")

    fun getAccessDoc(): MongoCollection<Access> {
        return accessDoc
    }

    fun getAllAccess() = runBlocking {
        log("1")
        val accessDoc = getAccessDoc()
        log("2")
        val access = accessDoc.find(gt(Access::count.name, 5)).firstOrNull()
        log("3")
        return@runBlocking access
    }

    fun getUserDoc(): MongoCollection<User> {
        return userDoc
    }
}

suspend fun setupConnection(
    databaseName: String = "kinesio_helper_compose",
    connectionEnvVariable: String = "mongodb://kartohhka:123s456o789s0@192.168.1.74:27777/?authSource=admin"
): MongoDatabase? {
    val connectString = if (System.getenv(connectionEnvVariable) != null) {
        System.getenv(connectionEnvVariable)
    } else {
        "mongodb://kartohhka:123s456o789s0@192.168.1.74:27777/?authSource=admin"
    }
    val client = MongoClient.create(connectionString = connectString)
    val database = client.getDatabase(databaseName = databaseName)

    return try {
        // Send a ping to confirm a successful connection
        val command = Document("ping", BsonInt64(1))
        database.runCommand(command)
        log("Pinged your deployment. You successfully connected to MongoDB!")
        println("Pinged your deployment. You successfully connected to MongoDB!")
        database
    } catch (me: MongoException) {
        System.err.println(me)
        log(me.stackTraceToString())
        null
    }
}