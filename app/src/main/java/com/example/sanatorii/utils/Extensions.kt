package com.example.sanatorii.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun EditText.isNotEmpty(): Boolean {
    return if (text.isEmpty()) {
        error = "Ошибка введите еще раз"
        requestFocus()
        false
    } else true
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}