package NWUP.com.Timer

import NWUP.com.R
import NWUP.com.Timer.util.PrefUtil
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_timer.*
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {

    enum class TimerState{
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Stopped
    private var secondsRemaining: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabStart.setOnClickListener{view ->
            startTimer()
            timerState =  TimerState.Running
            updateButtons()
        }

        fabPause.setOnClickListener { view ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        fabStop.setOnClickListener { view ->
            timer.cancel()
            onTimerFinished()
        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

        //TODO: remove background timer, hide notification
    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running){
            timer.cancel()
            //TODO: start background timer and show notification
        }
        else if (timerState == TimerState.Paused){
            //TODO: show notification
        }

        context?.let { PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, it) }
        context?.let { PrefUtil.setSecondsRemaining(secondsRemaining, it) }
        context?.let { PrefUtil.setTimerState(timerState, it) }
    }

    private fun initTimer(){
        timerState = context?.let { PrefUtil.getTimerState(it) }!!

        //we don't want to change the length of the timer which is already running
        //if the length was changed in settings while it was backgrounded
        if (timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = (if (timerState == TimerState.Running || timerState == TimerState.Paused)
            context?.let { PrefUtil.getSecondsRemaining(it) }
        else
            timerLengthSeconds)!!

        //TODO: change secondsRemaining according to where the background timer stopped

        //resume where we left off
        if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.Stopped

        //set the length of the timer to be the one set in SettingsActivity
        //if the length was changed when the timer was running
        setNewTimerLength()

        progress_countdown.progress = 0

        context?.let { PrefUtil.setSecondsRemaining(timerLengthSeconds, it) }
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val lengthInMinutes = context?.let { PrefUtil.getTimerLength(it) }
        if (lengthInMinutes != null) {
            timerLengthSeconds = (lengthInMinutes * 60L)
        }
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = context?.let { PrefUtil.getPreviousTimerLengthSeconds(it) }!!
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        textView_countdown.text = "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        progress_countdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when (timerState) {
            TimerState.Running ->{
                fabStart.isEnabled = false
                fabPause.isEnabled = true
                fabStop.isEnabled = true
            }
            TimerState.Stopped -> {
                fabStart.isEnabled = true
                fabPause.isEnabled = false
                fabStop.isEnabled = false
            }
            TimerState.Paused -> {
                fabStart.isEnabled = true
                fabPause.isEnabled = false
                fabStop.isEnabled = true
            }
        }
    }
}
