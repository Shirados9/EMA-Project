package NWUP.com.Weltuhr

import NWUP.com.R
import android.graphics.Color
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*
import org.w3c.dom.Text

class RecyclerAdapter(private val exampleList: List<RecyclerItem>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val item_timezone: TextView = itemView.item_timezone
        val item_date: TextView = itemView.item_date
        val item_time: TextView = itemView.item_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val createview = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_row,parent,false)
        return ViewHolder(createview)
    }


    override fun getItemCount() = exampleList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]


        holder.item_timezone.text = currentItem.text1
        holder.item_date.text = currentItem.text2
        holder.item_time.text = currentItem.text3
    }


}