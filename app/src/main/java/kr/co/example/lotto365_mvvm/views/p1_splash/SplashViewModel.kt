package kr.co.example.lotto365_mvvm.views.p1_splash

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.Single
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _startActivityCall = SingleLiveEvent<Boolean>()

    val startActivityCall: LiveData<Boolean>
        get() = _startActivityCall


    fun setFcm() {

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }

            it.result?.let { result ->
                if (appRepository.getPrefToken() == "") {
                    appRepository.saveToken(result.token)
                } else {
                    if (appRepository.getPrefToken() != result.token) {
                        appRepository.saveToken(result.token)
                    }
                }
                Utils.postDelayed({
                    _startActivityCall.value = true
                }, 500)

            }
        }
    }

    override fun onClick(any: Any) {
        TODO("Not yet implemented")
    }

}