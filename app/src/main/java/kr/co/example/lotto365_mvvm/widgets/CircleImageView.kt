package kr.co.example.lotto365_mvvm.widgets

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kr.co.example.lotto365_mvvm.R


class CircleImageView : AppCompatImageView {
    private val mDrawableRect = RectF()
    private val mBorderRect = RectF()
    private val mShaderMatrix = Matrix()
    private val mBitmapPaint = Paint()
    private val mBorderPaint = Paint()
    private var mBorderColor = DEFAULT_BORDER_COLOR
    private var mBorderWidth = DEFAULT_BORDER_WIDTH
    private var mBitmap: Bitmap? = null
    private var mBitmapShader: BitmapShader? = null
    private var mBitmapWidth = 0
    private var mBitmapHeight = 0
    private var mDrawableRadius = 0f
    private var mBorderRadius = 0f
    private var mReady = false
    private var mSetupPending = false
    private val defaultDrawable: Drawable? = null
    private val themeDrawable: Drawable? = null
    private var viewWidth = 0f
    private var viewHeight = 0f

    constructor(context: Context?) : super(context) {
        init()
    }

//    constructor(context: Context , attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0) : super(context, attrs, defStyle) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0)
        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_borderWidth, DEFAULT_BORDER_WIDTH)
        mBorderColor = a.getColor(R.styleable.CircleImageView_borderColor, DEFAULT_BORDER_COLOR)
        a.recycle()
        init()
    }

    private fun init() {
        super.setScaleType(SCALE_TYPE)
        mReady = true
        if (mSetupPending) {
            setup()
            mSetupPending = false
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // TODO Auto-generated method stub
        val d = drawable
        if (d != null) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val height = Math.ceil(
                width.toFloat() * d.intrinsicHeight.toFloat() / d.intrinsicWidth.toDouble()
            ).toInt()
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun getScaleType(): ScaleType {
        return SCALE_TYPE
    }

    override fun setScaleType(scaleType: ScaleType) {
        require(scaleType == SCALE_TYPE) {
            String.format(
                "ScaleType %s not supported.", scaleType
            )
        }
    }

    override fun setAdjustViewBounds(adjustViewBounds: Boolean) {
        require(!adjustViewBounds) { "adjustViewBounds not supported." }
    }

    override fun onDraw(canvas: Canvas) {
        if (drawable == null) {
            return
        }
        canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), mDrawableRadius - paddingLeft, mBitmapPaint)
        //        canvas.drawCircle( getWidth( ) / 2 , getHeight( ) / 2 , mDrawableRadius , mBitmapPaint );
        if (mBorderWidth != 0) {
            canvas.drawCircle(width / 2.toFloat(), height / 2.toFloat(), mBorderRadius, mBorderPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w.toFloat()
        viewHeight = h.toFloat()
        setup()
    }

    var borderColor: Int
        get() = mBorderColor
        set(borderColor) {
            if (borderColor == mBorderColor) {
                return
            }
            mBorderColor = borderColor
            mBorderPaint.color = mBorderColor
            invalidate()
        }

    var borderWidth: Int
        get() = mBorderWidth
        set(borderWidth) {
            if (borderWidth == mBorderWidth) {
                return
            }
            mBorderWidth = borderWidth
            setup()
        }

    override fun setImageBitmap(bm: Bitmap) {
        super.setImageBitmap(bm)
        mBitmap = bm
        setup()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        mBitmap = getBitmapFromDrawable(drawable)
        setup()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        mBitmap = getBitmapFromDrawable(drawable)
        setup()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        mBitmap = getBitmapFromDrawable(drawable)
        setup()
    }

    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else try {
            val bitmap: Bitmap
            bitmap = if (drawable is ColorDrawable) {
                Bitmap.createBitmap(
                    COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG
                )
            } else {
                Bitmap.createBitmap(
                    drawable.intrinsicWidth, drawable.intrinsicHeight, BITMAP_CONFIG
                )
            }
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } catch (e: OutOfMemoryError) {
            null
        }
    }

    private fun setup() {
        if (!mReady) {
            mSetupPending = true
            return
        }
        if (mBitmap == null) {
            return
        }
        if (paddingLeft != 0 || paddingBottom != 0 || paddingRight != 0 || paddingRight != 0) {
            mBitmap = paddingBitmap(mBitmap!!)
        }
        mBitmapShader = BitmapShader(
            mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        mBitmapPaint.isAntiAlias = true
        mBitmapPaint.shader = mBitmapShader
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.isAntiAlias = true
        mBorderPaint.color = mBorderColor
        mBorderPaint.strokeWidth = mBorderWidth.toFloat()
        mBitmapHeight = mBitmap!!.height
        mBitmapWidth = mBitmap!!.width
        mBorderRect[0f, 0f, width.toFloat()] = height.toFloat()
        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2 - paddingTop / 2.0f, (mBorderRect.width() - mBorderWidth) / 2) - paddingTop / 2.0f
        mDrawableRect[mBorderWidth - paddingTop / 2.0f, mBorderWidth - paddingTop / 2.0f, mBorderRect.width() - mBorderWidth - paddingTop / 2.0f] = mBorderRect.height() - mBorderWidth - paddingTop / 2.0f
        //        mBorderRadius = Math.min( ( mBorderRect.height( ) - mBorderWidth ) / 2, ( mBorderRect.width( ) - mBorderWidth ) / 2 );
        //        mDrawableRect.set( mBorderWidth , mBorderWidth , mBorderRect.width( ) - mBorderWidth  , mBorderRect.height( ) - mBorderWidth );
        mDrawableRadius = Math.min(
            mDrawableRect.height() / 2, mDrawableRect.width() / 2
        )
        updateShaderMatrix()
        invalidate()
    }

    private fun updateShaderMatrix() {
        val scale: Float
        var dx = 0f
        var dy = 0f
        mShaderMatrix.set(null)
        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / mBitmapHeight.toFloat()
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f
        } else {
            scale = mDrawableRect.width() / mBitmapWidth.toFloat()
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f
        }
        mShaderMatrix.setScale(scale, scale)
        mShaderMatrix.postTranslate(
            (dx + 0.5f).toInt() + mBorderWidth.toFloat(), (dy + 0.5f).toInt() + mBorderWidth.toFloat()
        )
        mBitmapShader!!.setLocalMatrix(mShaderMatrix)
    }

    private fun paddingBitmap(bitmap: Bitmap): Bitmap {
        if (viewWidth <= 0 || viewHeight <= 0) {
            return bitmap
        }
        val paddedBitmap = Bitmap.createBitmap(viewWidth.toInt(), viewHeight.toInt(), BITMAP_CONFIG)
        val canvas = Canvas(paddedBitmap)
        canvas.drawARGB(0xFF, 0xFF, 0xFF, 0xFF) // this represents white color
        canvas.drawBitmap(bitmap, null, RectF(paddingLeft.toFloat(), paddingTop.toFloat(), viewWidth - paddingRight, viewHeight - paddingBottom), Paint(Paint.FILTER_BITMAP_FLAG))
        return paddedBitmap
    }

    companion object {
        private val SCALE_TYPE = ScaleType.CENTER_CROP
        private val BITMAP_CONFIG = Bitmap.Config.ARGB_8888
        private const val COLORDRAWABLE_DIMENSION = 2
        private const val DEFAULT_BORDER_WIDTH = 0
        private val DEFAULT_BORDER_COLOR = Color.argb(0, 0, 0, 0)
    }
}