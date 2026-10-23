package com.example.secureexpensesplitter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var etEmail : EditText
    lateinit var etPassword : EditText
    lateinit var btnLogin : Button
    lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // define sp variable
        sp = getSharedPreferences("USERINFO",MODE_PRIVATE)
        val status = sp.getBoolean("isLoggedIn",false)
        if (status) {
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
            finish()
        }

        btnLogin.setOnClickListener {
            val UserEmail = etEmail.text.toString().trim()
            val UserPass = etPassword.text.toString().trim()

            //store data in shared preference
            val editor = sp.edit()
            editor.putBoolean("isLoggedIn",true)
            editor.apply()

            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)

            //remove space from m/y or closed the page
                finish()
        }
    }
}