package NWUP.com.Weltuhr

import NWUP.com.R
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

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
            val selectedPosition = position

            finish()
        }

    }




}