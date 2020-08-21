package NWUP.com.Alarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class AlarmsListViewModel(application: Application) : AndroidViewModel(application) {
    private val alarmRepository: AlarmRepository = AlarmRepository(application)
    val alarmsLiveData: LiveData<List<Alarm>>

    fun update(alarm: Alarm?) {
        alarmRepository.update(alarm)
    }

    init {
        alarmsLiveData = alarmRepository.alarmsLiveData
    }
}