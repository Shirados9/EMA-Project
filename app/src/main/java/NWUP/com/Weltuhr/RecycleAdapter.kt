package NWUP.com.Weltuhr

import NWUP.com.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_weltuhr_recyclerview.view.*

class RecyclerAdapter(private val exampleList: List<RecyclerItem>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {
    private var data: ArrayList<String>? = null


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val item_timezone: TextView = itemView.item_timezone
        val item_date: TextView = itemView.item_date
        val item_time: TextClock = itemView.item_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val createview = LayoutInflater.from(parent.context).inflate(R.layout.fragment_weltuhr_recyclerview,parent,false)
        return ViewHolder(createview)
    }


    override fun getItemCount() = exampleList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]


        holder.item_timezone.text = currentItem.timezone
        holder.item_date.text = currentItem.date
        holder.item_time.timeZone = currentItem.timezone_clock
    }
}