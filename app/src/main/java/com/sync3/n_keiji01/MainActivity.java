package com.sync3.n_keiji01;

import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //送信用データクラス
    class SendData{
        int a;
        int b;
    }
    //受信用データクラス
    class RecvData{
        int anser;
    }
    //GAS
    final String GAS_URL="https://script.google.com/macros/s/AKfycbwA9DC70o-jpaH1rjQ1Ed9DACgEPrQuyN7oTRMVt6-3J4IVEsjU/exec";
    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(){
            @Override
            public  void run (){
              super.run();

                //送信データの作成
                SendData sendData = new SendData();
                sendData.a = 10;
                sendData.b = 20;

                //GASに接続
                RecvData recvData = Json.send(GAS_URL,sendData,RecvData.class);

                if(recvData != null)
                    setText(""+recvData.anser);
                else
                    System.out.println("送信失敗");
          }
        };
        thread.start();
    }
    void setText(final String text){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = (TextView)findViewById(R.id.view);
                textView.setText(text);
            }
        });
    }

}
