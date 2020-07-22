package kr.co.example.lotto365_mvvm.miniframework.DataBase.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.co.example.lotto365_mvp.MiniFrameWork.DataBase.Entity.History

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM History WHERE uniqueId = :uniqueId")
    fun select(uniqueId: String): History

    @Query("SELECT * FROM History ORDER BY historyDate DESC")
    fun selectAll(): LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(history: History)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(historys: List<History>)

    @Query("DELETE FROM History WHERE uniqueId = :uniqueId")
    fun delete(uniqueId: String)

    @Query("DELETE FROM History")
    fun deleteAll()
}