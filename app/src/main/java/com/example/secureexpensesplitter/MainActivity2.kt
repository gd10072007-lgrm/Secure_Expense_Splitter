package com.example.secureexpensesplitter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var etTotalBill : EditText
    lateinit var etNumber : EditText
    lateinit var SpinnerCategory : Spinner
    lateinit var etSplitBill : EditText
    lateinit var btnCalculate : Button
    lateinit var btnLogout : Button
    lateinit var carsp : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Connect XML views
        etTotalBill = findViewById(R.id.etTotalBill)
        etNumber = findViewById(R.id.etNumber)
        SpinnerCategory = findViewById(R.id.SpinnerCategory)
        etSplitBill = findViewById(R.id.etSplitBill)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnLogout = findViewById(R.id.btnLogout)

        // Spinner
        val category = arrayOf("🍴  Food" , "✈️  Travel")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,category)
        SpinnerCategory.adapter = adapter

        // Calculate button
        btnCalculate.setOnClickListener {
            val TotalBill = etTotalBill.text.toString().trim()
            val Number = etNumber.text.toString().trim()

            if (TotalBill.isEmpty() || Number.isEmpty()) {
                Toast.makeText(this, "Enter all fields",Toast.LENGTH_SHORT).show()
            }
            else {
                val bill = TotalBill.toDouble()
                val friends = Number.toInt()

                if (friends>0) {
                    val result = bill / friends
                    etSplitBill.setText("Rs." + result.toString())
                }
                else {
                    Toast.makeText(this, "Number must be greater than 0", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //Logout button
        btnLogout.setOnClickListener {

            carsp = getSharedPreferences("USERINFO",Context.MODE_PRIVATE)

            val editor = carsp.edit()
            editor.putBoolean("isLoggedIn",false)
            editor.clear().apply()

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }
    }
}


