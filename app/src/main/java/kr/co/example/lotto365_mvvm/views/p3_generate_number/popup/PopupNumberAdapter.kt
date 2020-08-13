package kr.co.example.lotto365_mvvm.views.p3_generate_number.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365.vo.Number
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.BaseViewHolder
import kr.co.example.lotto365_mvvm.databinding.ItemPopupNumberBinding
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateActivity

import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateViewModel

class PopupNumberAdapter(val vm: RandomGenerateViewModel) : ListAdapter<Number, PopupNumberAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_popup_number, parent, false)
    )


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.binding.number = getItem(position)
//        holder.binding.number = getItem(position).value
        holder.binding.vm = vm
        holder.binding.executePendingBindings()



    }

    inner class ItemViewHolder(view: View) : BaseViewHolder<ItemPopupNumberBinding>(view)


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Number>() {
            override fun areItemsTheSame(oldItem: Number, newItem: Number): Boolean =
                oldItem.isFixed == newItem.isFixed

            override fun areContentsTheSame(oldItem: Number, newItem: Number): Boolean =
                oldItem == newItem


        }
    }


}