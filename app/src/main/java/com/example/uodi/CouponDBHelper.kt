package com.example.uodi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CouponDBHelper(context: Context) : SQLiteOpenHelper(context, "COUPON.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS coupon_table (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "couponName TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        }
}
