package com.example.prefereceskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
//import com.example.prefereceskotlin.UserView.Companion.pref

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var pref:Preferencia
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        pref = Preferencia(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val name: EditText = findViewById(R.id.nombre)
        val contra:EditText = findViewById(R.id.contra)
        val check: Switch = findViewById(R.id.switch1)
        val btnlogin: Button   = findViewById(R.id.btnlogin)


        fun goNextPage(){
            startActivity(Intent(this,MainActivity2::class.java))
        }


        fun validateInformation(){
            if (name.text.toString().isNotEmpty() && contra.text.toString().isNotEmpty() && check.isChecked){
                pref.saveInformation(name.text.toString(),contra.text.toString())
                pref.checkboxSwitch(check.isChecked)
                goNextPage()
            }else if (name.text.toString().isNotEmpty() && contra.text.toString().isNotEmpty()){
                goNextPage()
            }
        }

        if (pref.getName().isNotEmpty() && pref.getPassword().isNotEmpty()){
            goNextPage()
        }

        btnlogin.setOnClickListener {
            validateInformation()
        }

    }
}