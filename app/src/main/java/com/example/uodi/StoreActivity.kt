package com.example.uodi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.uodi.databinding.ActivityStoreBinding

class StoreActivity : AppCompatActivity() {

    lateinit var receivedIntent: Intent
    lateinit var store: String
    lateinit var section: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Main에서 넘어온 Section 값 받아오기

        receivedIntent = intent
        store = receivedIntent.getStringExtra("sData").toString()
        section = receivedIntent.getStringExtra("sectionKey").toString()

        Log.d("section","STORE - 어답터한테 받은 sectionKey : $section")

        val binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val includedLayout = findViewById<LinearLayout>(R.id.includedLayout)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (section) {
            "0" -> {section = "A-0"}

            "3" -> {section = "B-0"}

            "6" -> {section = "C-0"}

            else -> {section = "-1"}
        }

        Log.d("section","STORE - WHEN 에서 결정 : $section")

        when (store) {
            "0" -> {
                layoutInflater.inflate(R.layout.store_nike, includedLayout)
            }

            "1" -> {
                layoutInflater.inflate(R.layout.store_seolhwasu, includedLayout)
            }

            "2" -> {
                layoutInflater.inflate(R.layout.store_starbucks, includedLayout)
            }

            "3" -> {
                layoutInflater.inflate(R.layout.store_converse, includedLayout)
            }

            "4" -> {
                layoutInflater.inflate(R.layout.store_osh, includedLayout)
            }

            "5" -> {
                layoutInflater.inflate(R.layout.store_tisso, includedLayout)
            }

            "6" -> {
                layoutInflater.inflate(R.layout.store_default, includedLayout)
            }

            // 다른 case에 따른 레이아웃 추가
            else -> {
                layoutInflater.inflate(R.layout.store_default, includedLayout)
            }
        }



    }

    override fun onBackPressed() {
        val intent = Intent(this, AroundActivity::class.java)
        intent.putExtra("section",section)
        Log.d("section","STORE - AROUND로 전송 $section")
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }}
}

