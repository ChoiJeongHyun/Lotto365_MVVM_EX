package kr.co.example.lotto365_mvvm.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kr.co.example.lotto365.miniframework.database.dbmanager.AppDatabase
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.miniframework.database.entity.History
import kr.co.example.lotto365_mvvm.miniframework.database.PrefDatabase
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository @Inject constructor(private val appDatabase: AppDatabase, private val prefDatabase: PrefDatabase) {

    //

    //    private val historyDAO: HistoryDAO by lazy {
    //        val db = AppDatabase.getInstance()
    //        db.historyDAO()
    //    }
    //
    //    private val localDB: PrefDatabase by lazy {
    //        val db = PrefDatabase(context)
    //        db
    //    }

    suspend fun selectTest(uniqueId: String): History? {

        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            appDatabase.historyDAO().select(uniqueId)
        }

        //        return d.await()

    }


    suspend fun select(uniqueId: String): History? {

        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            appDatabase.historyDAO().select(uniqueId)
        }

    }

    fun selectTC(): LiveData<List<History>>? {
        //        PLog.e("call data !!!!!! : ${appDatabase.historyDAO().selectAll().value}")
        return null
    }


    suspend fun selectAll(): List<History>? {
        //        return appDatabase.historyDAO().selectAll()

        //        return CoroutineScope(Dispatchers.IO).async {
        //            appDatabase.historyDAO().selectAll()
        //        }.await()

        //        CoroutineScope(Dispatchers.IO).async {
        //            appDatabase.historyDAO().selectAll()
        //        }.await()

        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            appDatabase.historyDAO().selectAll()
        }

        //        return null
    }

    fun insert(history: History) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.historyDAO().insert(history)
        }

    }

    fun insertAll(historys: ArrayList<History>) {
        //        Observable.fromCallable { historyDAO.insertAll(historys) }
        //        coroutineScope {
        //            launch { appDatabase.historyDAO().insertAll(historys) }
        //        }

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.historyDAO().insertAll(historys)
        }

    }


    suspend fun delete(uniqueId: String) {
        //        = Observable.fromCallable { historyDAO.delete(uniqueId) }

        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.historyDAO().delete(uniqueId)
        }

    }

    suspend fun deleteAll() {
        //        = Observable.fromCallable { historyDAO.deleteAll() }
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.historyDAO().deleteAll()
        }

    }

    fun getPrefToken() = prefDatabase.getToken()
    fun getPrefExceptNumber() = prefDatabase.getExceptNumber()
    fun getPrefFixedNumber() = prefDatabase.getFixedNumber()
    fun saveToken(token: String) = prefDatabase.saveToken(token)
    fun savePrefFixedNumber(list: ArrayList<String>) = prefDatabase.saveFixedNumber(list)
    fun savePrefExceptNumber(list: ArrayList<String>) = prefDatabase.saveExceptNumber(list)

}