package kr.co.example.lotto365_mvvm.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kr.co.example.lotto365.commonset.components.LottoApplication
import kr.co.example.lotto365_mvvm.di.module.ActivityBindingModule
import kr.co.example.lotto365_mvvm.di.module.AppModule
import kr.co.example.lotto365_mvvm.di.module.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ViewModelModule::class, ActivityBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

//        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: LottoApplication)
}