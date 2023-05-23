package com.example.uodi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uodi.databinding.ActivityAroundBinding

class AroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("seo","주변 매장 정보 액티비티")

        val binding = ActivityAroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Main에서넘어온 Section 값 받아오기
        lateinit var section: String
        val receivedIntent = intent
        if (receivedIntent != null) {
            section = receivedIntent.getStringExtra("section").toString()
            if (section != null) {
                binding.aroundSection.text = section
            }
        }

        var contents1 = mutableListOf<Int>()
        var contents2 = mutableListOf<String>()
        var contents3 = mutableListOf<String>()
        var contents4 = mutableListOf<String>()

        // content4 도 만들어서 key 값 넣기
        when (section) {
            "A-0" -> {
                contents1 = mutableListOf(
                    R.drawable.nikdehall,
                    R.drawable.seolhall,
                    R.drawable.starbuckshall,
                )
                contents2 = mutableListOf("NIKE", "설하수", "STARBUCKS")
                contents3 = mutableListOf("나이키 매장 입니다.", "설하수 매장 입니다.", "스타벅스 매장입니다.")
                contents4 = mutableListOf("0", "1", "2")
            }
            "B-0" -> {
                contents1 = mutableListOf(
                    R.drawable.conversehall,
                    R.drawable.outback
                )
                contents2 = mutableListOf("컨버스", "아웃백")
                contents3 = mutableListOf("컨버스 매장 입니다", "아웃백 매장입니다.")
                contents4 = mutableListOf("3", "4")
            }
            "C-0" -> {
                contents1 = mutableListOf(
                    R.drawable.tissohall
                )
                contents2 = mutableListOf("tisso")
                contents3 = mutableListOf("티쏘 매장입니다.")
                contents4 = mutableListOf("5")
            }
            else -> {
                contents1 = mutableListOf(
                    R.drawable.er
                )
                contents2 = mutableListOf("오류")
                contents3 = mutableListOf("데이터 값을 읽어오지 못했습니다.")
                contents4 = mutableListOf("-1")
            }
        }

        binding.recyclerviewAround.layoutManager = LinearLayoutManager(this)
        val adapter = AroundAdapter(this, contents1, contents2, contents3, contents4)
        binding.recyclerviewAround.adapter = adapter
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
