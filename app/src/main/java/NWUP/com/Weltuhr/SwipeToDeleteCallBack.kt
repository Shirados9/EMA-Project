package NWUP.com.Weltuhr

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteCallBack(adapter: RecyclerAdapter): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    private val mAdapter: RecyclerAdapter = adapter

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position: Int = viewHolder.adapterPosition
        mAdapter.deleteItem(position)
    }

}