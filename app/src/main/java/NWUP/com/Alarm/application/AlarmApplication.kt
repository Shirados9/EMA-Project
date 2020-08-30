package NWUP.com.Alarm.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

//creates the notification channel with the id "CHANNEL_ID"
class AlarmApplication: Application() {
    companion object {
        const val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
}