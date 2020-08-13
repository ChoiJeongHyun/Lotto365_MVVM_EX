package kr.co.example.lotto365_mvvm.views.in_common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.BaseViewHolder
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.databinding.ItemListPageBinding

class ListAdapter(var items: ArrayList<String>, val vm: BaseViewModel) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_page, parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.binding.item = items[position]
        holder.binding.vm = vm



    }

    override fun onViewAttachedToWindow(holder: ItemViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder) {
        super.onViewDetachedFromWindow(holder)

    }

    inner class ItemViewHolder(view: View) : BaseViewHolder<ItemListPageBinding>(view)


}