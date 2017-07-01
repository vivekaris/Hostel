package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.iotwebplanet.learn.hostel.R.anim.abc_fade_in
import com.iotwebplanet.learn.hostel.R.anim.abc_fade_out

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val mainActivity=Intent(this@SplashActivity,MainActivity::class.java)
        //val mainActivity=Intent(this@SplashActivity,All_problemActivity::class.java)
        startActivity(mainActivity)

        overridePendingTransition(abc_fade_in, abc_fade_out)
        finish()
    }
}
