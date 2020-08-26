package NWUP.com.Alarm.alarmsList

import NWUP.com.Alarm.data.Alarm
import NWUP.com.Alarm.data.AlarmRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class AlarmsListViewModel(application: Application) : AndroidViewModel(application) {
    private val alarmRepository: AlarmRepository =
        AlarmRepository(application)
    val alarmsLiveData: LiveData<List<Alarm>>

    fun update(alarm: Alarm?) {
        alarmRepository.update(alarm)
    }

    fun delete(alarm: Alarm?) {
        alarmRepository.delete(alarm)

    }

    fun deleteAll() {
        alarmRepository.deleteAll()
    }

    suspend fun getAlarmData(): List<Alarm> {
        return alarmRepository.getAlarmsData()
    }

    init {
        alarmsLiveData = alarmRepository.alarmsLiveData
    }
}