package kr.co.example.lotto365_mvvm.views.p3_generate_number

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kr.co.example.lotto365.vo.Number
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.BaseViewHolder
import kr.co.example.lotto365_mvvm.databinding.ItemGenerateNumberBinding
import java.util.ArrayList

class RandomNumberAdapter(val vm: RandomNumberViewModel) : ListAdapter<ArrayList<String>, RandomNumberAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_generate_number, parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.vm = vm
        holder.binding.item = getItem(position)
    }

    inner class ItemViewHolder(view: View) : BaseViewHolder<ItemGenerateNumberBinding>(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArrayList<String>>() {

            override fun areItemsTheSame(oldItem: ArrayList<String>, newItem: ArrayList<String>): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArrayList<String>, newItem: ArrayList<String>): Boolean {
                return oldItem == newItem
            }

        }
    }


}