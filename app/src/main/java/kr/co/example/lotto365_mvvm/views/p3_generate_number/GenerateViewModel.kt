package kr.co.example.lotto365_mvvm.views.p3_generate_number

import androidx.lifecycle.LiveData
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.repository.AppRepository
import kr.co.example.lotto365_mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class GenerateViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {

    val items = arrayListOf("꿈 분석", "랜덤 생성")

    private val _startActivityCall = SingleLiveEvent<Int>()
    val startActivityCall: LiveData<Int>
        get() = _startActivityCall

    private val _backPressedCall = SingleLiveEvent<Boolean>()
    val backPressedCall
        get() = _backPressedCall


    override fun onClick(any: Any) {
        if (any is String) {
            when (any) {
                "꿈 분석"  -> _startActivityCall.value = 0
                "랜덤 생성" -> _startActivityCall.value = 1
            }
        }

    }

    override fun toolbarEvent(position: Int, any: Any?) {
        _backPressedCall.value = true
    }


}