package com.example.internal_calendar_phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    private static final String TAG = "ReceiveSms";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from;

            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        // Cria uma instância da classe postActivity
                        postActivity activity = new postActivity();
                        // Chama o método sendPostRequest(), passando o contexto corretamente
                        activity.sendPostRequest(context, msgBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
