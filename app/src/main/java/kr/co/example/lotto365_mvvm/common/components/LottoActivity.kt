package kr.co.example.lotto365.commonset.components

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import kr.co.example.lotto365.commonset.components.abspopupview.PopupView
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

abstract class LottoActivity<T : ViewDataBinding> : FragmentActivity(), View.OnClickListener {

    enum class Transition {
        NO_ANIMATION, SLIDE_SIDE, SLIDE_UP
    }


    protected var transition = Transition.SLIDE_SIDE
    protected lateinit var binding: T
    private var popupViewStack: Stack<PopupView> = Stack()
    private lateinit var contentV: View
    private var fadeIn = false
    private var statusTransparent = false

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        contentV = LayoutInflater.from(this).inflate(getLayoutId(), null)
        Utils.overrideFonts(contentV)
        super.setContentView(contentV)
        setStatusColor()
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }


    protected fun childViewAllFadeIn(v: View) {
        val animations = ArrayList<Animator>()
        val animatorSet = AnimatorSet()
        animatorSet.duration = 400
        if (v is ViewGroup) {
            for (i in 0..v.childCount) {
                val child = v.getChildAt(i)
                child.alpha = 0.0f
                animations.add(ObjectAnimator.ofFloat(child, "alpha", 1.0f))
            }
        }
        animatorSet.playTogether(animations)
        animatorSet.start()
    }

    protected fun childViewAllFadeOut(v: View) {
        val animations = ArrayList<Animator>()
        val animatorSet = AnimatorSet()
        animatorSet.duration = 400
        if (v is ViewGroup) {
            for (i in 0..v.childCount) {
                val child = v.getChildAt(i)
                animations.add(ObjectAnimator.ofFloat(child, "alpha", 0.0f))
            }
        }
        animatorSet.playTogether(animations)
        animatorSet.start()
    }

    override fun onBackPressed() {
        popupViewStack.let {
            if (!it.isEmpty()) {
                onRemovePopup(popupViewStack.peek())
                popupViewStack.pop().onBackPressed()
            } else {
                if (fadeIn) {
                    childViewAllFadeOut((this.contentV as ViewGroup).getChildAt(0))
                    Handler().postDelayed(Runnable {
                        finish()
                        setExitTransition()
                    }, 400)
                } else {
                    try {
                        super.onBackPressed()
                        setExitTransition()
                    } catch (e: NullPointerException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun setEnterTransition() {
        if (transition == Transition.NO_ANIMATION) overridePendingTransition(0, 0)
        if (transition == Transition.SLIDE_SIDE) overridePendingTransition(
            R.anim.right_in,
            R.anim.left_out
        )
        if (transition == Transition.SLIDE_UP) overridePendingTransition(
            R.anim.bottom_in,
            R.anim.no
        )
    }

    private fun setExitTransition() {
        transition = Transition.values()[getInt("ACTIVITY_TRANSITION")]
        if (transition == Transition.NO_ANIMATION) overridePendingTransition(0, 0)
        if (transition == Transition.SLIDE_SIDE) overridePendingTransition(
            R.anim.left_in,
            R.anim.right_out
        )
        if (transition == Transition.SLIDE_UP) overridePendingTransition(
            R.anim.no,
            R.anim.bottom_out
        )
    }

    fun getInt(key: String) = getInt(key, 0)

    fun getInt(key: String, defVal: Int): Int {
        var retVal = defVal
        intent.extras?.let {
            retVal = it.getInt(key)
        }
        return retVal

    }

    fun getStringArray(key: String): ArrayList<String>? {

        intent.getStringArrayListExtra(key)?.let {
            return it
        }
        return ArrayList()

    }

    fun getSerialize(key: String): Serializable? {
        intent?.let {
            return it.getSerializableExtra(key)
        }
        return null
    }


    private fun setStatusColor() {
        try {
            val color = (this.contentV.background as ColorDrawable).color
            if (statusTransparent) {
                setTransparentStatus()
            } else {
                setStatusColor(color)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setStatusColor(color: Int) {
        window.statusBarColor = color
    }

    open fun setTransparentStatus() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.attributes.flags =
            window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()

        window.statusBarColor = Color.TRANSPARENT
    }

    fun onRemovePopup(popupView: PopupView) {

    }

    override fun onClick(v: View?) {

    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        intent?.putExtra("REQUEST_CODE", requestCode)
        super.startActivityForResult(intent, requestCode)
    }

    fun getRequestCode() = getInt("REQUEST_CODE", -1)

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (i in grantResults.indices) {
            val grantResult = grantResults[i]
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(permissions[i])) {
                        neverNotAllowed(requestCode, permissions[i])
                        return
                    }
                }
                permissionNotAllowed(requestCode, permissions[i])
                return
            }
        }

        permissionsAllowed(requestCode)
    }

    protected open fun neverNotAllowed(code: Int, permission: String?) {}

    protected open fun permissionsAllowed(code: Int) {}

    protected open fun permissionNotAllowed(code: Int, permission: String?) {}

    protected open fun requestPermissions(code: Int, vararg permissions: String?) {
        var isCheck = false
        for (permission in permissions) {
            isCheck = ActivityCompat.checkSelfPermission(
                this,
                permission!!
            ) != PackageManager.PERMISSION_GRANTED || isCheck
        }
        if (isCheck) {
            ActivityCompat.requestPermissions(this, permissions, code)
        } else {
            permissionsAllowed(code)
        }
    }


    protected open fun viewInit() {}

    protected open fun setData() {}

    fun setTransition(transition: Transition): LottoActivity<T> {
        this.transition = transition
        return this
    }

    private fun putTransitionToBundle(intent: Intent, transition: Transition) =
        intent.putExtra("ACTIVITY_TRANSITION", transition.ordinal)

    fun sendAction(targetClass: Class<*>?) {
        val intent = Intent(this, targetClass)
        startActivity(putTransitionToBundle(intent, transition))
        setEnterTransition()
    }

    fun sendAction(targetClass: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, targetClass)
        if (bundle != null) intent.putExtras(bundle)
        startActivity(putTransitionToBundle(intent, transition))
        setEnterTransition()
    }

    open fun sendAction(requestCode: Int, targetClass: Class<*>?) {
        val intent = Intent(this, targetClass)
        startActivityForResult(putTransitionToBundle(intent, transition), requestCode)
        setEnterTransition()
    }

    open fun sendAction(requestCode: Int, targetClass: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, targetClass)
        intent.putExtras(bundle!!)
        startActivityForResult(putTransitionToBundle(intent, transition), requestCode)
        setEnterTransition()
    }

    open fun sendActionUri(uri: String?) {
        if (uri == null) return
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(uri)
        startActivity(putTransitionToBundle(intent, transition))
        setEnterTransition()
    }

    open fun addPopup(popupView: PopupView?) {
        popupViewStack.push(popupView)
    }

    open fun setStatusTransparent(statusTransparent: Boolean) {
        this.statusTransparent = statusTransparent
    }


    fun getPopupViewStack() = popupViewStack

}