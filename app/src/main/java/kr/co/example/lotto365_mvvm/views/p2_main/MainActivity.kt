package kr.co.example.lotto365_mvvm.views.p2_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.ViewModelFactory
import kr.co.example.lotto365_mvvm.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : LottoActivity<ActivityMainBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = binding.vm as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        Utils.loadAdView(binding.viewActivityMainAdView)

        getViewModel().startActivityCall.observe(this, Observer {

        })
    }


}