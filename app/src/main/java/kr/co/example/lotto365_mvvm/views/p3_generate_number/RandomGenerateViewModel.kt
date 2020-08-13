package kr.co.example.lotto365_mvvm.views.p3_generate_number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.example.lotto365.vo.Number
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.repository.AppRepository
import kr.co.example.lotto365_mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class RandomGenerateViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {

    private val _backPressedCall = SingleLiveEvent<Boolean>()
    val backPressedCall
        get() = _backPressedCall

    private val _fixedNumbers: MutableLiveData<ArrayList<String>> = MutableLiveData(ArrayList(appRepository.getPrefFixedNumber()))
    val fixedNumbers: LiveData<ArrayList<String>>
        get() = _fixedNumbers

    private val _exceptNumbers: MutableLiveData<ArrayList<String>> = MutableLiveData(ArrayList(appRepository.getPrefExceptNumber()))
    val exceptNumbers: LiveData<ArrayList<String>>
        get() = _exceptNumbers

    private val _showPopup: MutableLiveData<String> = MutableLiveData()
    val showPopup: LiveData<String>
        get() = _showPopup

    private val _popupNumbers: MutableLiveData<ArrayList<Number>> = MutableLiveData()
    val popupNumbers: LiveData<ArrayList<Number>>
        get() = _popupNumbers


    private val _isFixed: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFixed: LiveData<Boolean>
        get() = _isFixed

    private val _number: MutableLiveData<Number> = MutableLiveData()
    val number: LiveData<Number>
        get() = _number

    private val _itemClickMsg: MutableLiveData<String> = MutableLiveData()
    val itemCLickMsg: LiveData<String>
        get() = _itemClickMsg

    private val _startActivityCall = SingleLiveEvent<Int>()
    val startActivityCall: LiveData<Int>
        get() = _startActivityCall

    init {
        setData()
    }

    private fun setData() {

        val list: ArrayList<Number> = arrayListOf()
        for (i in 0..44) {
            var n = Number()
            n.position = (i + 1).toString()

            when {
                _fixedNumbers.value!!.contains(n.position)  -> {
                    n.isFixed = true
                    n.isEXCEPT = false
                }
                _exceptNumbers.value!!.contains(n.position) -> {
                    n.isFixed = false
                    n.isEXCEPT = true
                }
                else                                        -> {
                    n.isFixed = false
                    n.isEXCEPT = false
                }
            }

            list.add(n)

            //            _popupNumbers.value!!.add(n)
        }
        _popupNumbers.value = list
    }


    override fun onClick(any: Any) {
        if (any is String) {
            _showPopup.value = any
            _isFixed.value = any == "고정번호"
        } else if ( any is Int){
            _startActivityCall.value = 0
        }
    }

    override fun toolbarEvent(position: Int, any: Any?) {
        _backPressedCall.value = true
    }

    fun numberPopupItemClick(item: Number) {
        if (isFixed.value!!) {
            if (item.isEXCEPT) {
                _itemClickMsg.value = "제외번호입니다."
                return
            }

            if (fixedNumbers.value!!.size == 5) {
                if (!item.isFixed) {
                    _itemClickMsg.value = "최대 5개까지 선택가능합니다."
                    return
                }
            }

            if (item.isFixed) fixedNumbers.value!!.remove(item.position) else fixedNumbers.value!!.add(item.position)
            item.isFixed = !item.isFixed

        } else {

            if (item.isFixed) {
                _itemClickMsg.value = "고정번호입니다."
                return
            }

            if (exceptNumbers.value!!.size == 35) {
                if (!item.isFixed) {
                    _itemClickMsg.value = "최대 35개까지 선택가능합니다."
                    return
                }
            }

            if (item.isEXCEPT) exceptNumbers.value!!.remove(item.position) else exceptNumbers.value!!.add(item.position)
            item.isEXCEPT = !item.isEXCEPT
        }

        _popupNumbers.value!![item.position.toInt() - 1] = item
        _popupNumbers.value = _popupNumbers.value


    }

    fun popUpOnSaveClick() {
        fixedNumbers.value!!.sortWith(Comparator { t, t2 -> t.toInt().compareTo(t2.toInt()) })
        exceptNumbers.value!!.sortWith(Comparator { t, t2 -> t.toInt().compareTo(t2.toInt()) })

        appRepository.savePrefFixedNumber(fixedNumbers.value!!)
        appRepository.savePrefExceptNumber(exceptNumbers.value!!)

        _fixedNumbers.value = ArrayList(appRepository.getPrefFixedNumber())
        _exceptNumbers.value = ArrayList(appRepository.getPrefExceptNumber())
        _backPressedCall.value = true


    }

    fun popUpOnCancelClick() {
        _fixedNumbers.value = ArrayList(appRepository.getPrefFixedNumber())
        _exceptNumbers.value = ArrayList(appRepository.getPrefExceptNumber())
        setData()
        _backPressedCall.value = true
    }

    fun popUpOnBackPressed() {
        _fixedNumbers.value = ArrayList(appRepository.getPrefFixedNumber())
        _exceptNumbers.value = ArrayList(appRepository.getPrefExceptNumber())
        setData()
    }

    fun popUpOnResetClick() {
        if (isFixed.value!!) {
            _popupNumbers.value!!.forEach {
                it.isFixed = false
            }
            _fixedNumbers.value!!.clear()
        } else {
            _popupNumbers.value!!.forEach {
                it.isEXCEPT = false
            }
            _exceptNumbers.value!!.clear()
        }
        _popupNumbers.value = _popupNumbers.value
    }




}