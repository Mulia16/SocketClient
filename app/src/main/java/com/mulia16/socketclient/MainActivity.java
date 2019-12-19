package com.mulia16.socketclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    final static String IPSERVER = "127.0.0.1";
    final static int PORTSERVER = 8888;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress srvAddress = InetAddress.getByName(IPSERVER);
                    Socket client = new Socket(srvAddress, PORTSERVER);
                    inputStream = new DataInputStream(client.getInputStream());
                    outputStream = new DataOutputStream(client.getOutputStream());
                    outputStream.writeUTF("Mulia Firmansyah Arief");
                    Toast.makeText(getApplicationContext(), inputStream.readUTF(), Toast.LENGTH_SHORT).show();
                    Thread.sleep(2000);
                    client.close();
                    outputStream.close();
                } catch (UnknownHostException e) {
                    Log.e("ERROR", e.toString());
                } catch (IOException e) {
                    Log.e("ERROR", e.toString());
                } catch (InterruptedException e) {
                    Log.d("ERROR", e.toString());
                }
            }
        }).start();

    }
}
