package com.example.uodi

import android.Manifest
import android.app.Service
import android.bluetooth.BluetoothManager
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.bluetooth.le.BluetoothLeAdvertiser
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.ParcelUuid
import android.util.Log
import androidx.core.app.ActivityCompat


class BeaconService : Service() {
    private var bluetoothLeAdvertiser: BluetoothLeAdvertiser? = null
    override fun onCreate() {
        super.onCreate()
        Log.d("sBLE", "=================")
        Log.d("sBLE", "백 그라운드 서비스 실행 호출")

        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter
        bluetoothLeAdvertiser = bluetoothAdapter.bluetoothLeAdvertiser

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("sBLE", "백 그라운드 서비스 시작")
        startAdvertising()

        // 서비스가 강제 종료되었을 때 다시 시작하지 않도록 START_NOT_STICKY 플래그 반환
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("sBLE", "백 그라운드 서비스 소멸 호출")
        stopAdvertising()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun startAdvertising() {
        val settings = buildAdvertiseSettings()
        val data = buildAdvertiseData()

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {
            // 필요한 권한 요청
            var check : String = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE).toString()
            Log.d("sBLE",check)
            Log.d("sBLE", "비콘 동작 실패 (퍼미션에 걸린 듯)")
            return
        }
        bluetoothLeAdvertiser!!.startAdvertising(settings, data, advertiseCallback)
        Log.d("sBLE", "비콘 동작 실시")
    }

    private fun stopAdvertising() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_ADVERTISE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 필요한 권한 요청
            return
        }
        bluetoothLeAdvertiser!!.stopAdvertising(advertiseCallback)

        Log.d("sBLE", "비콘 동작 중지")
        Log.d("sBLE", "=================")
    }

    private fun buildAdvertiseSettings(): AdvertiseSettings {
        return AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
            .setConnectable(false)
            .build()
    }

    private fun buildAdvertiseData(): AdvertiseData {
        val builder = AdvertiseData.Builder()
        // 비콘 데이터 설정
        val uuid = "a1b2c3d4-e5f6-0123-4567-89abcdef0123" // 비콘의 UUID
        val major = 9999 // 비콘의 Major 값
        val minor = -16 // 비콘의 Minor 값
        builder.setIncludeDeviceName(false)
        builder.addManufacturerData(0x0118, byteArrayOf(0x00)) // AltBeacon의 제조업체 코드와 데이터 필드 설정
        builder.addServiceUuid(ParcelUuid.fromString(uuid))
        builder.addServiceData(
            ParcelUuid.fromString(uuid),
            byteArrayOf(major.toByte(), minor.toByte())
        )
        return builder.build()
    }

    private val advertiseCallback: AdvertiseCallback = object : AdvertiseCallback() {
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings) {
            // 광고 시작 성공
            Log.d("sBLE", "비콘 광고 시작 성공 >,<")
        }

        override fun onStartFailure(errorCode: Int) {
            // 광고 시작 실패
            Log.d("sBLE", "비콘 광고 시작 실패 T,T")
        }
    }
}
