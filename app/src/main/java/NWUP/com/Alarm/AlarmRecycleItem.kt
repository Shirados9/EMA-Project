package NWUP.com.Alarm

//data class AlarmRecycleItem (val Uhrzeit_Alarm:String)


class AlarmRecycleItem(var Uhrzeit_Alarm: String) {
    fun changeUhrzeit(Uhrzeit: String) {
        Uhrzeit_Alarm = Uhrzeit
    }

}