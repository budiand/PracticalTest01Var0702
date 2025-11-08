package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07_Secondary : AppCompatActivity() {

    private lateinit var input11: TextView
    private lateinit var input22: TextView
    private lateinit var input33: TextView
    private lateinit var input44: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val in1 = intent.getIntExtra("input1", 0)
        val in2 = intent.getIntExtra("input2", 0)
        val in3 = intent.getIntExtra("input3", 0)
        val in4 = intent.getIntExtra("input4", 0)

        input11 = findViewById(R.id.input1)
        input22 = findViewById(R.id.input2)
        input33 = findViewById(R.id.input3)
        input44 = findViewById(R.id.input4)

        input11.text = in1.toString()
        input22.text = in2.toString()
        input33.text = in3.toString()
        input44.text = in4.toString()


        val sum = findViewById<TextView>(R.id.sum)
        sum.setOnClickListener {
            val result = in1 + in2 + in3 + in4
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(RESULT_OK, intent)
            finish()
        }

        val prod = findViewById<TextView>(R.id.prod)
        prod.setOnClickListener {
            val result = in1 * in2 * in3 * in4
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}