package kr.co.example.lotto365_mvvm.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kr.co.example.lotto365_mvvm.di.ViewModelKey
import kr.co.example.lotto365_mvvm.views.p1_splash.SplashViewModel
import kr.co.example.lotto365_mvvm.views.p2_main.MainViewModel


@Module
abstract class ViewModelModule {





    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}