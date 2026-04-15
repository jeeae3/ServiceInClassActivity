package edu.temple.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var timerService: TimerService.TimerBinder? = null

    private var isBound = false

    private lateinit var textView: TextView

    private val defaultVal = 20

    private val handler = Handler(Looper.getMainLooper()) { msg ->
        textView.text = msg.what.toString()
        true
    }

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            timerService = p1 as TimerService.TimerBinder
            timerService?.setHandler(handler)
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            timerService = null
            isBound = false
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)



        findViewById<Button>(R.id.startButton).setOnClickListener {
            if (isBound) {
                val savedValue = timerService?.getSavedValue() ?: -1
                val startVal = if (savedValue == -1) defaultVal else savedValue
                timerService?.start(startVal)
            }
        }

        findViewById<Button>(R.id.stopButton).setOnClickListener {
            if (isBound) {
                timerService?.pause()
            }

        }

    }

    override fun onStart() {
        super.onStart()
        bindService(
            Intent(this, TimerService::class.java),
            conn,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(conn)
            isBound = false
        }
    }
}
