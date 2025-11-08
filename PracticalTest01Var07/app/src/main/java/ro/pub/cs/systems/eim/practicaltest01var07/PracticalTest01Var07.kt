package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var07 : AppCompatActivity() {
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText
    private lateinit var input4: EditText

    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent != null && intent.action == "ProcessingThread") {
                val n1 = intent.getIntExtra("n1", 0)
                val n2 = intent.getIntExtra("n2", 0)
                val n3 = intent.getIntExtra("n3", 0)
                val n4 = intent.getIntExtra("n4", 0)

                input1.setText(n1.toString())
                input2.setText(n2.toString())
                input3.setText(n3.toString())
                input4.setText(n4.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        val serviceIntent = Intent(this, Practical_Service::class.java)
        startService(serviceIntent)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        input3 = findViewById(R.id.input3)
        input4 = findViewById(R.id.input4)

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val message = result.data?.getStringExtra("result")
                if (message != null) {
                    Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        val navigateToSecondaryActivityButton = findViewById<Button>(R.id.set)
        navigateToSecondaryActivityButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var07_Secondary::class.java)
            intent.putExtra("input1", Integer.valueOf(input1.text.toString()))
            intent.putExtra("input2", Integer.valueOf(input2.text.toString()))
            intent.putExtra("input3", Integer.valueOf(input3.text.toString()))
            intent.putExtra("input4", Integer.valueOf(input4.text.toString()))
            activityResultsLauncher.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(messageBroadcastReceiver, IntentFilter("ProcessingThread"), Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageBroadcastReceiver)
    }

    override fun onDestroy() {
        val intent = Intent(applicationContext, Practical_Service::class.java)
        applicationContext.stopService(intent)
        super.onDestroy()
    }
}