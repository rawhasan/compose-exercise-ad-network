package com.example.adnetwork

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

// find the current activity from a composable
fun Context.findActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}