package com.example.uodi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.uodi.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkAndRequestPermissions()

        binding.startBtn.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //BLUETOOTH_ADVERTISE PERMSSION 체크
    // 권한 요청 시 사용할 상수
    private val PERMISSION_REQUEST_CODE = 123
    private fun checkAndRequestPermissions() {
        Log.d("sBLE","StartActivity에서 권한 체크 함수 호출")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission = Manifest.permission.BLUETOOTH_ADVERTISE
            val granted = PackageManager.PERMISSION_GRANTED
            val permissionCheck = ContextCompat.checkSelfPermission(this, permission)
            if (permissionCheck != granted) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("sBLE","퍼미션 요청 함수 슛 !")

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 승인된 경우
                val intent = Intent(this, BeaconService::class.java)
                Log.d("sBLE", "백그라운드 서비스 intent로 호출")
                startService(intent)
            } else {
                // 권한이 거부된 경우
                Log.d("sBLE", "퍼미션 권한 거부, 허용 요청 다이얼로그 실행")
                showPermissionDeniedDialog()
                // 권한이 거부된 경우 처리할 로직 추가
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("권한 필요")
            .setMessage("이 기능을 사용하기 위해서는 블루투스 권한이 필요합니다.")
            .setPositiveButton("허용") { _, _ ->

                requestBluetoothAdvertisePermission()

                val intent = Intent(this, BeaconService::class.java)
                Log.d("sBLE", "요청 허용 후 , 백그라운드 서비스 intent로 호출")
                startService(intent)
            }
            .setNegativeButton("거부") { dialog, _ ->
                dialog.dismiss()
                // 권한이 거부된 경우 처리할 로직 추가
            }
            .show()
    }

    private fun requestBluetoothAdvertisePermission() {
        Log.d("sBLE", "다이얼로그에서 퍼미션 요청 함수 호출")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission = Manifest.permission.BLUETOOTH_ADVERTISE

            Log.d("sBLE", "실제 퍼미션 요청 함수를 호출.")
            ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_CODE)
        }
    }



    //두번 빠르게 누르면 앱 종료하는 기능

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
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
