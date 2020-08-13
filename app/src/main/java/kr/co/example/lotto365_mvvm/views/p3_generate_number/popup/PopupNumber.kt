package kr.co.example.lotto365_mvvm.views.p3_generate_number.popup

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365.commonset.components.abspopupview.AbsFadeInView
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.databinding.PopupNumberBinding
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateActivity
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateViewModel

class PopupNumber(context: Context) : AbsFadeInView(context), View.OnClickListener {


    private lateinit var binding: PopupNumberBinding
    private lateinit var viewModel: RandomGenerateViewModel

    private var title: String = ""
    private var isFixed = true


    override fun contentView(): View = View.inflate(getContext(), R.layout.popup_number, null)

    override fun onCreateView() {

        binding = DataBindingUtil.bind(getContentView())!!

    }

    fun setViewModel(viewModel: RandomGenerateViewModel): PopupNumber {
        this.viewModel = viewModel
        return this
    }

    fun setTitle(s: String): PopupNumber {
        this.title = s
        return this
    }

    fun setFixed(isFixed: Boolean): PopupNumber {
        this.isFixed = isFixed
        return this
    }

    override fun show() {
        super.show()
        binding.vm = viewModel
        binding.lifecycleOwner = getActivity() as RandomGenerateActivity


        binding.popupNumberViewTextTitle.text = title


    }


}