package kr.co.example.lotto365_mvvm.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvvm.R


class RoundButton @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) :
    AppCompatButton(context, attrs, defStyleAttr), View.OnClickListener {

    private var state: Int = 0
    private var width: Float = 0f
    private var height: Float = 0f
    private var radius: Float = 0f
    private var cornerRadius: Float = 0f
    private var borderSize: Float = 0f

    private var paintBack: Paint = Paint()
    private var paintBoard: Paint = Paint()

    private var borderColorDisable: Int = Color.TRANSPARENT
    private var borderColorNormal: Int = Color.TRANSPARENT
    private var borderColorHighlight: Int = Color.TRANSPARENT
    private var backgroundColorDisable: Int = Color.TRANSPARENT
    private var backgroundColorNormal: Int = Color.TRANSPARENT
    private var backgroundColorHighlight: Int = Color.TRANSPARENT
    private var textColorDisable: Int = Color.TRANSPARENT
    private var textColorNormal: Int = Color.TRANSPARENT
    private var textColorHighlight: Int = Color.TRANSPARENT
    private var backgroundColorGradientStart: Int = Color.TRANSPARENT
    private var backgroundColorGradientEnd: Int = Color.TRANSPARENT


    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.RoundButton, defStyleAttr, 0)
        cornerRadius = typeArray.getDimensionPixelSize(R.styleable.RoundButton_cornerRadius, -1).toFloat()
        borderSize = typeArray.getDimensionPixelSize(R.styleable.RoundButton_borderWidth, 0).toFloat()
        borderColorDisable = typeArray.getColor(R.styleable.RoundButton_borderColorDisable, Color.TRANSPARENT)
        borderColorNormal = typeArray.getColor(R.styleable.RoundButton_borderColorNormal, Color.TRANSPARENT)
        borderColorHighlight = typeArray.getColor(R.styleable.RoundButton_borderColorHighlight, Color.TRANSPARENT)
        backgroundColorDisable = typeArray.getColor(R.styleable.RoundButton_backgroundColorDisable, Color.TRANSPARENT)
        backgroundColorNormal = typeArray.getColor(R.styleable.RoundButton_backgroundColorNormal, Color.TRANSPARENT)
        backgroundColorHighlight = typeArray.getColor(R.styleable.RoundButton_backgroundColorHighlight, Color.TRANSPARENT)
        textColorDisable = typeArray.getColor(R.styleable.RoundButton_textColorDisable, Color.WHITE)
        textColorNormal = typeArray.getColor(R.styleable.RoundButton_textColorNormal, Color.WHITE)
        textColorHighlight = typeArray.getColor(R.styleable.RoundButton_textColorHighlight, Color.WHITE)
        backgroundColorGradientStart = typeArray.getColor(
            R.styleable.RoundButton_backgroundColorNormalGradientStart, Color.TRANSPARENT
        )
        backgroundColorGradientEnd = typeArray.getColor(
            R.styleable.RoundButton_backgroundColorNormalGradientEnd, Color.TRANSPARENT
        )
        typeArray.recycle()
        viewInit()
    }

    fun viewInit() {
        state = MotionEvent.ACTION_UP
        paintBack.apply {
            color = backgroundColorNormal
            style = Paint.Style.FILL
        }

        paintBoard.apply {
            color = borderColorNormal
            style = Paint.Style.STROKE
            strokeWidth = borderSize
        }

        super.setBackgroundColor(Color.TRANSPARENT)
        super.setTextColor(textColorNormal)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        radius = cornerRadius

        if (radius == 0f) radius = if (height > width) (width - borderSize) / 2f else (height - borderSize) / 2f
        setMeasuredDimension(
            MeasureSpec.getSize(width.toInt()), MeasureSpec.getSize(height.toInt())
        )
    }

    override fun setTextColor(color: Int) {
        super.setTextColor(color)
        textColorNormal = color
        textColorHighlight = color

        typeface = Typeface.DEFAULT_BOLD
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        backgroundColorNormal = color
        backgroundColorHighlight = color

        invalidate()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (!enabled) {
            super@RoundButton.setTextColor(textColorDisable)
            if (textColorNormal == textColorDisable) {
                invalidate()
            }
        } else {
            super@RoundButton.setTextColor(textColorNormal)
            if (textColorNormal == textColorDisable) {
                invalidate()
            }
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        radius = cornerRadius
        if (radius == 0f) radius = if (height > width) (width - borderSize) / 2f else (height - borderSize) / 2f

        if (!this.isEnabled) {
            paintBack.color = backgroundColorDisable
            paintBoard.color = borderColorDisable

            canvas?.drawRoundRect(
                RectF(
                    borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f
                ), radius, radius, paintBack
            )

            canvas?.drawRoundRect(
                RectF(
                    borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f
                ), radius, radius, paintBoard
            )
        }

        if (isSelected) {
            state = MotionEvent.ACTION_DOWN
        }

        when (state) {
            MotionEvent.ACTION_DOWN -> {
                paintBack.color = backgroundColorHighlight
                paintBoard.color = borderColorHighlight
                canvas?.drawRoundRect(
                    RectF(
                        borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f
                    ), radius, radius, paintBack
                )

                canvas?.drawRoundRect(
                    RectF(
                        borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f
                    ), radius, radius, paintBoard
                )
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                paintBack.color = backgroundColorNormal
                paintBoard.color = borderColorNormal


                if (backgroundColorGradientStart != Color.TRANSPARENT && backgroundColorGradientEnd != Color.TRANSPARENT) {
                    val gradient = LinearGradient(
                        0f, 0f, width, 0f, backgroundColorGradientStart, backgroundColorGradientEnd, Shader.TileMode.CLAMP
                    )
                    paintBack.isDither = true
                    paintBack.shader = gradient
                }
                canvas?.drawRoundRect(
                    RectF(
                        borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f
                    ), radius, radius, paintBack
                )

                canvas?.drawRoundRect(
                    RectF(
                        borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f
                    ), radius, radius, paintBoard
                )

            }
        }

        super.onDraw(canvas)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        setOnTouchListener(onTouchListener)
    }

    @SuppressLint("ClickableViewAccessibility")
    private val onTouchListener = OnTouchListener { _, event ->
        PLog.e("onTouch call")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                state = event.action
                super@RoundButton.setBackgroundColor(Color.TRANSPARENT)
                super@RoundButton.setTextColor(textColorHighlight)
                if (textColorHighlight == textColorNormal) {
                    invalidate()
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                state = event.action
                super@RoundButton.setBackgroundColor(Color.TRANSPARENT)
                super@RoundButton.setTextColor(textColorNormal)
                if (textColorHighlight == textColorNormal) {
                    invalidate()
                }
            }
        }
        false
    }


    override fun onClick(v: View?) {
    }

}