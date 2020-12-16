package com.example.kotlinsqliteexample.Helper

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InputValidation(private val context: Context) {
    fun isInputEditTextFilled(
        textInputEditText: TextInputEditText, textInputLayout: TextInputLayout, message: String?
    ): Boolean {
        val value = textInputEditText.text.toString().trim { it <= ' ' }
        if (value.isEmpty()) {
            textInputLayout.error = message
            hideKeyboardFrom(textInputEditText)
            return false
        }
        else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }

    fun isInputEditTextEmail(
        textInputEditText: TextInputEditText, textInputLayout: TextInputLayout, message: String?
    ): Boolean {
        val value = textInputEditText.text.toString().trim { it <= ' ' }
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.error = message
            hideKeyboardFrom(textInputEditText)
            return false
        }
        else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }

    fun isInputEditTextMatches(
        textInputEditText1: TextInputEditText, textInputEditText2: TextInputEditText, textInputLayout: TextInputLayout, message: String?
    ): Boolean {
        val value1 = textInputEditText1.text.toString().trim { it <= ' ' }
        val value2 = textInputEditText2.text.toString().trim { it <= ' ' }
        if (!value1.contentEquals(value2)) {
            textInputLayout.error = message
            hideKeyboardFrom(textInputEditText2)
            return false
        }
        else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun hideKeyboardFrom(view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}