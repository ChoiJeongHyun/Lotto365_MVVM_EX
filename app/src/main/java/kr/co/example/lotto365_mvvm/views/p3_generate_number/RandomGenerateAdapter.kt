package kr.co.example.lotto365_mvvm.views.p3_generate_number

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.BaseViewHolder
import kr.co.example.lotto365_mvvm.databinding.ItemNumberBinding

class RandomGenerateAdapter(var items: ArrayList<String>, val vm: RandomGenerateViewModel) :
    RecyclerView.Adapter<RandomGenerateAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.vm = vm
    }

    inner class ItemViewHolder(view: View) : BaseViewHolder<ItemNumberBinding>(view)


}