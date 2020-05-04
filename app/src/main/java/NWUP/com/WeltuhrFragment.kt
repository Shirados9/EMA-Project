package NWUP.com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_weltuhr.*
import java.text.DateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class WeltuhrFragment : Fragment() {

    fun setCurrentDate() {
        var calendar = Calendar.getInstance()
        var currentDate =  DateFormat.getDateInstance().format(calendar.getTime())

        val tz: TimeZone = TimeZone.getDefault()





        CurrentTimeZone.text = tz.getDisplayName()
        CurrentDate.text = currentDate
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        var binding = inflater.inflate(R.layout.fragment_weltuhr, container, false)
        return binding
    }

    override fun onStart() {
        super.onStart()
        setCurrentDate()
    }


}
