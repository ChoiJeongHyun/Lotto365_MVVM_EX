package kr.co.example.lotto365_mvvm.common.components

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun onClick(any: Any)

    abstract fun toolbarEvent(position: Int, any: Any?)

}