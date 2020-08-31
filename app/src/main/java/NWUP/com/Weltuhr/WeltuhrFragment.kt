package NWUP.com.Weltuhr

import NWUP.com.R
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_weltuhr_main.*
import kotlinx.android.synthetic.main.fragment_weltuhr_main.view.*
import java.lang.reflect.Type
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


class WeltuhrFragment : Fragment() {

    lateinit var mAdapter: RecyclerAdapter

    private fun setCurrentDate() {
        // gets current time, date and timezone
        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance().format(calendar.time)
        val tz: TimeZone = TimeZone.getDefault()

        // sets current date and timezone
        currenttimezone.text = tz.displayName
        currentdate.text = currentDate
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadData()
    }


    override fun onStart() {
        super.onStart()

        setCurrentDate()

        recycler_weltuhr.layoutManager = LinearLayoutManager(activity)
        recycler_weltuhr.adapter = RecyclerAdapter(RecyclerItems)
        recycler_weltuhr.setHasFixedSize(true)

        mAdapter = RecyclerAdapter(RecyclerItems)

        val swipeHandler = object : SwipeToDeleteCallBack(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mAdapter.deleteItem(viewHolder.adapterPosition)
                saveData()

                val ft = fragmentManager!!.beginTransaction()
                ft.detach(this@WeltuhrFragment).attach(this@WeltuhrFragment).commit()
            }

        }
        //val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallBack(RecyclerAdapter(RecyclerItems)))
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recycler_weltuhr)
    }

    fun saveData() {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("com.weltuhr.preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(RecyclerItems)
        editor.putString("weltuhr", json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("com.weltuhr.preferences", MODE_PRIVATE)
        val gson = Gson()
        val emptyList = Gson().toJson(ArrayList<RecyclerItem>())
        val json = sharedPreferences.getString("weltuhr", emptyList)
        val type: Type = object : TypeToken<ArrayList<RecyclerItem>>() {}.type
        RecyclerItems = gson.fromJson(json, type)
    }

    companion object {
        var RecyclerItems = ArrayList<RecyclerItem>()
    }
}
