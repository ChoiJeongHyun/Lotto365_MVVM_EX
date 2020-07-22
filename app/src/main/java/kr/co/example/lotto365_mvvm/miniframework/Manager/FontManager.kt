package kr.co.example.lotto365_mvp.MiniFrameWork.Manager

import android.content.Context
import android.graphics.Typeface

class FontManager(context: Context) {

    companion object {
        lateinit var instance: FontManager
            private set

        private const val NORMAL = Typeface.NORMAL
        private const val BOLD = Typeface.BOLD
        private const val MEDIUM = 5
        private const val SEMIBOLD = 6


        fun init(context: Context) {
            instance = FontManager(context)
        }
    }

    private var fontsMap: HashMap<Int, Typeface> = HashMap()

    init {
        fontsMap[NORMAL] = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
        fontsMap[BOLD] = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
    }

    fun getFont(fontStyle:Int) = fontsMap[fontStyle]


}