package kr.co.example.lotto365_mvvm.views.p4_history_number

import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.miniframework.database.entity.History
import kr.co.example.lotto365_mvvm.repository.AppRepository
import kr.co.example.lotto365_mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class HistoryNumberViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {

    private val _backPressedCall = SingleLiveEvent<Boolean>()
    val backPressedCall
        get() = _backPressedCall

    private var _historyItems: MutableLiveData<List<History>> = MutableLiveData(arrayListOf())
    val historyItems: LiveData<List<History>>
        get() = _historyItems

    private val _showMsg: MutableLiveData<String> = MutableLiveData()
    val showMsg: LiveData<String>
        get() = _showMsg

    //    val _testItem: LiveData<List<History>> = appRepository.selectTC()

    init {
        viewModelScope.launch {
            var list = MutableLiveData(appRepository.selectAll()!!)
            _historyItems.value = list.value
        }
    }


    override fun onClick(any: Any) {

    }

    override fun toolbarEvent(position: Int, any: Any?) {
        _backPressedCall.value = true
    }


    fun historyRemove(uniqueId: String) {
        viewModelScope.launch {
            appRepository.delete(uniqueId)
            _historyItems.value = MutableLiveData(appRepository.selectAll()!!).value
        }
    }

    fun historyAllRemove(){
        viewModelScope.launch {
            appRepository.deleteAll()
            _historyItems.value = MutableLiveData(appRepository.selectAll()!!).value
        }
    }

}