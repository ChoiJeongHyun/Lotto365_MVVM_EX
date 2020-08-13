package kr.co.example.lotto365_mvvm.views.p4_history_number

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.miniframework.database.entity.History
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.BaseViewHolder
import kr.co.example.lotto365_mvvm.databinding.ItemHistoryNumberBinding

class HistoryNumberAdapter(val vm: HistoryNumberViewModel): ListAdapter<History, HistoryNumberAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_history_number, parent , false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.vm = vm
        holder.binding.item = getItem(position)
        holder.binding.position = position
        holder.binding.executePendingBindings()
    }

    inner class ItemViewHolder(view: View): BaseViewHolder<ItemHistoryNumberBinding>(view)

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>(){
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

        }
    }



}
