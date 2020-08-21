package NWUP.com.Alarm

import NWUP.com.Alarm.AlarmApplication.Companion.CHANNEL_ID
import NWUP.com.Alarm.AlertReceiver.Companion.TITLE
import android.R
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import java.lang.String


class AlarmService: Service() {

    var mediaPlayer: MediaPlayer? = null
    var vibrator: Vibrator? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()


        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(this, notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            r.isLooping = true
        }

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, AlarmRingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val alarmTitle =
            String.format("%s Alarm", intent!!.getStringExtra(TITLE))

        val notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Ring Ring .. Ring Ring")
                .setSmallIcon(R.drawable.btn_radio)
                .setContentIntent(pendingIntent)
                .build()


        mediaPlayer?.start()
        mediaPlayer!!.start()

        val pattern = longArrayOf(0, 100, 1000)
        vibrator!!.vibrate(pattern, 0)

        startForeground(1, notification)

        return START_STICKY


        //return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        vibrator!!.cancel()
    }
}
