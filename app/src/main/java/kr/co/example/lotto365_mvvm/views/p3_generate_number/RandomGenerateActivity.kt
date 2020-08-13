package kr.co.example.lotto365_mvvm.views.p3_generate_number

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.popup_number.*
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.ViewModelFactory
import kr.co.example.lotto365_mvvm.databinding.ActivityRandomGenerateBinding
import kr.co.example.lotto365_mvvm.views.p3_generate_number.popup.PopupNumber
import javax.inject.Inject

class RandomGenerateActivity : LottoActivity<ActivityRandomGenerateBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getLayoutId() = R.layout.activity_random_generate

    override fun getViewModel() = binding.vm as RandomGenerateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val v = ViewModelProvider(this, factory).get(RandomGenerateViewModel::class.java)

        binding.vm = v
        binding.lifecycleOwner = this

        Utils.loadAdView(binding.viewRandomGenerateAdView)

        viewInit()


        getViewModel().backPressedCall.observe(this, Observer {
            if (it) {
                this.onBackPressed()
            }
        })

        getViewModel().showPopup.observe(this, Observer {
            when (it) {
                "고정번호" -> PopupNumber(this).setTitle(it).setFixed(true).setViewModel(getViewModel()).show()
                "제외번호" -> PopupNumber(this).setTitle(it).setFixed(false).setViewModel(getViewModel()).show()
            }
        })

        getViewModel().itemCLickMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        getViewModel().startActivityCall.observe(this , Observer {
            sendAction(RandomNumberActivity::class.java)
        })


    }

    override fun viewInit() {
        super.viewInit()
        binding.viewRandomGenerateGroupExceptNumber.setAlWaysMyTouch(true)
        binding.viewRandomGenerateGroupFixedNumber.setAlWaysMyTouch(true)
    }

    override fun onBackPressed() {
        if (!getPopupViewStack().empty()) {
            getViewModel().popUpOnBackPressed()
            super.onBackPressed()
            return
        }
        super.onBackPressed()
    }


}