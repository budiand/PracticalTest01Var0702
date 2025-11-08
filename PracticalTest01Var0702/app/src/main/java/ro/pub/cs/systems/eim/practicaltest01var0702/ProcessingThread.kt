package ro.pub.cs.systems.eim.practicaltest01var0702

import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import java.util.Date
import java.util.Random
import kotlin.math.sqrt



class ProcessingThread(private val context: Context) : Thread() {
    private var isRunning = true
    private val random = Random()


    override fun run() {
        Log.d("Thread process" ,"Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid()
        )
        while (isRunning) {
            sendMessage()
            sleep()
        }
        Log.d("Thread process", "Thread has stopped!")
    }

    private fun sendMessage() {
        val v1 = random.nextInt(0, 100)
        val v2 = random.nextInt(0, 100)
        val v3 = random.nextInt(0, 100)
        val v4 = random.nextInt(0, 100)

        val intent = Intent()
        intent.setAction("ProcessingThread")
        intent.putExtra("in1", v1)
        intent.putExtra("in2", v2)
        intent.putExtra("in3", v3)
        intent.putExtra("in4", v4)
        context.sendBroadcast(intent)
        Log.d("ProcessingThread", "Broadcast sent: $v1 $v2 $v3 $v4")
    }

    private fun sleep() {
        try {
            sleep(10000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }
}