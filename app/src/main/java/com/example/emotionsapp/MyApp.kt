package com.example.emotionsapp

import android.app.Application
import com.example.emotionsapp.data.UserDatabase

class EmotionApp : Application() {
    val database: UserDatabase by lazy { UserDatabase.getDatabase(this) }
}