package kr.co.example.lotto365_mvvm.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kr.co.example.lotto365_mvvm.di.ViewModelKey
import kr.co.example.lotto365_mvvm.views.p1_splash.SplashViewModel
import kr.co.example.lotto365_mvvm.views.p2_main.MainViewModel
import kr.co.example.lotto365_mvvm.views.p3_generate_number.GenerateViewModel
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateViewModel
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomNumberViewModel
import kr.co.example.lotto365_mvvm.views.p4_history_number.HistoryNumberViewModel


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

    @Binds
    @IntoMap
    @ViewModelKey(GenerateViewModel::class)
    abstract fun bindGenerateViewModel(generateViewModel: GenerateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RandomGenerateViewModel::class)
    abstract fun bindRandomGenerateViewModel(randomGenerateViewModel: RandomGenerateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RandomNumberViewModel::class)
    abstract fun bindRandomNumberViewModel(randomNumberViewModel: RandomNumberViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryNumberViewModel::class)
    abstract fun bindHistoryNumberViewModel(historyNumberViewModel: HistoryNumberViewModel): ViewModel
}