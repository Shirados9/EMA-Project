package NWUP.com.Stoppuhr

import NWUP.com.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_stoppuhr_recycler_item.view.*


class StoppuhrRecyclerAdapter(private val exampleList: List<StoppuhrExampleItem>) :
    RecyclerView.Adapter<StoppuhrRecyclerAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_stoppuhr_recycler_item,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun getItemCount() = exampleList.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.stopwatch_text_view_1
        val textView2: TextView = itemView.stopwatch_text_view_2
    }
}