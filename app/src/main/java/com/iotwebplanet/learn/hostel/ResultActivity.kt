package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val comid=intent.extras.getString("com_id")
        status.text="Registered successfully :"+comid


        homeBtn.setOnClickListener({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })

    }

    override fun onBackPressed() {
        val mainActivity= Intent(this@ResultActivity,MainActivity::class.java)
        startActivity(mainActivity)
    }
}
