package NWUP.com.Weltuhr

import NWUP.com.R
import NWUP.com.Weltuhr.WeltuhrFragment.Companion.RecyclerItems
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.text.DateFormat
import java.util.*

class PickTimeZone: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_weltuhr_timezonepicker)

        // creates autotextview and access to resource: "zeitzonen" in app/res/values/strings
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val zeitzonen = resources.getStringArray(R.array.zeitzonen)


        // creates adapter with ressource zeitzonen
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,zeitzonen)


        // sets autotextview together with adapter to show available timezones in dropdown menu
        autotextView.setAdapter(adapter)


        // shows dropdown menu when clicked on
        autotextView.setOnClickListener {
            autotextView.showDropDown()
        }

        // if item in dropdown menu gets clicked on, gets parameters to set in "Weltuhrfragment"
        autotextView.setOnItemClickListener { parent, view, position, id ->
            val selecteditemAsCity = resources.getStringArray(R.array.zeitzone_als_stadt_states)[position]

            val selectedtimezone = resources.getStringArray(R.array.available_timezones)[position]

            val tz = TimeZone.getTimeZone(selectedtimezone)
            val c = Calendar.getInstance(tz)

            val currentDate =  DateFormat.getDateInstance().format(c.time)


            RecyclerItems.add(RecyclerItem(selecteditemAsCity,currentDate,selectedtimezone))
            saveData()
            finish()
        }

    }

    fun saveData() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("weltuhr preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(RecyclerItems)
        editor.putString("weltuhr", json)
        editor.apply()
    }



}