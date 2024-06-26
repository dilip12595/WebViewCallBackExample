package com.example.webviewcallbackexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Get the view id
        val button: Button = findViewById(R.id.webViewBtn)

        // Button click listener
        button.setOnClickListener {

            // Start the web view activity on click of button from this activity
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)

        }

        // Get the intent data
        val data = intent.getStringExtra("data")

        // Check and use as per your requirements if data is not null
        data?.let {
            Toast.makeText(this, "We have received this message from web site : $it", Toast.LENGTH_LONG).show()
        }

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(40, 50, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}