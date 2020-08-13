package kr.co.example.lotto365_mvvm.views.p1_splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.ViewModelFactory
import kr.co.example.lotto365_mvvm.databinding.ActivitySplashBinding
import kr.co.example.lotto365_mvvm.views.p2_main.MainActivity
import javax.inject.Inject


class SplashActivity : LottoActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getLayoutId() = R.layout.activity_splash

    override fun getViewModel() = binding.vm as SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this, factory).get(SplashViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        getViewModel().setFcm()
        getViewModel().startActivityCall.observe(this, Observer {
            if (it) {
                sendAction(MainActivity::class.java)
                finish()
            }
        })

    }
}