package ro.pub.cs.systems.eim.practicaltest01var0702

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context.RECEIVER_NOT_EXPORTED


class PracticalTest01Var0702MainActivity : AppCompatActivity() {
    private lateinit var in1: EditText
    private lateinit var in2: EditText
    private lateinit var in3: EditText
    private lateinit var in4: EditText

    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null && intent.action == "ProcessingThread") {
                val v1 = intent.getIntExtra("in1", 0)
                val v2 = intent.getIntExtra("in2", 0)
                val v3 = intent.getIntExtra("in3", 0)
                val v4 = intent.getIntExtra("in4", 0)

                Log.d("BROADCAST RECEIVER", "Received: $v1, $v2, $v3, $v4")

                in1.setText(v1.toString())
                in2.setText(v2.toString())
                in3.setText(v3.toString())
                in4.setText(v4.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var0702_main)

        in1 = findViewById(R.id.input1)
        in2 = findViewById(R.id.input2)
        in3 = findViewById(R.id.input3)
        in4 = findViewById(R.id.input4)

        val serviceIntent = Intent(this, PracticalTest01Var0702Service::class.java)
        startService(serviceIntent)

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val message = result.data?.getStringExtra("result")
                if (message != null) {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        val navigateToSecondaryActivityButton = findViewById<Button>(R.id.press_me_button)
        navigateToSecondaryActivityButton.setOnClickListener {
            if (in1.text.toString() == "" || in2.text.toString() == "" || in3.text.toString() == "" || in4.text.toString() == "") {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this, PracticalTest01Var0702SecondaryActivity::class.java)
                intent.putExtra("in1", Integer.valueOf(in1.text.toString()))
                intent.putExtra("in2", Integer.valueOf(in2.text.toString()))
                intent.putExtra("in3", Integer.valueOf(in3.text.toString()))
                intent.putExtra("in4", Integer.valueOf(in4.text.toString()))
                activityResultsLauncher.launch(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //registerReceiver(messageBroadcastReceiver, IntentFilter("ProcessingThread"))
        registerReceiver(messageBroadcastReceiver, IntentFilter("ProcessingThread"), RECEIVER_EXPORTED)

    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageBroadcastReceiver)
    }

    override fun onDestroy() {
        stopService(Intent(this, PracticalTest01Var0702Service::class.java))
        super.onDestroy()
    }

}