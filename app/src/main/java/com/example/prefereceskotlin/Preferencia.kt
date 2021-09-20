package com.example.prefereceskotlin

import android.content.Context

class Preferencia (context:Context) {

    val BD = "Database";
    val USERNAME="username"
    val CONTRA="contra"
    val CHECBOX = "state"

    //Implementando el preference
    val  storage = context.getSharedPreferences(BD,Context.MODE_PRIVATE)

    fun saveInformation(name:String,contra:String){
        storage.edit().putString(USERNAME,name).apply()
        storage.edit().putString(CONTRA,contra).apply()
    }

    fun checkboxSwitch(state:Boolean){
        storage.edit().putBoolean(CHECBOX,state).apply()
    }

    fun getName():String{
        return storage.getString(USERNAME,"") !!
    }
    fun getPassword():String{
        return storage.getString(CONTRA,"") !!
    }
    fun getValidation():Boolean{
        return storage.getBoolean(CHECBOX,false)
    }

    fun clearPreferences(){
        storage.edit().clear().apply()
    }
}