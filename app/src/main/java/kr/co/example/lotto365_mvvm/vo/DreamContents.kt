package kr.co.example.lotto365.vo

import java.io.Serializable

data class DreamContents(var categoryName: String = "", var isChoice: Boolean = false, var name: String = "", var categoryPosition: Int = 0, var position: Int = 0) : Serializable
