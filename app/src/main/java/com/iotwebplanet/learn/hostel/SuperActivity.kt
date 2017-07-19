package com.iotwebplanet.learn.hostel

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_super.*

/**
 * Created by developer on 10/07/17.
 */
class SuperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super)
        sButton.setOnClickListener({
            val secret_psw=stext.text.toString()
            if(secret_psw=="superuser"){


                    val mainActivity = Intent(this@SuperActivity,add_wardenActivity::class.java)
                    startActivity(mainActivity)

            }

        })

    }

}
