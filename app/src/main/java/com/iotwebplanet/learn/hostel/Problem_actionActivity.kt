package com.iotwebplanet.learn.hostel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_problem_action.*

class Problem_actionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_action)

        val id_num=intent.extras.getString("id_num");
        val name=intent.extras.getString("name");
        val st=intent.extras.getString("status");

        //set
        textIdnumValue.text=id_num
      textStatusValue.text=st
        textNameValue.text=name


    }
}
