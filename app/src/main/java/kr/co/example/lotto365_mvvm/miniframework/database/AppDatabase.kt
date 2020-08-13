package kr.co.example.lotto365.miniframework.database.dbmanager

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kr.co.example.lotto365_mvvm.miniframework.database.entity.History
import kr.co.example.lotto365_mvvm.miniframework.database.Converter.Converts
import kr.co.example.lotto365_mvvm.miniframework.database.DAO.HistoryDAO
import javax.inject.Singleton


@Database(entities = [History::class], version = 1, exportSchema = false)
@TypeConverters(Converts::class)
@Singleton
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        private const val DB_NAME = "LottoDataBaseMVVM.db"

        fun init(context: Context) {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, DB_NAME
                    ).addMigrations(MIGRATION_1_2).build()
                }
            }
        }

        fun getInstance() = instance!!

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }

    abstract fun historyDAO(): HistoryDAO


}