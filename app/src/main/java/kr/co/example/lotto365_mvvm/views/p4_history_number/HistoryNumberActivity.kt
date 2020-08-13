package kr.co.example.lotto365_mvvm.views.p4_history_number

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.ViewModelFactory
import kr.co.example.lotto365_mvvm.databinding.ActivityHistoryNumberBinding
import javax.inject.Inject

class HistoryNumberActivity : LottoActivity<ActivityHistoryNumberBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getLayoutId() = R.layout.activity_history_number

    override fun getViewModel() = binding.vm as HistoryNumberViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this, factory).get(HistoryNumberViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        Utils.loadAdView(binding.viewHistoryNumberAdView)

        getViewModel().backPressedCall.observe(this , Observer {
            if (it){
                onBackPressed()
            }
        })

    }


}