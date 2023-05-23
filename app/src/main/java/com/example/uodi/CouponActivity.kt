package com.example.uodi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import com.example.uodi.databinding.ActivityCouponBinding

class CouponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCouponBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //Main에서넘어온 Section 값 받아오기

        lateinit var section : String
        val receivedIntent = intent
        if (receivedIntent != null) {
            // "key"라는 키로 전달된 값을 getStringExtra() 메서드를 사용하여 가져옵니다.
            section= receivedIntent.getStringExtra("section").toString()
            if(section!=null) {
                binding.couponSection.text = section
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // 뒤로가기 버튼을 클릭했을 때의 동작을 여기에 작성합니다.
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}