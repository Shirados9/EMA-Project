package NWUP.com.Alarm.ItemTouchHelper

import NWUP.com.Alarm.alarmsList.AlarmRecyclerViewAdapter
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView


class SwipetoDeleteCallback(var adapter: AlarmRecyclerViewAdapter): SimpleCallback(0, RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position: Int = viewHolder.adapterPosition
        //adapter.deleteItem(position)
    }
}