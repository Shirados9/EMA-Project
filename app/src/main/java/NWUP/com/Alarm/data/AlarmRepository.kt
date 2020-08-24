package NWUP.com.Alarm.data

import NWUP.com.Alarm.data.Alarm
import NWUP.com.Alarm.data.AlarmDao
import NWUP.com.Alarm.data.AlarmDatabase
import android.app.Application
import androidx.lifecycle.LiveData


class AlarmRepository(application: Application?) {
    private val alarmDao: AlarmDao
    val alarmsLiveData: LiveData<List<Alarm>>

    fun insert(alarm: Alarm?) =
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.insert(alarm) }

    fun update(alarm: Alarm?) =
        AlarmDatabase.databaseWriteExecutor.execute { alarmDao.update(alarm) }

    fun deleteAll() = AlarmDatabase.databaseWriteExecutor.execute{ alarmDao.deleteAll() }

    fun getAlarms(): LiveData<List<Alarm>> {
        return alarmDao.getAlarms()
    }

    init {
        val db: AlarmDatabase? = AlarmDatabase.getDatabase(application!!)
        alarmDao = db?.alarmDao()!!
        alarmsLiveData = alarmDao.getAlarms()
    }
}