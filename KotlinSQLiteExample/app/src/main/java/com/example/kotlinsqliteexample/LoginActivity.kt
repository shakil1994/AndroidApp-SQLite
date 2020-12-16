package com.example.kotlinsqliteexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlinsqliteexample.Database.DatabaseHelper
import com.example.kotlinsqliteexample.Helper.InputValidation
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var inputValidation: InputValidation? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListeners()
        initObjects()
    }

    private fun initListeners() {
        appCompatButtonLogin.setOnClickListener(this)
        textViewLinkRegister.setOnClickListener(this)
    }

    private fun initObjects() {
        databaseHelper = DatabaseHelper(this)
        inputValidation = InputValidation(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.appCompatButtonLogin -> verifyFromSQLite()
            R.id.textViewLinkRegister -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun verifyFromSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))){
            return
        }

        if (databaseHelper!!.checkUser(textInputEditTextEmail.text.toString().trim { it <= ' ' },
                textInputEditTextPassword.text.toString().trim { it <= ' ' })) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EMAIL", textInputEditTextEmail.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(intent)
        }
        else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun emptyInputEditText() {
        textInputEditTextEmail.text = null
        textInputEditTextPassword.text = null
    }
}
