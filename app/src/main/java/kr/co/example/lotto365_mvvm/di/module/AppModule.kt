package kr.co.example.lotto365_mvvm.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kr.co.example.lotto365_mvvm.di.ApplicationContext
import kr.co.example.lotto365_mvvm.miniframework.DataBase.PrefDatabase
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext() = context

    @Provides
    @Singleton
    fun providePrefDataBase(prefDatabase: PrefDatabase) = prefDatabase


}