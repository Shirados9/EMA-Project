package NWUP.com

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.pick_timezone.*

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


    }




}