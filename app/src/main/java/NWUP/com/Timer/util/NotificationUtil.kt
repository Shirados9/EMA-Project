package NWUP.com.Timer.util

import NWUP.com.MainActivity
import NWUP.com.R
import NWUP.com.Timer.AppConstants
import NWUP.com.Timer.TimerNotificationActionReceiver
import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import java.text.SimpleDateFormat
import java.util.*

/**
 * builds notifications when leaving the app
 */
class NotificationUtil {
    companion object {
        private const val CHANNEL_ID_TIMER = "menu_timer"
        private const val CHANNEL_NAME_TIMER = "Timer App Timer"
        private const val TIMER_ID = 0

        fun showTimerExpired(context: Context) {
            val startIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            startIntent.action = AppConstants.ACTION_START

            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(
                context,
                notification
            )
            r.play()

            val startPendingIntent = PendingIntent.getBroadcast(
                context,
                0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, false)
            nBuilder.setContentTitle("Timer Expired!")
                .setContentText("Start again?")
                .setContentIntent(getResultPendingTimerIntent(context))
                .addAction(R.drawable.ic_play_arrow, "Start", startPendingIntent)

            val nManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, false)

            nManager.notify(TIMER_ID, nBuilder.build())
        }

        fun showTimerRunning(context: Context, wakeUpTime: Long) {
            val stopIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            stopIntent.action = AppConstants.ACTION_STOP
            val stopPendingIntent = PendingIntent.getBroadcast(
                context,
                0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val pauseIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            pauseIntent.action = AppConstants.ACTION_PAUSE
            val pausePendingIntent = PendingIntent.getBroadcast(
                context,
                0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, false)
            nBuilder.setContentTitle("Timer is Running.")
                .setContentText("End: ${df.format(Date(wakeUpTime))}")
                .setContentIntent(getResultPendingTimerIntent(context))
                .setOngoing(true)
                .addAction(R.drawable.ic_stop, "Stop", stopPendingIntent)
                .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)

            val nManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, false)

            nManager.notify(TIMER_ID, nBuilder.build())
        }

        fun showTimerPaused(context: Context) {
            val resumeIntent = Intent(context, TimerNotificationActionReceiver::class.java)
            resumeIntent.action = AppConstants.ACTION_RESUME
            val resumePendingIntent = PendingIntent.getBroadcast(
                context,
                0, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, false)
            nBuilder.setContentTitle("Timer is paused.")
                .setContentText("Resume?")
                .setContentIntent(getResultPendingTimerIntent(context))
                .setOngoing(true)
                .addAction(R.drawable.ic_play_arrow, "Resume", resumePendingIntent)

            val nManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, false)

            nManager.notify(TIMER_ID, nBuilder.build())
        }

        fun hideTimerNotification(context: Context) {
            val nManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.cancel(TIMER_ID)
        }

        private fun getBasicNotificationBuilder(
            context: Context,
            channelId: String,
            playSound: Boolean
        )
                : NotificationCompat.Builder {

            val notificationSound: Uri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val nBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_timer)
                .setAutoCancel(true)
                .setDefaults(0)
            if (playSound) nBuilder.setSound(notificationSound)
            return nBuilder
        }

        /**
         * when clicking on the notification, MainActivity opens
         * in MainActivity intent gets handled and TimerFragment opens
         * returns [PendingIntent]
         */
        private fun getResultPendingTimerIntent(context: Context): PendingIntent {
            val resultIntent = Intent(context, MainActivity::class.java)
            resultIntent.putExtra("startTimeFragment", "timerFragment")
            return PendingIntent.getActivity(
                context,
                1,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        /**
         * for APIs > 25
         */
        @TargetApi(26)
        private fun NotificationManager.createNotificationChannel(
            channelID: String,
            channelName: String,
            playSound: Boolean
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelImportance = if (playSound) NotificationManager.IMPORTANCE_DEFAULT
                else NotificationManager.IMPORTANCE_LOW
                val nChannel = NotificationChannel(channelID, channelName, channelImportance)
                nChannel.enableLights(true)
                nChannel.lightColor = Color.BLUE
                this.createNotificationChannel(nChannel)
            }
        }
    }
}