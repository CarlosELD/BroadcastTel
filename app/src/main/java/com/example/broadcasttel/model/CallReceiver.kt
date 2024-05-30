package com.example.broadcasttel.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.view.Gravity
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    fun showToastMsg(context: Context, msg: String) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
        when (state) {
            TelephonyManager.EXTRA_STATE_OFFHOOK -> showToastMsg(context!!, msg = "La llamada esta comenzando..!")
            TelephonyManager.EXTRA_STATE_IDLE -> showToastMsg(context!!, msg = "Llamada finalizada..!")
            TelephonyManager.EXTRA_STATE_RINGING -> showToastMsg(context!!, msg = "Llamada entrante..!")
            else -> showToastMsg(context!!, msg = "Nada..!")
        }
    }
}
