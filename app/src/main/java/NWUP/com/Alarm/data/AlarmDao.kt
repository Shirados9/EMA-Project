@file:Suppress("AndroidUnresolvedRoomSqlReference")

package NWUP.com.Alarm.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Dao for database queries
 */
@Dao
interface AlarmDao {
    @Insert
    fun insert(alarm: Alarm?)

    @Delete
    fun delete(alarm: Alarm?)

    @Query("DELETE FROM alarm_table")
    fun deleteAll()

    @Query("SELECT * FROM alarm_table ORDER BY alarmId ASC")
    fun getAlarms(): LiveData<List<Alarm>>

    @Query("SELECT * FROM alarm_table")
    suspend fun getAlarmsData(): List<Alarm>

    @Update
    fun update(alarm: Alarm?)
}