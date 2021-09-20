package com.example.prefereceskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//import com.example.prefereceskotlin.UserView.Companion.pref

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var pref:Preferencia
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        pref = Preferencia(applicationContext)
        // Initialize Firebase Auth
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name: EditText = findViewById(R.id.nombre)
        val contra:EditText = findViewById(R.id.contra)
        val check: Switch = findViewById(R.id.switch1)
        val btnlogin: Button   = findViewById(R.id.btnlogin)


        fun goNextPage(){
            startActivity(Intent(this,MainActivity2::class.java))
        }

        fun SignIn(email:String, contra:String){

            System.out.println("DENTRO DEL METODO ORIGINAL")


            auth.signInWithEmailAndPassword(email, contra)
                .addOnCompleteListener(this) { task ->
                    System.out.println("DENTRO DEL METODO DE FIREBASE")
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        System.out.println("USUARIO CORRECTO !!")
                        goNextPage()
                        val user = auth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        System.out.println("USUARIO NO VALIDO !!")
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }

        fun validateInformation(){

            if (name.text.toString().isNotEmpty() && contra.text.toString().isNotEmpty() && check.isChecked){
                pref.saveInformation(name.text.toString(),contra.text.toString())
                pref.checkboxSwitch(check.isChecked)
                val email = pref.getName()
                System.out.println(email)
                val password = pref.getPassword()
                System.out.println(password)
                SignIn(name.text.toString(),contra.text.toString())

            }else {
                System.out.println("NO SE USO SHAREDPREFERENCE")
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