package NWUP.com.Weltuhr

import NWUP.com.R
import NWUP.com.Weltuhr.WeltuhrFragment.Companion.RecyclerItems
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_weltuhr_recyclerview.view.*


class RecyclerAdapter(private var exampleList: ArrayList<RecyclerItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTimezone: TextView = itemView.item_timezone
        val itemDate: TextView = itemView.item_date
        val itemTime: TextClock = itemView.item_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createview = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_weltuhr_recyclerview, parent, false)
        return ViewHolder(createview)
    }

    override fun getItemCount() = exampleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]


        holder.itemTimezone.text = currentItem.timezone
        holder.itemDate.text = currentItem.date
        holder.itemTime.timeZone = currentItem.timezone_clock
    }

    fun deleteItem(position: Int) {

        RecyclerItems.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, RecyclerItems.size)
        notifyDataSetChanged()
    }
}