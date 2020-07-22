package kr.co.example.lotto365_mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import kotlinx.coroutines.*
import kr.co.example.lotto365.miniframework.database.dbmanager.AppDatabase
import kr.co.example.lotto365_mvp.MiniFrameWork.DataBase.Entity.History
import kr.co.example.lotto365_mvvm.di.ApplicationContext
import kr.co.example.lotto365_mvvm.miniframework.DataBase.DAO.HistoryDAO
import kr.co.example.lotto365_mvvm.miniframework.DataBase.PrefDatabase
import javax.inject.Inject
import javax.inject.Singleton

import kotlin.collections.ArrayList


@Singleton
class AppRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val historyDAO: HistoryDAO by lazy {
        val db = AppDatabase.getInstance()
        db.historyDAO()
    }

    private val localDB: PrefDatabase by lazy {
        val db = PrefDatabase(context)
        db
    }

    fun select(uniqueId: String): History = historyDAO.select(uniqueId)

    fun selectAll(): LiveData<List<History>> = historyDAO.selectAll()

    suspend fun insert(history: History) {
//        = Observable.fromCallable { historyDAO.insert(history) }
        coroutineScope {
            launch { historyDAO.insert(history) }
        }
    }

    suspend fun insertAll(historys: ArrayList<History>) {
//        Observable.fromCallable { historyDAO.insertAll(historys) }
        coroutineScope {
            launch { historyDAO.insertAll(historys) }
        }

    }


    suspend fun delete(uniqueId: String) {
//        = Observable.fromCallable { historyDAO.delete(uniqueId) }
        coroutineScope {
            launch { historyDAO.delete(uniqueId) }
        }
    }

    suspend fun deleteAll() {
//        = Observable.fromCallable { historyDAO.deleteAll() }
        coroutineScope {
            launch { historyDAO.deleteAll() }
        }
    }

    fun getPrefToken() = localDB.getToken()
    fun getPrefExceptNumber() = localDB.getExceptNumber()
    fun getPrefFixedNumber() = localDB.getFixedNumber()
    fun saveToken(token: String) = localDB.saveToken(token)

}