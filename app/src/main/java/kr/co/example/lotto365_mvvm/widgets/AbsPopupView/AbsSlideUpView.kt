package kr.co.example.lotto365.commonset.components.abspopupview

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Utils.Utils
import java.util.*

abstract class AbsSlideUpView(private val context: Context) : PopupView, View.OnClickListener {
    private lateinit var contentView: View
    private lateinit var background: View

    private lateinit var title: String


    private val dismissViews: ArrayList<View>? = null

    init {
        this.let {
            (context as LottoActivity<*>).addPopup(it)

            viewInit()
        }
    }

    private fun viewInit() {
        background = View(context)
        contentView = contentView()


        contentView.let {
            Utils.overrideFonts(it)
            run {
                background.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                background.setBackgroundColor(Color.parseColor("#80000000"))
                background.alpha = 0.0f
                background.setOnClickListener(this)
                ((context as Activity).findViewById<View>(android.R.id.content) as? ViewGroup)!!.addView(
                    background
                )
            }

            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            it.y = Utils.getScreenHeight(context).toFloat()
            ((context as Activity).findViewById<View>(android.R.id.content) as? ViewGroup)!!.addView(
                it
            )
        }


    }


    fun getContext() = context

    fun getActivity() = context as Activity

    fun backgroundClickDisable() {
        contentView.setOnClickListener(this)
    }

    fun getBackground() = background

    fun getContentView() = contentView

    fun setData() {}

    fun preAnimation() {}

    fun show(duration: Long, interpolator: TimeInterpolator) {

        onCreateView()

        val animator =
            ObjectAnimator.ofFloat(contentView, "y", Utils.getScreenHeight(context) / 1.0f, 0.0f)
        animator.duration = duration
        animator.interpolator = interpolator
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                this@AbsSlideUpView.setData()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {
                preAnimation()
            }
        })
        animator.start()
        background.animate().alpha(1.0f).setDuration(duration).setInterpolator(interpolator).start()

    }

    fun show(duration: Long) = this.show(duration, AccelerateDecelerateInterpolator())

    open fun show() = this.show(400, AccelerateDecelerateInterpolator())

    fun dimiss(duration: Long) {

        val animator =
            ObjectAnimator.ofFloat(contentView, "y", 0.0f, Utils.getScreenHeight(context) / 1.0f)
        animator.apply {
            this.duration = duration
            this.interpolator = interpolator
        }

        val animator2 = ObjectAnimator.ofFloat(background, "alpha", 1.0f, 0.0f)
        animator2.apply {
            this.duration = duration
            this.interpolator = interpolator
        }

        val animatorSet = AnimatorSet()
        animatorSet.duration = duration
        animatorSet.playTogether(animator, animator2)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                ((context as Activity).findViewById<View>(android.R.id.content) as? ViewGroup)!!.removeView(
                    background
                )
                ((context as Activity).findViewById<View>(android.R.id.content) as? ViewGroup)!!.removeView(
                    contentView
                )
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }
        })
        animatorSet.start()

    }

    fun dismiss() = this.dimiss(400)

    fun <T : View> findViewById(resId: Int): T = contentView.findViewById(resId)

    override fun onBackPressed() {
        dismiss()
    }

    abstract fun contentView(): View

    abstract fun onCreateView()

    override fun onClick(v: View) {
        if (dismissViews != null) {
            if (dismissViews.contains(v)) getActivity().onBackPressed()
        }
        if (v === background) {
            getActivity().onBackPressed()
        }
    }


}


