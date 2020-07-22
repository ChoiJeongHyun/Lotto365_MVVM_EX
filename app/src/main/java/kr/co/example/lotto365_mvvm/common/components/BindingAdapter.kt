package kr.co.example.lotto365_mvvm.common.components

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365_mvp.Utils.PLog


@BindingAdapter(value = ["items", "viewModel"])
fun setItems(recyclerView: RecyclerView, items: ArrayList<String>, vm: BaseViewModel) {
    PLog.e("items : $items")
    PLog.e("recyclerView : $recyclerView")
    PLog.e("vm : $vm")
    PLog.e("check adapter ? ${recyclerView.adapter}")

    recyclerView.adapter?.run {
        (this as kr.co.example.lotto365_mvvm.views.in_common.ListAdapter).items = items
        this.notifyDataSetChanged()
    } ?: run {
        kr.co.example.lotto365_mvvm.views.in_common.ListAdapter(items, vm).apply { recyclerView.adapter = this }
    }

}