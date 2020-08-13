package kr.co.example.lotto365_mvvm.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.co.example.lotto365_mvvm.di.ActivityScope
import kr.co.example.lotto365_mvvm.views.p1_splash.SplashActivity
import kr.co.example.lotto365_mvvm.views.p2_main.MainActivity
import kr.co.example.lotto365_mvvm.views.p3_generate_number.GenerateActivity
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateActivity
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomNumberActivity
import kr.co.example.lotto365_mvvm.views.p4_history_number.HistoryNumberActivity


@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity() : SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun generateActivity() : GenerateActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun randomGenerateNumberActivity(): RandomGenerateActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun randomNumberActivity(): RandomNumberActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun historyNumberActivity(): HistoryNumberActivity

}