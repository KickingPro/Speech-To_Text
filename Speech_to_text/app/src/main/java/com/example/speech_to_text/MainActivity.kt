package com.example.speech_to_text

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {
    private val rq_speech_rec=101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button= findViewById<Button>(R.id.btn_button)

        button.setOnClickListener{
            askSpeechInput()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == rq_speech_rec && resultCode== Activity.RESULT_OK){
            var text_speech= findViewById<TextView>(R.id.textViewSpeech)
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            text_speech.text= result?.get(0).toString()
        }
    }

    private fun askSpeechInput(){
        if(!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this, "Speech Recognition Not Available", Toast.LENGTH_SHORT).show()
        } else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something!")
            startActivityForResult(i, rq_speech_rec)
        }
    }
}