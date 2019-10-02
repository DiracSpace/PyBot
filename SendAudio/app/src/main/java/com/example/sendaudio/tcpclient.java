package com.example.sendaudio;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class tcpclient {

    public static final String nick = tcpclient.class.getSimpleName();
    public static final String ip = "192.168.0.10";
    public static final int port = 3456;
    private String text;
    //private OnMessageReceived listener = null;
    private boolean server_scan = false;
    private PrintWriter buffer;
    private BufferedReader bufferentrada;

    public void SendMyMessage(final String message){
        Runnable process = new Runnable(){
            @Override
            public void run() {
                if(buffer != null){
                    Log.d(nick, "enviando: "+message);
                    buffer.println(message);
                    buffer.flush();
                }
            }
        };
        Thread sendmessage = new Thread(process);
        sendmessage.start();
    }

    public void StopMyProcess(){
        server_scan = false;

        if(buffer != null){
            buffer.flush();
            buffer.close();
        }

        bufferentrada = null;
        buffer = null;
        text = null;
    }

    public void HereWeGo(){
        // to keep scanning that nigga
        server_scan = true;

        try {

            // get that pc IP address
            InetAddress nigga_server = InetAddress.getByName(ip);

            Log.d("TCP Client", "C: Connecting to that nigga ..");

            // need a fucking socket for sending shit
            Socket s = new Socket(nigga_server, port);

            try {
                // send that message baby
                buffer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
            }catch(Exception e){
                Log.e("TCP", "S: Error", e);
            }
            // here we would close the socket in case we needed to
            // but we will leave the socket connection opened for further message sending
        }catch (Exception e){
            Log.e("TCP", "C: you fucked up", e);
        }
    }
}