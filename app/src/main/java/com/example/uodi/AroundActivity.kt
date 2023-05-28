package com.example.uodi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
            Log.d("section","From StoreAct $section")

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
                contents2 = mutableListOf(
                    "NIKE",
                    "설화수",
                    "STARBUCKS",)

                contents3 = mutableListOf(
                    "JUST DO IT, NIKE 매장입니다.",
                    "혁신적인 한방 피부 과학, 설화수 매장입니다. ",
                    "다국적 글로벌 최대 카페, STARBUCKS 매장입니다.")
                contents4 = mutableListOf("0", "1", "2")
            }
            "B-0" -> {
                contents1 = mutableListOf(
                    R.drawable.conversehall,
                    R.drawable.osh,
                    R.drawable.tissohall
                )
                contents2 = mutableListOf(
                    "CONVERSE",
                    "오설록",
                    "클래식 시계 매장")

                contents3 = mutableListOf(
                    "스니커즈 문화의 아이콘, CONVERSE 매장입니다.",
                    "차와 제주가 선사하는 가치 있는 쉼터\n오설록 매장입니다.",
                    "Tisso , Hamilton 등 명품 클래식 시계 매장입니다.")

                contents4 = mutableListOf("3","4", "5")
            }
            "C-0" -> {
                contents1 = mutableListOf(
                    R.drawable.er
                )
                contents2 = mutableListOf("추가 예정입니다.")
                contents3 = mutableListOf(
                    "C-0 구역 매장 추가 예정입니다.")
                contents4 = mutableListOf("7")
            }
            else -> {
                contents1 = mutableListOf(
                    R.drawable.er,

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
