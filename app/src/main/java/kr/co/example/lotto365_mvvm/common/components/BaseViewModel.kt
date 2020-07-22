package kr.co.example.lotto365_mvvm.common.components

import androidx.lifecycle.ViewModel
import kr.co.example.lotto365_mvvm.repository.AppRepository
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var appRepository: AppRepository


    abstract fun onClick(any: Any)

}