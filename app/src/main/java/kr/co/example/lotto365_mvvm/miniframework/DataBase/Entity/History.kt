package kr.co.example.lotto365_mvp.MiniFrameWork.DataBase.Entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "History")
data class History(
    @PrimaryKey
    val uniqueId: String = "",
    var historyDate: Long = System.currentTimeMillis(),
    var numbers: List<String>,
    var type: String = "Random"
)