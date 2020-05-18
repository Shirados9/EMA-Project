package NWUP.com

import android.os.Bundle
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity

class PickTimeZone: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_timezone)

        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
    }




}