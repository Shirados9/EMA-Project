package NWUP.com.Alarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class CreateAlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val alarmRepository: AlarmRepository = AlarmRepository(application)
    fun insert(alarm: Alarm?) {
        alarmRepository.insert(alarm)
    }

}