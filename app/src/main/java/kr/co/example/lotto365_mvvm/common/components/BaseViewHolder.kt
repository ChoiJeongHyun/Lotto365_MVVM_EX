package kr.co.example.lotto365_mvvm.common.components

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out B : ViewDataBinding>(v: View) : RecyclerView.ViewHolder(v) {
    val binding: B = DataBindingUtil.bind(v)!!


}