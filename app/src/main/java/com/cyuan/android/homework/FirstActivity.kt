package com.cyuan.android.homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        btnNext.setOnClickListener {
            val intent = Intent(this, NetworkDataActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}