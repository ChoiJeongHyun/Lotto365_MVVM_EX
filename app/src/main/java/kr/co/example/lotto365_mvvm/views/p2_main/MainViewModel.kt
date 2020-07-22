package kr.co.example.lotto365_mvvm.views.p2_main

import androidx.lifecycle.LiveData
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    val items = arrayListOf("번호 생성", "내 번호", "당첨 확인(QR코드)")

    private val _startActivityCall = SingleLiveEvent<Int>()

    val startActivityCall: LiveData<Int>
        get() = _startActivityCall


    override fun onClick(any: Any) {


        if (any is String) {

            when (any) {
                "번호 생성" -> _startActivityCall.value = 0
                "내 번호" -> _startActivityCall.value = 1
                "당첨 확인(QR코드)" -> _startActivityCall.value = 2
            }
        }


    }


}