package kr.co.example.lotto365_mvvm.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import kr.co.example.lotto365.miniframework.database.dbmanager.AppDatabase
import kr.co.example.lotto365_mvvm.di.ApplicationContext
import kr.co.example.lotto365_mvvm.miniframework.database.PrefDatabase
import kr.co.example.lotto365_mvvm.repository.AppRepository
import javax.inject.Singleton

@Module
class AppModule {
//    (private val context: Context)

//    @Provides
//    @Singleton
//    @ApplicationContext
//    fun provideContext() = context

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(application: Application) = application

//    @Provides
//    @Singleton
//    fun provideAppRepository(appRepository: AppRepository) = appRepository

    @Provides
    @Singleton
    fun provideAppRepository(appDatabase: AppDatabase , prefDatabase: PrefDatabase) = AppRepository(appDatabase, prefDatabase)

//    @Provides
//    @Singleton
//    fun provideAppDataBase(application: Application) =
//        Room.databaseBuilder(application, AppDatabase::class.java, "Lotto365_MVVM.db")
//            .addMigrations().build()

    @Provides
    @Singleton
    fun provideAppDataBase() = AppDatabase.getInstance()


    @Provides
    @Singleton
    fun providePrefDataBase(application: Application) = PrefDatabase(application)

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase) = appDatabase.historyDAO()

}