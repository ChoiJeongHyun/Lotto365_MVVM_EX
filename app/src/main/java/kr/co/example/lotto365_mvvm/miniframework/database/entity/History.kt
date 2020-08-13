package kr.co.example.lotto365_mvvm.miniframework.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "History")
data class History(
    @PrimaryKey
    var uniqueId: String = "",
    var historyDate: Long = System.currentTimeMillis(),
    var numbers: List<String> = arrayListOf(),
    var type: String = "Random"
)