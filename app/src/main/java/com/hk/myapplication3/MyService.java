package com.hk.myapplication3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new myBider();
    }

    private class myBider extends Binder implements IPlayer {
        @Override
        public void printLn() {
            printLn();
        }
    }

    public void printLn() {
        System.out.println("123123");
    }
}
