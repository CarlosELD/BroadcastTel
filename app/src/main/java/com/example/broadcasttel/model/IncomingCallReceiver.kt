package com.example.broadcasttel.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.widget.Toast


@Suppress("DEPRECATION")
class IncomingCallReceiver : BroadcastReceiver() {

    fun showToastMsg(context: Context, msg: String) {
        val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val stateStr = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            when (stateStr) {
                TelephonyManager.EXTRA_STATE_RINGING -> {
                    if (phoneNumber != null) {
                        //Log.d("TEL", "Número de teléfono: $phoneNumber")
                        showToastMsg(context, msg = "Llamada de: $phoneNumber")
                        sendMessage(context, phoneNumber)
                    } else {
                        showToastMsg(context, msg = "Número de teléfono desconocido")
                    }
                }
            }
        }
    }

    private fun sendMessage(context: Context, phoneNumber: String?) {
        if (!phoneNumber.isNullOrBlank()) {
            val smsManager = SmsManager.getDefault()
            val message = "Hola, este es un mensaje automatico."
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            showToastMsg(context, msg = "Mensaje Enviado a $phoneNumber")
        } else {
            showToastMsg(context, msg = "Número de teléfono no disponible")
        }
    }
}