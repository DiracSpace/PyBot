package com.example.sendaudio;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessage extends AsyncTask<String, String, Void> {

    @Override
    protected Void doInBackground(String... params) {
        try {
            try {
                Socket data = new Socket(params[1], 3456);
                PrintWriter ToServer = new PrintWriter(
                        new OutputStreamWriter(
                                data.getOutputStream()
                        )
                );
                ToServer.print(params[0]);
                ToServer.flush();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}