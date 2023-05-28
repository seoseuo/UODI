package com.example.uodi

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uodi.databinding.ActivityCouponBinding
import com.example.uodi.databinding.ActivityQractivityBinding
import kr.ac.hallym.seoseuofolio.CouponAdapter
import kotlin.text.Typography.section

class QRActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityQractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        Toast.makeText(this, "점원이 스캔 후 사용 완료 버튼을 누를 수 있게 점원에게 화면을 보여주세요", Toast.LENGTH_SHORT).show();

        val receivedIntent = intent

            var cName = receivedIntent.getStringExtra("couponName").toString()
            var cID = receivedIntent.getIntExtra("couponID",0)
        Log.d("cID", "쿠폰 어답터에서 받아온 -> cID : $cID")


        //when에서 어떤 사진 보여줄건지 결정
        var QRIndex : Int
        when (cID) {
            1 -> QRIndex = 0
            2 -> QRIndex = 1
            3 -> QRIndex = 2
            4 -> QRIndex = 3
            else -> QRIndex = 4
        }

        var QRPics = mutableListOf<Int>(
            R.drawable.qr1,
            R.drawable.qr2,
            R.drawable.qr3,
            R.drawable.qr4,
            R.drawable.qr5
        )

            //해당하는 값 보여주기
            binding.cName.text = cName
            binding.QRpic.setImageResource(QRPics[QRIndex])

        binding.useFix.setOnClickListener {
            val db = CouponDBHelper(this).writableDatabase

            db.execSQL("DELETE FROM coupon_table WHERE _id = ?", arrayOf(cID))

            Log.d("cID", "DB에서 삭제할 -> cID : $cID")

            val builder = AlertDialog.Builder(this)

            builder.setTitle("ㅇㄷ? 쿠폰 사용!")
            builder.setMessage("쿠폰 사용이 완료되었습니다.\n이용해주셔서 감사합니다.")

            builder.setOnCancelListener {
                // 팝업 외부를 클릭하여 팝업이 사라질 때 다른 액티비티로 이동
                val intent = Intent(this, CouponActivity::class.java)
                startActivity(intent)
                finish()
            }

            builder.show()
        }

    }



    override fun onBackPressed() {
        val intent = Intent(this, CouponActivity::class.java)
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

