package com.example.uodi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uodi.databinding.ActivityCouponBinding
import kr.ac.hallym.seoseuofolio.CouponAdapter

class CouponActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCouponBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //Main에서넘어온 Section 값 받아오기


        //DB 인스턴스 만들기
        val dbHelper = CouponDBHelper(this)
        val db = dbHelper.writableDatabase

        val cName = mutableListOf<String>()
        val cID = mutableListOf<Int>()


        var cursor = db.rawQuery("SELECT couponName, _id FROM coupon_table", null)

        while (cursor.moveToNext()) {
            val couponName = cursor.getString(cursor.getColumnIndex("couponName"))
            val couponID = cursor.getInt(cursor.getColumnIndex("_id"))
            Log.d("cID", "DB에서 가져온 cID 값들 순서대로 : $couponID")
            
            cName.add(couponName)
            cID.add(couponID)
        }

        cursor.close()


        binding.recyclerviewCoupon.layoutManager = LinearLayoutManager(this)
        val adapter = CouponAdapter(this, cName, cID)
        binding.recyclerviewCoupon.adapter = adapter
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