package NWUP.com.Alarm.data


import android.app.Application
import androidx.lifecycle.LiveData

/**
 * starts alarmdao queries
 */
class AlarmRepository(application: Application?) {
    private val alarmDao: AlarmDao
    val alarmsLiveData: LiveData<List<Alarm>>

    init {
        val db: AlarmDatabase? = AlarmDatabase.getDatabase(application!!)
        alarmDao = db?.alarmDao()!!
        alarmsLiveData = alarmDao.getAlarms()
    }

    fun insert(alarm: Alarm?) =
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.insert(alarm) }

    fun delete(alarm: Alarm?) =
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.delete(alarm) }

    fun update(alarm: Alarm?) =
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.update(alarm) }

    fun deleteAll() =
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.deleteAll() }

    suspend fun getAlarmsData(): List<Alarm> {
        return alarmDao.getAlarmsData()
    }
}