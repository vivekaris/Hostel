package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_no_internet.*


class No_internetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)

        tryagain.setOnClickListener({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })
    }
}
