package edu.temple.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startButton).setOnClickListener {

        }
        
        findViewById<Button>(R.id.stopButton).setOnClickListener {

        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {

            menuInflater.inflate(R.menu.main, menu)

            return super.onCreateOptionsMenu(munu)
        }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.action_start -> Toast.makeText(this, "Item 1 Selected", Toast.LENGTH_SHORT).show()
                R.id.action_start2 -> Toast.makeText(this, "Item 2 Selected", Toast.LENGTH_SHORT).show()
                R.id.action_start3 -> Toast.makeText(this, "Item 3 Selected", Toast.LENGTH_SHORT).show()
            }

            return super.onOptionsItemSelected(item)
        }
    }
}