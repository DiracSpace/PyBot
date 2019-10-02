package com.example.sendaudio;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private OutputStream out;
    private InputStream in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView editarTexto = findViewById(R.id.displaytext);
        final EditText laip = findViewById(R.id.ip);
        final SpeechRecognizer ear = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent earIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        earIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        earIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        ear.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                // aqui movemos nuestro texto
                ArrayList<String> concuerdo = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                // mostrando
                if(concuerdo != null){
                    editarTexto.setText(concuerdo.get(1));
                    String text = concuerdo.get(1);
                    String ip = laip.getText().toString();

                    SendMessage ha = new SendMessage();
                    ha.doInBackground(text,ip);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        findViewById(R.id.recording).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        ear.stopListening();
                        // cuando el usuario ya quito su dedo sucio
                        editarTexto.setHint("Input");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        ear.startListening(earIntent);
                        // cuando el usuario puso su asqueroso dedo
                        editarTexto.setText("");
                        editarTexto.setHint("Escuchando ... ");
                        break;
                }
                return false;
            }
        });
    }
    public void SendMessage(String message){

        new SendMessage().execute(message);
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }
}