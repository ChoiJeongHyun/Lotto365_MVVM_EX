package kr.co.example.lotto365_mvvm.views.in_common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.R
import kr.co.example.lotto365_mvvm.common.components.BaseViewHolder
import kr.co.example.lotto365_mvvm.common.components.BaseViewModel
import kr.co.example.lotto365_mvvm.databinding.ActivityMainBinding
import kr.co.example.lotto365_mvvm.databinding.ActivitySplashBinding
import kr.co.example.lotto365_mvvm.databinding.ItemListPageBinding
import kr.co.example.lotto365_mvvm.views.p1_splash.SplashViewModel
import kr.co.example.lotto365_mvvm.views.p2_main.MainViewModel

class ListAdapter(var items: ArrayList<String>, val vm: BaseViewModel) :
    RecyclerView.Adapter<ListAdapter.ItemList>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemList(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_page, parent, false)
    )

    override fun onBindViewHolder(holder: ItemList, position: Int) {

        holder.binding.item = items[position]
        holder.binding.vm = vm

    }

    inner class ItemList(view: View) : BaseViewHolder<ItemListPageBinding>(view)


}