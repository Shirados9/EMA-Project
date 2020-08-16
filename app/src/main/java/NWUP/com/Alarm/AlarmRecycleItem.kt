package NWUP.com.Alarm

import java.util.*

//data class AlarmRecycleItem (val Uhrzeit_Alarm:String)


class AlarmRecycleItem(var Uhrzeit_Alarm: String) {
    fun changeUhrzeit(Uhrzeit: String) {
        Uhrzeit_Alarm = Uhrzeit
    }
    fun getSetTime(): Calendar {
        // "02:11"

        var Zeit = Uhrzeit_Alarm

        var c = Calendar.getInstance()


        var stunden = Zeit[0].toString() + Zeit[1].toString()
        var minuten = Zeit[3].toString() + Zeit[4].toString()

        c.set(Calendar.HOUR_OF_DAY, stunden.toInt())
        c.set(Calendar.MINUTE, minuten.toInt())
        c.set(Calendar.SECOND, 0)

        return c


    }

}