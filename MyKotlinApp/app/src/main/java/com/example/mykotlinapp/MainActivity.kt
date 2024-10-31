package com.example.mykotlinapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import java.util.Objects

class MainActivity : AppCompatActivity() ,View.OnClickListener {
    private val RQ_SPEECH_REC = 102
    private val REQUEST_CODE_SPEECH_INPUT = 102

    //Kalkulator
    lateinit var btnAdd : Button
    lateinit var btnSub : Button
    lateinit var btnMultiply : Button
    lateinit var btnDivision : Button
    lateinit var etA : EditText
    lateinit var etB : EditText
    lateinit var resultTv : TextView
    lateinit var btn_button_a : Button
    lateinit var btn_button_b : Button
    var textA: Boolean = true

    //Quiz Format
    lateinit var questionTv: TextView
    lateinit var answerATv: TextView
    lateinit var answerBTv: TextView
    lateinit var answerCTv: TextView
    lateinit var btnVoiceInput: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Untuk declare yg kalkulator lmao
        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_minus)
        btnMultiply = findViewById(R.id.btn_multiplication)
        btnDivision = findViewById(R.id.btn_division)
        etA = findViewById(R.id.et_a)
        etB = findViewById(R.id.et_b)
        resultTv = findViewById(R.id.result_tv)
        btn_button_a = findViewById(R.id.btn_button_a)
        btn_button_b = findViewById(R.id.btn_button_b)
        questionTv = findViewById(R.id.question_tv)
        answerATv = findViewById(R.id.answer_a_tv)
        answerBTv = findViewById(R.id.answer_b_tv)
        answerCTv = findViewById(R.id.answer_c_tv)
        btnVoiceInput = findViewById(R.id.btn_voice_input)

        //Untuk declare VR (A) -> Masih bisa ditest kembali, karena ada metode selain Google VA
        btn_button_a.setOnClickListener{
            textA = true
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak something eh ?")

            try {
                startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT)
            }catch (e: Exception) {
                Toast.makeText(this,""+ e.message, Toast.LENGTH_SHORT).show()
            }
        }

        //(B) -> Sama seperti yg A, Masih bisa ditest kembali, karena ada metode selain Google VA
        btn_button_b.setOnClickListener(){
            textA = false
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak something eh ?")

            try {
                startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT)
            }catch (e: Exception) {
                Toast.makeText(this,""+ e.message, Toast.LENGTH_SHORT).show()
            }
        }
        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        btnVoiceInput.setOnClickListener(this)
    }

    //Method dibawah in case ada error di fun kedua
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
//            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//            etA.setText(result?.get(0).toString())
//            etB.setText(result?.get(0).toString())
//        }
//    }

    //Sedikit spare untuk Speech Input dari google tapi mungkin bisa di coba lagi nanti
//    private fun askSpeechInput() {
//        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
//            Toast.makeText(this, "Speech Recognition is not available", Toast.LENGTH_SHORT).show()
//        } else {
//            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something eh?")
//            startActivityForResult(i,RQ_SPEECH_REC)
//        }
//    }

    //Metode untuk kalkulator
    override fun onClick(v: View?) {
        val a = etA.text.toString().toDouble()
        val b = etB.text.toString().toDouble()
        var result: Double=0.0
        when(v?.id){
            R.id.btn_add ->{
                result = a+b
            }
            R.id.btn_minus ->{
                result = a-b
            }
            R.id.btn_multiplication ->{
                result = a*b
            }
            R.id.btn_division ->{
                result = a/b
            }
        }
        resultTv.setText("Result is $result")
    }

    //Method untuk request Speech Input pakai logika bawah
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
//            if (resultCode == RESULT_OK && data != null) {
//                val res : ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
//                //print output dari speech pakai code bawah
//                etA.setText(Objects.requireNonNull(res)[0])
//            }
//        }
//        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
//            if (resultCode == RESULT_OK && data != null) {
//                val res : ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
//                //print output dari speech pakai code bawah
//                etB.setText(Objects.requireNonNull(res)[0])
//            }
//        }
//    }

    //Metode untuk request speech input
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK && data != null) {
            val res: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

            // untuk output sesuai dengan TV tertentu (a & b)
            if (textA) {
                etA.setText(Objects.requireNonNull(res)[0])
            } else {
                etB.setText(Objects.requireNonNull(res)[0])
            }
        }
    }
}
