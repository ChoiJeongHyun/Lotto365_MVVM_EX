package kr.co.example.lotto365_mvvm.common.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365.vo.Number
import kr.co.example.lotto365_mvvm.miniframework.database.entity.History
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateAdapter
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomGenerateViewModel
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomNumberAdapter
import kr.co.example.lotto365_mvvm.views.p3_generate_number.RandomNumberViewModel
import kr.co.example.lotto365_mvvm.views.p3_generate_number.popup.PopupNumberAdapter
import kr.co.example.lotto365_mvvm.views.p4_history_number.HistoryNumberAdapter
import kr.co.example.lotto365_mvvm.views.p4_history_number.HistoryNumberViewModel
import kr.co.example.lotto365_mvvm.widgets.CircleImageView
import kr.co.example.lotto365_mvvm.widgets.ToolBar


@BindingAdapter(value = ["items", "viewModel"])
fun setItems(recyclerView: RecyclerView, items: ArrayList<String>, vm: BaseViewModel) {
    recyclerView.adapter?.run {
        (this as kr.co.example.lotto365_mvvm.views.in_common.ListAdapter).items = items
        this.notifyDataSetChanged()
    } ?: run {
        kr.co.example.lotto365_mvvm.views.in_common.ListAdapter(items, vm).apply { recyclerView.adapter = this }
    }
}

@BindingAdapter(value = ["numbers", "viewModel"])
fun setNumbers(recyclerView: RecyclerView, items: ArrayList<String>, vm: RandomGenerateViewModel) {
    recyclerView.adapter?.run {
        (this as RandomGenerateAdapter).items = items
        this.notifyDataSetChanged()
    } ?: run {
        RandomGenerateAdapter(items, vm).apply { recyclerView.adapter = this }
    }
}


@BindingAdapter(value = ["popupNumber", "viewModel"])
fun setPopupNumbers(recyclerView: RecyclerView, items: ArrayList<Number>?, vm: RandomGenerateViewModel) {
    recyclerView.adapter?.run {
        if (this is PopupNumberAdapter)
        //            this.submitList(items)
            notifyDataSetChanged()

    } ?: run {
        PopupNumberAdapter(vm).apply {
            recyclerView.adapter = this
            //            this.submitList(items)
            this.submitList(items)

        }
    }
}


@BindingAdapter(value = ["generateNumber", "viewModel"])
fun setGenerateNumbers(recyclerView: RecyclerView, items: ArrayList<ArrayList<String>>, vm: RandomNumberViewModel) {
    recyclerView.adapter?.run {
        if (this is RandomNumberAdapter) {
            notifyDataSetChanged()
        }
    } ?: run {
        RandomNumberAdapter(vm).apply {
            recyclerView.adapter = this
            this.submitList(items)
        }
    }
}


@BindingAdapter(value = ["historyItems", "viewModel"])
fun setHistoryItems(recyclerView: RecyclerView, items: List<History>, vm: HistoryNumberViewModel) {
    recyclerView.adapter?.run {
        if (this is HistoryNumberAdapter) {
            this.submitList(items)
            //            notifyDataSetChanged()
        }
    } ?: run {
        HistoryNumberAdapter(vm).apply {
            recyclerView.adapter = this
            this.submitList(items)
        }
    }
}


@BindingAdapter(value = ["params", "viewModel"])
fun onListBtnClick(roundButton: Button, params: Any, vm: BaseViewModel) {
    roundButton.setOnClickListener { vm.onClick(params) }
}


@BindingAdapter(value = ["leftClick", "viewModel"])
fun onToolbarLeftClick(toolbar: ToolBar, left: Boolean, vm: BaseViewModel) {
    toolbar.getLeftImage()!!.setOnClickListener { vm.toolbarEvent(0, null) }
}

@BindingAdapter(value = ["rightClick", "viewModel"])
fun onToolbarRightClick(toolbar: ToolBar, right: Boolean, vm: BaseViewModel) {
    toolbar.getRightImage()!!.setOnClickListener { vm.toolbarEvent(1, null) }
}

@BindingAdapter(value = ["isNumber"])
fun numberTextColor(textView: TextView, number: String) {
    textView.setTextColor(Utils.getNumberColor(number.toInt()))
}


@BindingAdapter(value = ["isFixed", "numberItem"])
fun showingPopupNumberBackground(circleImageView: CircleImageView, isFixed: Boolean, number: Number) {
    if (isFixed) {

        if (number.isFixed) {
            circleImageView.setImageDrawable(ColorDrawable(Utils.getNumberColor(number.position.toInt())))
        } else {
            circleImageView.setImageDrawable(ColorDrawable(Color.parseColor("#338B95A1")))
        }

    } else {

        if (number.isEXCEPT) {
            circleImageView.setImageDrawable(ColorDrawable(Utils.getNumberColor(number.position.toInt())))
        } else {
            circleImageView.setImageDrawable(ColorDrawable(Color.parseColor("#338B95A1")))
        }

    }
}

@BindingAdapter(value = ["isFixed", "numberItem"])
fun showingPopupNumberTextView(textView: TextView, isFixed: Boolean, number: Number) {
    if (isFixed) {

        if (number.isFixed) {
            textView.text = number.position
            textView.setTextColor(Color.parseColor("#191F28"))
        } else {
            textView.text = if (number.isEXCEPT) "" else number.position
            textView.setTextColor(Color.parseColor("#8B95A1"))
        }

    } else {

        if (number.isEXCEPT) {
            textView.text = number.position
            textView.setTextColor(Color.parseColor("#191F28"))
        } else {
            textView.text = if (number.isFixed) "" else number.position
            textView.setTextColor(Color.parseColor("#8B95A1"))
        }
    }
}

@BindingAdapter(value = ["allItems" , "listPosition"])
fun historyDateVisibility(textView: TextView, listItems: List<History>, itemPosition: Int) {

    try {
        if (itemPosition == 0) {
            textView.visibility = View.VISIBLE
            textView.text = Utils.dataFormat(listItems[itemPosition].historyDate, "yyyy-MM-dd")
        } else {
            if (Utils.dateCompare(listItems[itemPosition - 1].historyDate.toString(), listItems[itemPosition].historyDate.toString()) > 0) {
                textView.visibility = View.VISIBLE
                textView.text = Utils.dataFormat(listItems[itemPosition].historyDate, "yyyy-MM-dd")
            } else {
                textView.visibility = View.GONE
            }
        }


    } catch (e: Exception) {
        e.printStackTrace()
    }

}
