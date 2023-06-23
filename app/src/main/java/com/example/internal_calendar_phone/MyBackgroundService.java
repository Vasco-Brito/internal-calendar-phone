package com.example.internal_calendar_phone;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyBackgroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Coloque o código que deseja executar em segundo plano aqui, por exemplo:
        sendPostRequest(getApplicationContext(), "Mensagem de teste");

        // Retorne a flag START_STICKY para indicar que o serviço deve ser reiniciado se for encerrado pelo sistema
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Este método é obrigatório, mas como não estamos fornecendo vinculação ao serviço, podemos retornar null
        return null;
    }

    public void sendPostRequest(Context context, String message) {
        // Seu código para enviar a solicitação POST aqui
        // Certifique-se de lidar com o contexto adequadamente
    }

}
