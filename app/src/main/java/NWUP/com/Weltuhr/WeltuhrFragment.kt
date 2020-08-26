package NWUP.com.Weltuhr

import NWUP.com.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_weltuhr_main.*
import kotlinx.android.synthetic.main.fragment_weltuhr_main.view.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeltuhrFragment : Fragment() {
    private fun setCurrentDate() {
        // gets current time, date and timezone
        val calendar = Calendar.getInstance()
        val currentDate =  DateFormat.getDateInstance().format(calendar.time)
        val tz: TimeZone = TimeZone.getDefault()

        // sets current date and timezone
        currenttimezone.text = tz.displayName
        currentdate.text= currentDate
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_weltuhr_main, container, false)

        // starts PickTimeZone
        binding.gettopicktimezone.setOnClickListener {
            val intent = Intent(context, PickTimeZone::class.java)
            startActivity((intent))
        }
        return binding
    }


    override fun onStart() {
        super.onStart()

        setCurrentDate()
        recycler_weltuhr.layoutManager = LinearLayoutManager(activity)
        recycler_weltuhr.adapter = RecyclerAdapter(RecyclerItems)

        recycler_weltuhr.setHasFixedSize(true)

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallBack(RecyclerAdapter(RecyclerItems)))
        recycler_weltuhr.adapter?.notifyDataSetChanged();
        itemTouchHelper.attachToRecyclerView(recycler_weltuhr)
    }
    companion object {
        var RecyclerItems = ArrayList<RecyclerItem>()
    }
}
