package NWUP.com.Alarm.createalarm

import NWUP.com.Alarm.data.Alarm
import NWUP.com.Alarm.data.AlarmRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel

//access to the database
class CreateAlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val alarmRepository: AlarmRepository =
        AlarmRepository(application)
    fun insert(alarm: Alarm?) {
        alarmRepository.insert(alarm)
    }

}