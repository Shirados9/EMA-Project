package NWUP.com.Stoppuhr

import NWUP.com.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_stoppuhr_main.*

/**
 * A simple [Fragment] subclass.
 */
class StoppuhrFragment : Fragment() {

    private var counter = 1
    private var isRunning = false

    companion object {
        var RecyclerItems = ArrayList<StoppuhrExampleItem>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stoppuhr_main, container, false)
    }

    override fun onResume() {
        super.onResume()

        stopwatch_recycler_view.scrollToPosition(RecyclerItems.size - 1)
    }

    override fun onStart() {
        super.onStart()
        stopwatch_recycler_view.layoutManager = LinearLayoutManager(activity)
        stopwatch_recycler_view.adapter = StoppuhrRecyclerAdapter(RecyclerItems)
        stopwatch_recycler_view.setHasFixedSize(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stopwatchStart.setOnClickListener { view ->
            isRunning = if (isRunning) {
                chronometer.stop()
                stopwatchShare.show()
                stopwatchLap.hide()
                stopwatchStart.setImageResource(R.drawable.ic_play_arrow)
                false
            } else {
                chronometer.restart()
                stopwatchStart.setImageResource(R.drawable.ic_pause)
                stopwatchReset.show()
                stopwatchLap.show()
                true
            }
        }

        stopwatchReset.setOnClickListener { view ->
            chronometer.resetTime()
            chronometer.stop()
            stopwatchStart.setImageResource(R.drawable.ic_play_arrow)
            stopwatchReset.hide()
            stopwatchLap.hide()
            clearRecyclerView()
            isRunning = false
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

        stopwatchLap.setOnClickListener { view ->
            RecyclerItems.add(StoppuhrExampleItem("Lap: " + counter++, chronometer.text.toString()))
            stopwatch_recycler_view.adapter?.notifyItemInserted(RecyclerItems.size - 1)
            stopwatch_recycler_view.scrollToPosition(RecyclerItems.size - 1)
        }
    }

    private fun clearRecyclerView() {
        RecyclerItems.clear()
        stopwatch_recycler_view.adapter?.notifyDataSetChanged();
        counter = 1
    }
}

