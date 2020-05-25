package NWUP.com.Weltuhr

import NWUP.com.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_weltuhr.*
import kotlinx.android.synthetic.main.fragment_weltuhr.view.*
import java.text.DateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */



class WeltuhrFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    private fun setCurrentDate() {
        val calendar = Calendar.getInstance()
        val currentDate =  DateFormat.getDateInstance().format(calendar.getTime())

        val tz: TimeZone = TimeZone.getDefault()

        currenttimezone.text = tz.displayName
        currentdate.text= currentDate
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_weltuhr, container, false)
        //linearLayoutManager = LinearLayoutManager(context)
        //recycler.layoutManager = linearLayoutManager


        binding.gettopicktimezone.setOnClickListener {
            val intent = Intent(context, PickTimeZone::class.java)
            startActivity((intent))
        }





        return binding
    }

    override fun onStart() {
        super.onStart()

        linearLayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = linearLayoutManager
        //adapter = RecyclerAdapter(photosList)
        //recycler.adapter = adapter
        setCurrentDate()
    }



}
