package kr.co.example.lotto365_mvp.Utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import kr.co.example.lotto365_mvp.MiniFrameWork.Manager.FontManager
import kr.co.example.lotto365_mvvm.common.commonset.C
import java.io.Serializable
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        fun overrideFonts(v: View) {
            try {
                if (v is ViewGroup) {
                    var vg: ViewGroup = v
                    for (i in 0 until vg.childCount) {
                        var child = vg.getChildAt(i)
                        overrideFonts(
                            child
                        )
                    }
                } else if (v is TextView) {
                    v.typeface = FontManager.instance.getFont(v.typeface.style)
                } //Todo FontTextView mapping

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun postDelayed(runnable: () -> Unit, delay: Long) {
            Handler().postDelayed(runnable, delay)
        }

        fun getScreenHeight(context: Context): Int {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return metrics.heightPixels
        }


        fun getPreferenceData(context: Context?, key: String?, defValue: Any): Any? {
            return if (context != null && key != null) {
                when (defValue.javaClass) {
                    String::class.java -> context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).getString(key, defValue as String)
                    Int::class.java -> context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).getInt(key, defValue as Int)
                    Long::class.java -> context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).getLong(key, defValue as Long)
                    Boolean::class.java -> context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).getBoolean(key, defValue as Boolean)
                    Float::class.java -> context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).getFloat(key, defValue as Float)
                    else -> defValue
                }
            } else null

        }

        fun getPreferenceData(context: Context?, key: String?): String? = if (context != null && key != null) context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).getString(key, "") else null

        fun setPreferenceData(context: Context?, key: String?, value: Any?) {
            if (context != null && key != null && value != null) {
                when (value.javaClass) {
                    String::class.java -> {
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().remove(key).apply()
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().putString(key, value as String).apply()
                    }
                    Int::class.java -> {
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().remove(key).apply()
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().putInt(key, value as Int).apply()
                    }
                    Long::class.java -> {
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().remove(key).apply()
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().putLong(key, value as Long).apply()
                    }
                    Float::class.java -> {
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().remove(key).apply()
                        context.getSharedPreferences(C.Pref.pref, Context.MODE_PRIVATE).edit().putFloat(key, value as Float).apply()
                    }
                }
            }
        }

        fun equals(obj1: Any?, obj2: Any?): Boolean {
            if (obj1 == null && obj2 == null) return true
            if (obj1 == null) return false
            if (obj2 == null) return false
            return obj1 == obj2
        }

        fun dpToPx(context: Context, dp: Int): Float {
            val metrics = context.resources.displayMetrics
            return dp * (metrics.densityDpi / 160f)
        }

        fun loadAdView(view: AdView) {
            var requestConfiguration = RequestConfiguration.Builder().setTestDeviceIds(listOf("B14131D619B9D901BB9D7334A655EE1F", "A1894C9541211E96FE8DA959627DABEF", "B93A1DA5543917A30D8BC7C6216136F6", "F6331E60355E97AD50435E212064B4AB")).build()
            MobileAds.setRequestConfiguration(requestConfiguration)
            var adRequest = AdRequest.Builder().build()
            view.loadAd(adRequest)
        }

        fun addTopMarginStatusBarHeight(context: Context?, target: View) {
            try {
                (target.layoutParams as MarginLayoutParams).topMargin = getDeviceStatusBarHeight(context)
                target.requestLayout()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        fun getDeviceStatusBarHeight(context: Context?): Int {
            return context?.resources?.getDimensionPixelSize(context.resources.getIdentifier("status_bar_height", "dimen", "android")) ?: 0
        }

        fun getNumberColor(number: Int): Int {
            return when (number) {
                in 1..9 -> Color.parseColor("#f28500")
                in 10..19 -> Color.parseColor("#2b59a2")
                in 20..29 -> Color.parseColor("#e20f00")
                in 30..39 -> Color.parseColor("#c6c6c6")
                in 40..45 -> Color.parseColor("#3f9d2f")
                else -> Color.parseColor("#338B95A1")
            }

        }

        fun createStringListBundle(key: String, values: ArrayList<String>): Bundle {
            val bundle = Bundle()
            bundle.putStringArrayList(key, values)
            return bundle
        }

        fun createSerializableBundle(key: String?, `object`: Serializable?): Bundle? {
            val bundle = Bundle()
            bundle.putSerializable(key, `object`)
            return bundle
        }

        fun mergeBundles(vararg bundles: Bundle): Bundle? {
            val bundle = Bundle()
            for (b in bundles) {
                val keys = b.keySet()
                for (k in keys) {
                    try {
                        bundle.putSerializable(k, b[k] as Serializable?)
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return bundle
        }

        fun dataFormat(longTime: String?, format: String?): String? {
            if (longTime == null || format == null) return "-"
            try {
                longTime.toLong()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                return "-"
            }
            val formatter = SimpleDateFormat(format, Locale.US)
            return formatter.format(Date(longTime.toLong()))
        }

        fun dataFormat(longTime: Long?, format: String?): String? {
            if (longTime == null || format == null) return "-"
            return try {
                val formatter = SimpleDateFormat(format, Locale.US)
                formatter.format(Date(longTime))
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                "-"
            }
        }

        fun sha256(s: String): String? {
            return try {
                val digest = MessageDigest.getInstance("SHA-256")
                digest.update(s.toByteArray())
                val m = digest.digest()
                byteArrayToHexString(m)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }


        fun byteArrayToHexString(bytes: ByteArray): String {
            val HEX_CHARS = "0123456789ABCDEF".toCharArray()
            val result = StringBuffer()
            for (b in bytes) {
                result.append(HEX_CHARS[(b.toInt() and 0xF0).ushr(4)]).append(HEX_CHARS[b.toInt() and 0x0F])
            }
            return result.toString()
        }

        fun dateCompare(beforeTime: String?, nowTime: String?): Int {
            if (nowTime == null) return 0
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            return try {
                val before = format.parse(format.format(Date(java.lang.Long.valueOf(beforeTime!!))))
                val now = format.parse(format.format(Date(java.lang.Long.valueOf(nowTime))))
                if (before == null || now == null) 0 else before.compareTo(now)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                0
            }
        }

        fun getAppVersion(context: Context): String? {
            var i: PackageInfo? = null
            return try {
                i = context.packageManager.getPackageInfo(context.packageName, 0)
                i.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                ""
            }
        }


    }


}