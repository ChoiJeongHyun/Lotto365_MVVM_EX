package kr.co.example.lotto365_mvvm.views.p3_generate_number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.example.lotto365_mvvm.miniframework.database.entity.History
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.repository.AppRepository
import kr.co.example.lotto365_mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class RandomNumberViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {

    private val _randomNumberItems: MutableLiveData<ArrayList<ArrayList<String>>> = MutableLiveData(arrayListOf(arrayListOf()))
    val randomNumberItems: MutableLiveData<ArrayList<ArrayList<String>>>
        get() = _randomNumberItems

    private val _showMsg: MutableLiveData<String> = MutableLiveData()
    val showMsg: LiveData<String>
        get() = _showMsg

    private val _backPressedCall = SingleLiveEvent<Boolean>()
    val backPressedCall
        get() = _backPressedCall

    init {
        setItems()
    }

    fun setItems() {
        var randomArrayList: ArrayList<String>

        _randomNumberItems.value!!.clear()

        loopMain@ while (_randomNumberItems.value!!.size < 5) {
            randomArrayList = arrayListOf()
            randomArrayList.addAll(appRepository.getPrefFixedNumber())
            loopSub@ while (randomArrayList.size < 6) {
                var randomNumber = (1..45).shuffled().first()
                if (!randomArrayList.contains(randomNumber.toString()) && !appRepository.getPrefExceptNumber().contains(randomNumber.toString())) {
                    randomArrayList.add(randomNumber.toString())
                }
            }
            randomArrayList.sortWith(Comparator { t, t2 -> t.toInt().compareTo(t2.toInt()) })

            loopFor@ for (i in 0 until _randomNumberItems.value!!.size) {
                if (_randomNumberItems.value!![i].containsAll(randomArrayList)) {
                    continue@loopMain
                }
            }

            _randomNumberItems.value!!.add(randomArrayList)
        }

        _randomNumberItems.value = _randomNumberItems.value


    }


    override fun onClick(any: Any) {

    }

    override fun toolbarEvent(position: Int, any: Any?) {
        _backPressedCall.value = true
    }

    fun saveNumber(numberItem: ArrayList<String>) {


        val currentTime = System.currentTimeMillis()
        val id = Utils.sha256(Utils.dataFormat(currentTime, "yyyyMMdd") + numberItem)!!


        viewModelScope.launch {
            if (appRepository.select(id) != null) {
                _showMsg.value = "이미 저장되어 있거나 일부분 저장되어 있습니다."
                return@launch
            }

            val history = History()
            history.apply {
                uniqueId = id
                historyDate = currentTime
                numbers = numberItem
                type = "Random"
            }

            appRepository.insert(history)
            _showMsg.value = "저장되었습니다."
        }

    }

    fun saveAllNumber() {
        val historyArrayList = arrayListOf<History>()
        val currentTime = System.currentTimeMillis()


        viewModelScope.launch {

            randomNumberItems.value!!.forEach {
                val id = Utils.sha256(Utils.dataFormat(currentTime, "yyyyMMdd") + it)!!
                if (appRepository.select(id) != null) {
                    _showMsg.value = "이미 저장되어 있거나 일부분 저장되어 있습니다."
                    return@launch
                }
            }

            randomNumberItems.value!!.forEach {
                val id = Utils.sha256(Utils.dataFormat(currentTime, "yyyyMMdd") + it)!!
                val h = History()
                h.apply {
                    uniqueId = id
                    historyDate = currentTime
                    numbers = it
                    type = "Random"
                    historyArrayList.add(h)
                }
            }

            appRepository.insertAll(historyArrayList)
            _showMsg.value = "저장되었습니다."


        }

    }

}