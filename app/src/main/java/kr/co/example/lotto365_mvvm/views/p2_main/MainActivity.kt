package kr.co.example.lotto365_mvvm.views.p2_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.ViewModelFactory
import kr.co.example.lotto365_mvvm.databinding.ActivityMainBinding
import kr.co.example.lotto365_mvvm.views.p3_generate_number.GenerateActivity
import kr.co.example.lotto365_mvvm.views.p4_history_number.HistoryNumberActivity
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
            when (it) {
                0 -> sendAction(GenerateActivity::class.java)
                1 -> sendAction(HistoryNumberActivity::class.java)
                2 -> Toast.makeText(this, "Todo2", Toast.LENGTH_SHORT).show()

            }
        })
    }






}