package com.example.uodi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uodi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()   {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Toast.makeText(this, "화면을 아래로 스크롤하면\n내 위치가 새로고침 됩니다.", Toast.LENGTH_SHORT).show();

        Log.d("seo","메인 화면")

        var sectionData = getSection()
        var section : String = sectionData.execute().get().toString()
        binding.section.text = section

        //새로고침 부분
        lateinit var swipeRefreshLayout: SwipeRefreshLayout


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            // 새로 고침 동작을 수행하는 코드를 여기에 작성하세요.
            // 예를 들어, 데이터를 다시 로드하는 API 호출 등을 수행할 수 있습니다.

            var dataTest=getSection()
            section = dataTest.execute().get().toString()
            binding.section.text = section

            Log.d("refresh Section data","refresh Section data $section")
            // 새로 고침이 완료되면 다음 메서드를 호출하여 새로 고침 상태를 종료합니다.
            swipeRefreshLayout.isRefreshing = false
        }


        Log.d("Around Activity Section", "Section: $section")

        binding.coupon.setOnClickListener {
            val intent = Intent(this, CouponActivity::class.java)
            intent.putExtra("section",section)
            startActivity(intent)
            finish()
        }

        binding.around.setOnClickListener {
            val intent = Intent(this, AroundActivity::class.java)
            intent.putExtra("section",section)
            startActivity(intent)
            finish()
        }

        binding.goToConfuse.setOnClickListener {
            val intent = Intent(this, ConfusionActivity::class.java)
            startActivity(intent)
            finish()

        }
    }




    //뒤로가기 두번 누르면 종료해주는 함수

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()

            val intent = Intent(this, BeaconService::class.java)
            Log.d("sBLE","백그라운드 서비스 종료 intent호출")
            stopService(intent)


            finishAffinity()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 3000)
    }
}