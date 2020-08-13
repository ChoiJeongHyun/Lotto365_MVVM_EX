package kr.co.example.lotto365_mvvm.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.R

class ToolBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) :
    RelativeLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private lateinit var backgroundView: View
    private lateinit var lineView: View

    private var onToolBarClickListener: OnToolBarClickListener? = null
    private var viewCenterTitle: TextView? = null
    private var viewLeftTitle: TextView? = null
    private var viewRightTitle: TextView? = null
    private var viewLeftImage: StretchImageView? = null
    private var viewRightImage: StretchImageView? = null

    private var textCenterTitle: String? = null
    private var textLeftTitle: String? = null
    private var textRightTitle: String? = null

    private var imageLeftDrawable: Drawable? = null
    private var imageRightDrawable: Drawable? = null

    private var bottomLineSize: Int = 0
    private var bottomLineColor: Int = Color.TRANSPARENT
    private var titleCenterColor: Int = Color.WHITE
    private var titleLeftColor: Int = Color.WHITE
    private var titleRightColor: Int = Color.WHITE

    private var titleCenterBold: Boolean = true

    private var buttonItems = ArrayList<ButtonItem>()

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar, defStyleAttr, 0)
        textCenterTitle = typeArray.getString(R.styleable.ToolBar_toolbarTitle)
        textLeftTitle = typeArray.getString(R.styleable.ToolBar_toolbarLeftTitle)
        textRightTitle = typeArray.getString(R.styleable.ToolBar_toolbarRightTitle)

        imageLeftDrawable = typeArray.getDrawable(R.styleable.ToolBar_toolbarLeftSrc)
        imageRightDrawable = typeArray.getDrawable(R.styleable.ToolBar_toolbarRightSrc)

        bottomLineSize = typeArray.getDimensionPixelSize(R.styleable.ToolBar_toolbarBottomWidth, 0)
        titleCenterBold = typeArray.getBoolean(R.styleable.ToolBar_toolbarTitleBold, true)

        typeArray.recycle()
        viewInit()
    }

    private fun viewInit() {
        val height = Utils.dpToPx(context, 68).toInt()
        run {
            backgroundView = View(context)
            val params = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(backgroundView, params)
            backgroundView.alpha = 0f
        }

        run {
            val relativeLayout = RelativeLayout(context)
            val params = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            ).apply {
                addRule(CENTER_HORIZONTAL)
                addRule(ALIGN_PARENT_BOTTOM)
            }
            addView(relativeLayout, params)

            viewCenterTitle = TextView(context).apply {
                layoutParams = params
                gravity = Gravity.CENTER
                setTextColor(titleCenterColor)
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)

                if (titleCenterBold) {
                    setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD)
                }
                text = textCenterTitle
            }
            relativeLayout.addView(viewCenterTitle)

        }

        run {
            lineView = LinearLayout(context).apply {
                val params = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
                params.addRule(ALIGN_PARENT_BOTTOM)
                layoutParams = params
                setBackgroundColor(bottomLineColor)
            }
            addView(lineView)
        }

        //Left
        run {
            if (imageLeftDrawable != null) {
                val params = LayoutParams(
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight),
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight)
                ).apply {
                    addRule(ALIGN_PARENT_LEFT)
                    addRule(ALIGN_PARENT_BOTTOM)
                    addRule(CENTER_VERTICAL)
                }

                viewLeftImage = StretchImageView(context).apply {
                    setImageDrawable(imageLeftDrawable)
                    setPadding(
                        Utils.dpToPx(context, 14).toInt(),
                        Utils.dpToPx(context, 20).toInt(),
                        Utils.dpToPx(context, 0).toInt(),
                        Utils.dpToPx(context, 20).toInt()
                    )
                    setOnClickListener(this@ToolBar)
                }

                addView(viewLeftImage, params)

            } else if (textLeftTitle != null) {
                val params = LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight)
                ).apply {
                    addRule(ALIGN_PARENT_LEFT)
                    addRule(ALIGN_PARENT_BOTTOM)
                }

                viewLeftTitle = TextView(context).apply {
                    gravity = Gravity.CENTER
                    setTextColor(titleLeftColor)
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
                    text = textLeftTitle
                    setPadding(Utils.dpToPx(context, 20).toInt(), 0, 0, 0)
                }

                addView(viewLeftTitle, params)
            }

        }

        //Right
        run {
            if (imageRightDrawable != null) {
                val params = LayoutParams(
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight),
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight)
                ).apply {
                    addRule(ALIGN_PARENT_RIGHT)
                    addRule(ALIGN_PARENT_BOTTOM)
                }

                viewRightImage = StretchImageView(context).apply {
                    setImageDrawable(imageRightDrawable)
                    setPadding(
                        Utils.dpToPx(context, 14).toInt(),
                        Utils.dpToPx(context, 14).toInt(),
                        Utils.dpToPx(context, 18).toInt(),
                        Utils.dpToPx(context, 14).toInt()
                    )
                    setOnClickListener(this@ToolBar)
                }

                addView(viewRightImage, params)

            } else if (textRightTitle != null) {
                val params = LayoutParams(
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight),
                    resources.getDimensionPixelSize(R.dimen.toolbarHeight)
                ).apply {
                    addRule(ALIGN_PARENT_RIGHT)
                    addRule(ALIGN_PARENT_BOTTOM)
                }

                viewRightTitle = TextView(context).apply {
                    gravity = Gravity.CENTER
                    setTextColor(titleRightColor)
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
                    text = textRightTitle
                    setPadding(0, 0, Utils.dpToPx(context, 20).toInt(), 0)
                }
                addView(viewRightTitle, params)
            }
        }

    }


    interface OnToolBarClickListener {
        fun onToolBarClick(tag: Any?, view: View?)
    }

    fun setOnToolBarClickListener(onToolBarClickListener: OnToolBarClickListener) {
        this.onToolBarClickListener = onToolBarClickListener
    }


    fun setOnToolBarClickListener(function: (s: Int, ss: Int) -> String) {

    }

    fun setLeftButton(buttonItem: ButtonItem) {

        viewLeftImage?.let {
            it.setOnClickListener(this)
            buttonItem.view = viewLeftImage
            buttonItems.add(buttonItem)
        }

        viewLeftTitle?.let {
            it.setOnClickListener(this)
            buttonItem.view = viewLeftTitle
            buttonItems.add(buttonItem)
        }
    }

    fun setRightButton(buttonItem: ButtonItem) {
        viewRightImage?.let {
            it.setOnClickListener(this)
            buttonItem.view = viewRightImage
            buttonItems.add(buttonItem)
        }

        viewRightTitle?.let {
            it.setOnClickListener(this)
            buttonItem.view = viewRightTitle
            buttonItems.add(buttonItem)
        }
    }

    fun getLeftImage() = viewLeftImage
    fun getRightImage() = viewRightImage


    class ButtonItem {
        var tag: Any? = null
            private set
        var view: View? = null
        var resource: Int = -1
        var text: String? = null
            private set

        constructor()

        constructor(resource: Int) {
            this.resource = resource
        }

        constructor(text: String) {
            this.text = text
        }

        fun setTag(tag: Any?): ButtonItem {
            this.tag = tag
            return this
        }
    }

    override fun onClick(v: View?) {
        buttonItems.let {
            for (i in it.indices) {
                if (Utils.equals(it[i].view, v)) {
                    onToolBarClickListener?.let { its ->
                        its.onToolBarClick(it[i].tag, v)
                    } ?: run {

                    }
                }
            }
        }
    }


}