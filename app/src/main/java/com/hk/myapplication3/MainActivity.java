package com.hk.myapplication3;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private NotificationManager systemService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = new Intent(this,MyService.class);
        startService(intent);

        systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sendMsgNotify();
    }
    @SuppressLint("NewApi")
    private void sendMsgNotify(){
        String title ="title";
        String content = "content";
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("来自客满满的通知");//通知首次出现在通知栏，带上升动画效果的
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.drawable.ic_launcher_background);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setContentTitle(title);//设置标题
        builder.setContentText(content);//设置内容
        builder.setPriority(Notification.PRIORITY_DEFAULT); //设置该通知优先级
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转 设置通知栏点击意图
        Intent intent = new Intent(this, MainActivity2.class);
        // 构建PendingIntent实际上是构建Intent

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pIntent);
        // 设置点击之后自动消失
        builder.setAutoCancel(true);
        // 具备声音震动呼吸灯
        systemService.notify(0, builder.build());


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.print) {
            MyService myService = new MyService();
            myService.printLn();
        }

        return super.onOptionsItemSelected(item);
    }
}
