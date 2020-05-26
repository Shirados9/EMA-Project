package NWUP.com.Weltuhr

import NWUP.com.R
import NWUP.com.Weltuhr.WeltuhrFragment.Companion.RecyclerItems
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_weltuhr.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class PickTimeZone: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_timezone)

        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val zeitzonen = resources.getStringArray(R.array.zeitzonen)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,zeitzonen)

        autotextView.setAdapter(adapter)

        autotextView.setOnClickListener {
            autotextView.showDropDown()
        }
        autotextView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val selectedtimezone = resources.getStringArray(R.array.available_ids)[position]

            val tz = TimeZone.getTimeZone(selectedtimezone)
            val c = Calendar.getInstance(tz)

            val currentDate =  DateFormat.getDateInstance().format(c.time)
            val time =  String.format("%02d" , c.get(Calendar.HOUR_OF_DAY))+":"+
                    String.format("%02d" , c.get(Calendar.MINUTE))+":"+
                    String.format("%02d" , c.get(Calendar.SECOND))+":"+
                    String.format("%03d" , c.get(Calendar.MILLISECOND));
            //



            RecyclerItems.add(RecyclerItem(selectedItem,currentDate,time))

            finish()
        }

    }




}