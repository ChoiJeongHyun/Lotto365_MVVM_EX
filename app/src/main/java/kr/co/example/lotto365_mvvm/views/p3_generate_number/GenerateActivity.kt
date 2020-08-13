package kr.co.example.lotto365_mvvm.views.p3_generate_number

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
import kr.co.example.lotto365_mvvm.databinding.ActivityGenerateBinding
import javax.inject.Inject

class GenerateActivity : LottoActivity<ActivityGenerateBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getLayoutId() = R.layout.activity_generate

    override fun getViewModel() = binding.vm as GenerateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this, factory).get(GenerateViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        Utils.loadAdView(binding.viewActivityGenerateAdView)

        getViewModel().startActivityCall.observe(this, Observer {
            when (it) {
                0 -> Toast.makeText(this, "Todo", Toast.LENGTH_SHORT).show()
                1 -> sendAction(RandomGenerateActivity::class.java)
            }
        })

        getViewModel().backPressedCall.observe(this, Observer {
            if (it) {
                onBackPressed()
            }
        })


    }


}