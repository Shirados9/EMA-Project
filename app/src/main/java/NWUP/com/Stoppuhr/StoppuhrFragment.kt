package NWUP.com.Stoppuhr

import NWUP.com.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_stoppuhr.*

/**
 * A simple [Fragment] subclass.
 */
class StoppuhrFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stoppuhr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stopwatchStart.setOnClickListener{view ->
            if(chronometer.isRunning) chronometer.start()
            else chronometer.restart()
            stopwatchStart.hide()
            stopwatchPause.show()
        }

        stopwatchPause.setOnClickListener { view ->
            chronometer.stop()
            stopwatchStart.show()
            stopwatchPause.hide()
            stopwatchReset.show()
            stopwatchShare.show()
        }

        stopwatchReset.setOnClickListener { view ->
            chronometer.start()
            chronometer.stop()
            stopwatchReset.hide()

        }
        stopwatchShare.setOnClickListener { view ->
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.setType("text/plain")
            val shareBody = "Look at my time: " + chronometer.text.toString()
            val shareSub = "Your Subject here"
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(myIntent, "Share your time"))
        }

    }
}

