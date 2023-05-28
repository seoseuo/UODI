package com.example.uodi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uodi.databinding.ActivityConfusionBinding

class ConfusionActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val binding = ActivityConfusionBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val confuse = mutableListOf<String>("혼잡", "보통", "원활")
            val color = mutableListOf<String>("#F44336","#8BC34A","#00BCD4")


            var first = (0..2).random()
            var second = (0..2).random()
            var third = (0..2).random()


            Log.d("con","스와이프 전 랜덤 결정 : $first , $second , $third")

            binding.A0C.text = confuse[first]
            binding.B0C.text = confuse[second]
            binding.C0C.text = confuse[third]


            Log.d("con","스와이프 전 이름 : $first , $second , $third")

            binding.A0C.setTextColor(Color.parseColor(color[first]))
            binding.B0C.setTextColor(Color.parseColor(color[second]))
            binding.C0C.setTextColor(Color.parseColor(color[third]))


            Log.d("con","스와이프 전 혼잡도 : $first , $second , $third")

            lateinit var swipeRefreshLayout: SwipeRefreshLayout

            swipeRefreshLayout = binding.swipeConfuseLayout
            swipeRefreshLayout.setOnRefreshListener {
                // 새로 고침 동작을 수행하는 코드를 여기에 작성하세요.
                // 예를 들어, 데이터를 다시 로드하는 API 호출 등을 수행할 수 있습니다.

                first = (0..2).random()
                second = (0..2).random()
                third = (0..2).random()


                Log.d("con","스와이프 후 핸덤결정 : $first , $second , $third")

                binding.A0C.text = confuse[first]
                binding.B0C.text = confuse[second]
                binding.C0C.text = confuse[third]

                Log.d("con","스와이프 후 핸덤결정 : $first , $second , $third")

                binding.A0C.setTextColor(Color.parseColor(color[first]))
                binding.B0C.setTextColor(Color.parseColor(color[second]))
                binding.C0C.setTextColor(Color.parseColor(color[third]))

                Log.d("con","스와이프 후 색 배치 $first , $second , $third ")

                swipeRefreshLayout.isRefreshing = false
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


