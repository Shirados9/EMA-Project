package NWUP.com.Alarm

import NWUP.com.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_alarm_clock.view.*

class AlarmRecycleAdapter(private val exampleList: List<AlarmRecycleItem>): RecyclerView.Adapter<AlarmRecycleAdapter.ViewHolder>()  {
    private var data: ArrayList<String>? = null


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val Uhrzeit: Button = itemView.Alarm_Uhrzeit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmRecycleAdapter.ViewHolder {
        val createview = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_alarm_clock,parent,false)
        return ViewHolder(createview)
    }


    override fun getItemCount() = exampleList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.Uhrzeit.text = currentItem.Uhrzeit_Alarm
    }
}

