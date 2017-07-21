package com.iotwebplanet.learn.hostel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_problem_action.*
import kotlinx.android.synthetic.main.activity_problem_action.view.*

class Problem_actionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_action)

        val id_num=intent.extras.getString("id_num");
        val name=intent.extras.getString("name");
        val st=intent.extras.getString("status");
        val mobile=intent.extras.getString("mobile")
        val com_id=intent.extras.getString("com_id")
        val hostel=intent.extras.getString("hostel")
        val problemtype=intent.extras.getString("problem_type")
        val roomnum=intent.extras.getString("room_number")
        val description=intent.extras.getString("description")
        val created=intent.extras.getString("created_on")



        //set
        sIdTextViewPView.text=id_num
        nameTextViewPView.text=name
        mobileTextViewPView.text=mobile
        comIdTextViewPView.text=com_id
        hostelTextViewPView.text=hostel
        problemTypeTVPView.text=problemtype
        roomTextViewPView.text=roomnum
        statusTVPView.text=st
        descTextViewPView.text=description
        //createdon.text=created




        //textIdnumValue.text=id_num
     // textStatusValue.text=st
      //  textNameValue.text=name


    }
}
