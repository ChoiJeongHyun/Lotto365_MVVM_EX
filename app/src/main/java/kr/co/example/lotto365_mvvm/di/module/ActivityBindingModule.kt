package kr.co.example.lotto365_mvvm.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.co.example.lotto365_mvvm.di.ActivityScope
import kr.co.example.lotto365_mvvm.views.p1_splash.SplashActivity
import kr.co.example.lotto365_mvvm.views.p2_main.MainActivity


@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity() : SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}