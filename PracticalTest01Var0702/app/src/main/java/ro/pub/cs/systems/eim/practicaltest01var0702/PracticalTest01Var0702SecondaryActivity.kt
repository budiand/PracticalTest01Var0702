package ro.pub.cs.systems.eim.practicaltest01var0702

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var0702SecondaryActivity : AppCompatActivity() {


    private lateinit var in11: TextView
    private lateinit var in22: TextView
    private lateinit var in33: TextView
    private lateinit var in44: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_activity)

        val in1 = intent.getIntExtra("in1", 0)
        val in2 = intent.getIntExtra("in2", 0)
        val in3 = intent.getIntExtra("in3", 0)
        val in4 = intent.getIntExtra("in4", 0)
        in11 = findViewById(R.id.input11)
        in22 = findViewById(R.id.input22)
        in33 = findViewById(R.id.input33)
        in44 = findViewById(R.id.input44)

        in11.text = in1.toString()
        in22.text = in2.toString()
        in33.text = in3.toString()
        in44.text = in4.toString()

        val sum = findViewById<Button>(R.id.sum_btn)
        sum.setOnClickListener {
            val result = in1 + in2 + in3 + in4
            val intent = Intent()
            intent.putExtra("result", "Sum = $result")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        val prod = findViewById<Button>(R.id.prod_btn)
        prod.setOnClickListener {
            val result = in1 * in2 * in3 * in4
            val intent = Intent()
            intent.putExtra("result", "Product = $result")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}