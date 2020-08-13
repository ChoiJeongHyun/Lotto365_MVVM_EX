package kr.co.example.lotto365_mvvm.miniframework.database

import android.content.Context
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvvm.common.commonset.C
import kr.co.example.lotto365_mvvm.di.ApplicationContext
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefDatabase @Inject constructor(@ApplicationContext val context: Context) {

    private var fixedList: MutableList<String> = arrayListOf()
    private var exceptList: MutableList<String> = arrayListOf()

    init {
        try {
            run {
                var jsonArray = JSONArray(Utils.getPreferenceData(context, C.Pref.fixed_number))
                for (i in 0 until jsonArray.length()) {
                    fixedList.add(jsonArray[i].toString())
                }


            }

            run {
                var jsonArray = JSONArray(Utils.getPreferenceData(context, C.Pref.except_number))
                for (i in 0 until jsonArray.length()) {
                    exceptList.add(jsonArray[i].toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveFixedNumber(number: List<String>) {
        Utils.setPreferenceData(context, C.Pref.fixed_number, number.toString())
        fixedList = number as MutableList<String>
    }

    fun saveExceptNumber(number: List<String>) {
        Utils.setPreferenceData(context, C.Pref.except_number, number.toString())
        exceptList = number as MutableList<String>
    }

    fun getFixedNumber() = fixedList
    fun getExceptNumber() = exceptList
    fun clearFixedNumber() = fixedList.clear()
    fun clearExceptNumber() = exceptList.clear()

    fun saveToken(token: String) = Utils.setPreferenceData(context, C.Pref.fcm_token, token)
    fun getToken() = Utils.getPreferenceData(context, C.Pref.fcm_token)


}


