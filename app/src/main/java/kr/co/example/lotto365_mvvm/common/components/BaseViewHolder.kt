package kr.co.example.lotto365_mvvm.common.components

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out B : ViewDataBinding>(v: View) : RecyclerView.ViewHolder(v), LifecycleOwner {
    val binding: B = DataBindingUtil.bind(v)!!

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    open fun onAttach(){
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    open fun onDetach(){
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }
}