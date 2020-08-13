package kr.co.example.lotto365_mvvm.views.p3_generate_number

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.ViewModelFactory
import kr.co.example.lotto365_mvvm.databinding.ActivityRandomNumberBinding
import javax.inject.Inject

class RandomNumberActivity : LottoActivity<ActivityRandomNumberBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getLayoutId() = R.layout.activity_random_number

    override fun getViewModel() = binding.vm as RandomNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this, factory).get(RandomNumberViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        Utils.loadAdView(binding.viewRandomNumberAdView)

        getViewModel().showMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        getViewModel().backPressedCall.observe(this, Observer {
            if (it) {
                onBackPressed()
            }
        })


    }
}